package pattern;

import reservation.Reservation;
import reservation.ReservationStatus;
import user.MemoryUserRepository;
import user.User;

import java.util.List;

public class UserNotificationObserver implements ReservationObserver {
    private final MemoryUserRepository userRepository;

    // 생성자에서 User 저장소를 주입받음 (전체 사용자에게 알림을 보내기 위해)
    public UserNotificationObserver(MemoryUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void update(Reservation reservation) {
        Long targetUserId = reservation.getUserId();
        Long spaceId = reservation.getSpaceId();
        Long reservationId = reservation.getId();

        if (reservation.getStatus() == ReservationStatus.REQUESTED) {
            // 1. 예약 생성: 당사자에게 확정 알림
            sendConfirmMessage(targetUserId, reservationId, spaceId);

        } else if (reservation.getStatus() == ReservationStatus.CANCELLED) {
            // 2. 예약 취소:
            // (1) 당사자에게 취소 확인 알림
            sendCancelMessage(targetUserId, reservationId, spaceId);

            // (2) 로그인한 다른 사용자들에게 "빈 자리 알림" (Marketing)
            broadcastVacancyAlert(targetUserId, spaceId);
        }
    }

    private void sendConfirmMessage(Long userId, Long reservationId, Long spaceId) {
        System.out.println("\n📨 [예약 확정 알림] (To. User " + userId + ")");
        System.out.println("   └─ 공간(ID: " + spaceId + ") 예약이 확정되었습니다. (예약번호: " + reservationId + ")");
    }

    private void sendCancelMessage(Long userId, Long reservationId, Long spaceId) {
        System.out.println("\n📨 [예약 취소 알림] (To. User " + userId + ")");
        System.out.println("   └─ 공간(ID: " + spaceId + ") 예약(번호: " + reservationId + ")이 정상적으로 취소되었습니다.");
    }

    // 로그인한 다른 사용자들에게만 빈 자리 알림 전송
    private void broadcastVacancyAlert(Long excludeUserId, Long spaceId) {
        List<User> allUsers = userRepository.findAll();

        System.out.println("\n📢 [전체 공지: 빈 자리 알림] (현재 접속 중인 사용자에게만 전송)");
        for (User user : allUsers) {
            // 조건 1: 취소한 본인이 아닐 것
            // 조건 2: 현재 로그인(isLoggedIn) 상태일 것
            if (!user.getUserId().equals(excludeUserId) && user.isLoggedIn()) {
                System.out.println("   (To. User " + user.getUserId() + ") \"" + user.getUsername() + "\"님! 방금 공간(ID: " + spaceId + ") 예약이 취소되어 즉시 예약 가능합니다!");
            }
        }
    }
}
