package reservation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * 예약 정보를 메모리에 저장하고 관리하는 저장소
 */
public class ReservationRepository {
    private static final Map<Long, Reservation> store = new ConcurrentHashMap<>();
    private static final AtomicLong sequence = new AtomicLong(0L); // 고유 ID 생성을 위해 AtomicLong 사용

    /**
     * 예약을 저장하고, 생성된 예약을 반환합니다.
     * ID가 자동으로 할당됩니다.
     */
    public Reservation save(Reservation reservation) {
        reservation.setId(sequence.incrementAndGet()); // 새 ID 발급
        store.put(reservation.getId(), reservation);
        return reservation;
    }

    /**
     * ID로 예약을 조회합니다.
     */
    public Reservation findById(Long id) {
        return store.get(id);
    }

    /**
     * 특정 사용자의 모든 예약 목록을 조회합니다.
     */
    public List<Reservation> findByUser(Long userId) {
        return store.values().stream()
                .filter(r -> r.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    /**
     * 특정 기간에 특정 공간에 겹치는 예약 목록을 조회합니다.
     * (취소된 예약은 제외)
     * @param spaceId 공간 ID (String으로 변경)
     * @param start 시작 시간
     * @param end 종료 시간
     * @return 겹치는 예약 목록
     */
    public List<Reservation> findBySpaceBetween(Long spaceId, LocalDateTime start, LocalDateTime end) {
        TimeState targetTime = new TimeState(start, end);

        return store.values().stream()
                .filter(r -> r.getSpaceId().equals(spaceId)) // 1. 공간 ID가 일치하는가
                .filter(r -> r.getStatus() != ReservationStatus.CANCELLED) // 2. 취소된 예매가 아닌가
                .filter(r -> r.getTime().overlap(targetTime)) // 3. 시간이 겹치는가
                .collect(Collectors.toList());
    }

    /**
     * 예약 상태를 업데이트합니다.
     * @return 성공 시 true, 해당 ID가 없으면 false
     */
    public boolean updateStatus(Long id, ReservationStatus status) {
        Reservation reservation = findById(id);
        if (reservation != null) {
            reservation.setStatus(status);
            return true;
        }
        return false;
    }
}
