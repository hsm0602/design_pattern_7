package reservation;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 예약 정보를 담는 클래스
 */


public class Reservation {
    private Long id;
    private final Long userId;       // 예약한 사용자 ID
    private final String spaceId;    // 예약된 공간 ID (Space.java의 id가 String이므로 String으로 변경)
    private final TimeState time;    // 예약 시간
    private ReservationStatus status;  // 예약 상태
    private final LocalDateTime createdAt; // 예약 생성 시각

    // --- 생성자 ---
    public Reservation(Long userId, String spaceId, TimeState time) {
        this.userId = userId;
        this.spaceId = spaceId;
        this.time = time;

        // 기본값 설정
        this.status = ReservationStatus.REQUESTED;
        this.createdAt = LocalDateTime.now();
    }

    // --- Getter ---
    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public String getSpaceId() { return spaceId; }
    public TimeState getTime() { return time; }
    public ReservationStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    // --- Setter ---
    // Repository에서만 ID를 설정하기 위해 사용
    public void setId(Long id) {
        this.id = id;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", userId=" + userId +
                ", spaceId='" + spaceId + '\'' +
                ", time=" + time +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }
}
