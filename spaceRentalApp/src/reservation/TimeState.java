package reservation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * 시작 시간과 종료 시간을 담는 클래스
 */
public class TimeState {
    private final LocalDateTime start;
    private final LocalDateTime end;

    public TimeState(LocalDateTime start, LocalDateTime end) {
        if (start.isAfter(end) || start.isEqual(end)) {
            throw new IllegalArgumentException("시작 시간은 종료 시간보다 빨라야 합니다.");
        }
        this.start = start;
        this.end = end;
    }

    // --- Getter ---
    public LocalDateTime getStart() { return start; }
    public LocalDateTime getEnd() { return end; }
    public int getPeriod() {
        LocalDate startDate = this.start.toLocalDate();
        LocalDate endDate = this.end.toLocalDate();

        return (int) ChronoUnit.DAYS.between(startDate, endDate);

    }

    /**
     * 두 시간대(TimeState)가 겹치는지 확인하는 로직
     * @param other 비교할 다른 시간대
     * @return 겹치면 true, 안 겹치면 false
     */
    public boolean overlap(TimeState other) {
        return this.start.isBefore(other.end) && this.end.isAfter(other.start);
    }

    @Override
    public String toString() {
        return "TimeState{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
