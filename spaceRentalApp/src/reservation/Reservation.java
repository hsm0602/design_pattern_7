package reservation;

import java.time.LocalDateTime;

/**
 * 예약 정보를 담는 클래스
 */


public class Reservation {
    private Long id;
    private Long userId;
    private Long spaceId;
    private TimeState time;
    private ReservationStatus status;
    private LocalDateTime createdAt;

    // --- 생성자, Getter, Setter 등 ---
}
