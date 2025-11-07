import pattern.DefaultSpaceFactory;
import pattern.SpaceFactory;
import payment.PaymentService;
import payment.discount.DiscountPolicyFactory;
import reservation.Reservation;
import reservation.ReservationService;
import reservation.TimeState;
import space.*;
import user.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MemoryUserRepository userRepository = new MemoryUserRepository();
        SpaceRepository spaceRepository = new SpaceRepository();
        DiscountPolicyFactory policyFactory = new DiscountPolicyFactory(); // PaymentService에 필요

        UserService userService = new UserService();
        SpaceFactory spaceFactory = new DefaultSpaceFactory();
        ReservationService reservationService = new ReservationService();
        // PaymentService 생성자에 맞게 3개 인자 전달
        PaymentService paymentService = new PaymentService(spaceRepository, userRepository, policyFactory);


        // testUser 생성
        User testUser = new User(123L, "456", "형균", "123@", PaymentMethod.MOBILE, UserGrade.GOLD, false);
        userService.register(testUser);
        User loggedInUser = userService.login(123L, "456");

        // space 생성 (관리자 입장)
        Space testSpace = spaceFactory.createSpace(
                1L, // Long ID
                "강남 3호점",
                SpaceType.OFFICE,
                SpaceScale.MEDIUM,
                List.of(UnitSpace.MEETING_ROOM, UnitSpace.LOUNGE),
                List.of(SecuritySystem.CCTV, SecuritySystem.DOOR_LOCK)
        );
        spaceRepository.save(testSpace);

        // user와 space 참조해서 reservation 객체 생성
        LocalDateTime startTime = LocalDateTime.now().plusDays(1).withHour(10).withMinute(0).withSecond(0);
        LocalDateTime endTime = startTime.plusDays(1); // 1일 뒤
        TimeState timeState = new TimeState(startTime, endTime);

        // [오류 수정 가정 1] ReservationService.reserve()가 Long spaceId를 받는다고 가정
        Reservation reservation = reservationService.reserve(
                loggedInUser.getUserId(),
                testSpace.getId(), // 1L (Long)
                startTime,
                endTime
        );

        // reservation 객체를 payment에 넘겨서 가격 책정
        // (1) 할인 전 1일 기본가 (UnitSpace + SpaceType + Scale + Security)
        BigDecimal previewOneDay = paymentService.previewOnedayTotal(reservation);
        System.out.println("1일 기본가 (할인 전): " + previewOneDay + "원");

        // (2) 할인 후 1일 최종가 (기본가에 GOLD 등급 할인 적용)
        BigDecimal finalOneDay = paymentService.OnedayTotal(reservation);
        System.out.println("1일 최종가 (GOLD 할인): " + finalOneDay + "원");

        // (3) 총 예약 기간 최종가 (PaymentService의 마지막 previewTotal 메서드 사용)
        BigDecimal finalTotalPrice = paymentService.finalTotal(reservation);
        System.out.println("-----------------------------------");
        System.out.println("총 예약 기간 (" + (reservation.getTime().getPeriod() == 0 ? 1 : reservation.getTime().getPeriod()) + "일) 최종 결제 금액: " + finalTotalPrice + "원");

    }
}