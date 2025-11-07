package reservation;

import pattern.ReservationObserver;
import space.Space;
import space.SpaceRepository;
import user.MemoryUserRepository;
import user.PaymentMethod;
import user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 예약 관련 비즈니스 로직을 처리하는 서비스 (Observer Pattern의 Subject 역할)
 */
public class ReservationService {
    private final MemoryUserRepository userRepository = new MemoryUserRepository();
    private final SpaceRepository spaceRepository = new SpaceRepository();
    private final ReservationRepository reservationRepository = new ReservationRepository();

    private final List<ReservationObserver> observers = new ArrayList<>();

    // Observer 등록
    public void addObserver(ReservationObserver observer) {
        observers.add(observer);
    }

    // Observer 해제
    public void removeObserver(ReservationObserver observer) {
        observers.remove(observer);
    }

    // 상태 변경 시 Observer들에게 알림
    public void notifyObservers(Reservation reservation) {
        for (ReservationObserver observer : observers) {
            observer.update(reservation);
        }
    }

    /**
     * 공간을 예약하는 핵심 로직
     *
     * @param userId  예약자 ID
     * @param spaceId 공간 ID (String으로 변경)
     * @param start   시작 시간
     * @param end     종료 시간
     * @return 예약 성공 시 Reservation 객체, 실패 시 null
     */
    public Reservation reserve(Long userId, Long spaceId, LocalDateTime start, LocalDateTime end) {
        // 1. 사용자 조회 및 로그인 확인
        User user = userRepository.findById(userId);
        if (user == null) {
            System.out.println("[예약 실패] 존재하지 않는 사용자입니다.");
            return null;
        }
        if (!user.isLoggedIn()) {
            System.out.println("[예약 실패] 로그인이 필요합니다.");
            return null;
        }

        // 2. 공간 조회
        Space space = spaceRepository.findById(spaceId); // (SpaceRepository 구현 필요)
        if (space == null) {
            System.out.println("[예약 실패] 존재하지 않는 공간입니다.");
            return null;
        }

        // 3. 겹치는 예약이 있는지 확인 (핵심 로직)
        List<Reservation> conflicts = reservationRepository.findBySpaceBetween(spaceId, start, end);
        if (!conflicts.isEmpty()) {
            System.out.println("[예약 실패] 해당 시간에 이미 예약이 존재합니다.");
            return null; // 예약이 겹치면 실패
        }

        // 4. 예약 생성 및 저장
        TimeState time = new TimeState(start, end);
        Reservation newReservation = new Reservation(userId, spaceId, time);
        Reservation savedReservation = reservationRepository.save(newReservation);

        // 5. [Observer] 예약 생성 알림
        notifyObservers(savedReservation);

        System.out.println("[예약 성공] " + user.getUsername() + "님, " + space.getDescription() + " 예약 완료");
        return savedReservation;
    }

    /**
     * 예약을 취소하는 로직
     *
     * @param reservationId 취소할 예약 ID
     */
    public void cancel(Long reservationId) {
        // 1. 예약 조회
        Reservation reservation = reservationRepository.findById(reservationId);
        if (reservation == null) {
            System.out.println("[취소 실패] 존재하지 않는 예약입니다.");
            return;
        }

        // 2. 이미 취소되었는지 확인
        if (reservation.getStatus() == ReservationStatus.CANCELLED) {
            System.out.println("[취소 실패] 이미 취소된 예약입니다.");
            return;
        }

        // 3. 예약 상태 변경 및 저장
        boolean success = reservationRepository.updateStatus(reservationId, ReservationStatus.CANCELLED);

        if (success) {
            // 4. [Observer] 예약 취소 알림
            reservation.setStatus(ReservationStatus.CANCELLED); // 로컬 객체 상태도 변경
            notifyObservers(reservation);
            System.out.println("[취소 성공] 예약(ID: " + reservationId + ")이 취소되었습니다.");
        } else {
            System.out.println("[취소 실패] 알 수 없는 오류");
        }
    }

    /**
     * 나의 예약 목록을 조회하는 로직
     *
     * @param userId 사용자 ID
     * @return 예약 목록
     */
    public List<Reservation> myReservations(Long userId) {
        return reservationRepository.findByUser(userId);
    }
}