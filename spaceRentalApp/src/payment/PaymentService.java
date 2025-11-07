package payment;

import payment.discount.DiscountPolicy;
import payment.discount.DiscountPolicyFactory;
import payment.securitysystempayment.SecurityPaymentFactory;
import payment.spacescale.ScalePaymentFactory;
import payment.spacetype.SpaceTypePaymentFactory;
import payment.unitspace.UnitSpacePaymentFactory;
import reservation.Reservation;
import space.Space;
import space.SpaceRepository;
import user.MemoryUserRepository;
import user.User;
import user.UserGrade;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PaymentService {
    private final SpaceRepository spaceRepository;
    private final MemoryUserRepository userRepository;
    public PaymentService(SpaceRepository spaceRepository,
                          MemoryUserRepository userRepository,
                          DiscountPolicyFactory policyFactory) {
        this.spaceRepository = spaceRepository;
        this.userRepository = userRepository;

    }

    public BigDecimal previewOnedayTotal(Reservation reservation) {
        SecurityPaymentFactory spf = new SecurityPaymentFactory();
        ScalePaymentFactory cpf = new ScalePaymentFactory();
        SpaceTypePaymentFactory stpf = new SpaceTypePaymentFactory();
        UnitSpacePaymentFactory usp = new UnitSpacePaymentFactory();
        BigDecimal result =new BigDecimal(0);
        Space space = spaceRepository.findById(reservation.getSpaceId());//모든 id값은 Long으로 통일 해야함..
        result = space.getUnits().stream().map(e->usp.get(e).apply(new BigDecimal(0))).reduce(BigDecimal::add).get().add(stpf.get(space.getType()).apply(cpf.get(space.getScale()).apply(space.getSecurities().stream().map(e->spf.get(e).applyPayment(new BigDecimal(0))).reduce(BigDecimal::add).get())));
        return result;
    }
    public BigDecimal previewTotal(Reservation reservation){
        int period=reservation.getTime().getPeriod();//시간 관련 메서드로 교체 해야함.
        BigDecimal base = previewOnedayTotal(reservation);
        return base.multiply(new BigDecimal(period));
    }


    public BigDecimal OnedayTotal(Reservation reservation) {
        BigDecimal result =new BigDecimal(0);
        BigDecimal base = previewOnedayTotal(reservation);
        User user = userRepository.findById(reservation.getUserId());
        Space space = spaceRepository.findById(reservation.getSpaceId());
        UnitSpacePaymentFactory usp = new UnitSpacePaymentFactory();
        DiscountPolicyFactory dpf = new DiscountPolicyFactory();
        result =dpf.get(user.getUserGrade()).discount(base);

        return result;
    }
    public BigDecimal finalTotal(Reservation reservation){
        int period=reservation.getTime().getPeriod();//시간 관련 메서드로 교체 해야함.
        BigDecimal base =OnedayTotal(reservation);
        return base.multiply(new BigDecimal(period));
    }
}
