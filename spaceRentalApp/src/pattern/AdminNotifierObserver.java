package pattern;

import reservation.Reservation;
import reservation.ReservationStatus;

/**
 * [Observer Pattern]
 * 예약 상태 변경 시 관리자에게 알림을 보내는 옵저버 (구독자)
 */
public class AdminNotifierObserver implements ReservationObserver{
    @Override
    public void update(Reservation reservation) {
        // 관리자에게 알림을 보내는 로직
        // (지금은 콘솔 출력이지만, 나중에 이메일, SMS 등으로 확장 가능)

        if (reservation.getStatus() == ReservationStatus.REQUESTED) {
            System.out.println("🔔 [관리자 알림] 새 예약이 등록되었습니다. (ID: " + reservation.getId() + ")");
        } else if (reservation.getStatus() == ReservationStatus.CANCELLED) {
            System.out.println("🔔 [관리자 알림] 예약이 취소되었습니다. (ID: " + reservation.getId() + ")");
        }
    }
}
