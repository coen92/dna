package com.coen92.entity.specification;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

class IndividualSubscription {
    Status status;
    Instant pausedAt;

    // invariants through individual SPECIFICATIONS
    private final Specification<IndividualSubscription> pauseSpec;
    private final Specification<IndividualSubscription> daysToPauseSpec;
    private static final Integer DAYS_TO_PAUSE = 10;

    IndividualSubscription() {
        this.daysToPauseSpec = new DaysSinceLastPauseCheck();
        this.pauseSpec = new CanOnlyPauseActiveSub();
    }

    public void pause() {
        if (!pauseSpec.isSatisfiedBy(this)) {
            throw new IllegalStateException("Pausing rules not satisfied!");
        }
        if (!daysToPauseSpec.isSatisfiedBy(this)) {
            throw new IllegalStateException(STR."Can't pause before \{DAYS_TO_PAUSE} days gone by since last pause!");
        }
        this.status = Status.Paused;
        this.pausedAt = Instant.now();
    }

    boolean isEnoughDaysSinceLastPause() {
        return pausedAt.isBefore(Instant.now().minus(DAYS_TO_PAUSE, ChronoUnit.DAYS));
    }

    enum Status {Paused, Active, Disabled}

    boolean isActive() {
        return this.status == Status.Active;
    }
}
