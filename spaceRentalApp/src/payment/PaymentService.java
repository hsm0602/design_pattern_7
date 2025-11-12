package payment;

import payment.addon.AddOnPaymentFactory;
import payment.discount.DiscountPolicyFactory;
import payment.securitysystempayment.SecurityPaymentFactory;
import payment.spacescale.ScalePaymentFactory;
import payment.spacetype.SpaceTypePaymentFactory;
import payment.unitspace.UnitSpacePaymentFactory;
import reservation.Reservation;
import space.domain.Space;
import space.SpaceRepository;
import user.MemoryUserRepository;
import user.User;

import java.math.BigDecimal;

public class PaymentService {
    private final SpaceRepository spaceRepository=new SpaceRepository();
    private final MemoryUserRepository userRepository= new MemoryUserRepository();

    public BigDecimal previewOnedayTotal(Space space) {
        SecurityPaymentFactory spf = new SecurityPaymentFactory();
        ScalePaymentFactory cpf = new ScalePaymentFactory();
        SpaceTypePaymentFactory stpf = new SpaceTypePaymentFactory();
        UnitSpacePaymentFactory usp = new UnitSpacePaymentFactory();
        BigDecimal result =new BigDecimal(0);
        result = space.getUnits().stream().map(e->usp.get(e).apply(new BigDecimal(0))).reduce(BigDecimal::add).get().add(
                 stpf.get(space.getType()).apply(
                         cpf.get(space.getScale()).apply(
                                 space.getSecurities().stream().map(e->spf.get(e).applyPayment(new BigDecimal(0))).reduce(BigDecimal::add).get())));
        return result;
    }


    public BigDecimal OnedayTotal(Reservation reservation) {
        BigDecimal result =new BigDecimal(0);
        Space space=spaceRepository.findById(reservation.getSpaceId()).orElseThrow(()->new IllegalArgumentException("해당 장소가 없음.."));
        BigDecimal base = previewOnedayTotal(space);
        User user = userRepository.findById(reservation.getUserId());
        DiscountPolicyFactory dpf = new DiscountPolicyFactory();
        AddOnPaymentFactory apf= new AddOnPaymentFactory();
        result =dpf.get(user.getUserGrade()).discount(base.add(space.getAddOn().stream().map(e->apf.get(e).applyPayment(new BigDecimal(0))).reduce(BigDecimal::add).get()));

        return result;
    }
    public BigDecimal Total(Reservation reservation){
        int period=reservation.getTime().getPeriod();// 여기 time State메서드로 수정해줘야함.
        BigDecimal base =OnedayTotal(reservation);
        return base.multiply(new BigDecimal(period));
    }
}
