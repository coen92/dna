package com.coen92.subscriptionmodule.model;

import com.coen92.subscriptionmodule.Result;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.Instant;

@NoArgsConstructor
public class Subscription {
    // aggregate shouldn't contain required data but rules for business processes
    private Status status = Status.New;
    private int availablePauses = 2;
    private Instant lastPause = null;

    enum Status { New, Activated, Deactivated, Paused, PastDue }

    Result activate(DomainEvent event) {
        status = Status.Activated;
        return Result.success(event);
    }

    Result deactivate(DomainEvent event) {
        status = Status.Deactivated;
        return Result.success(event);
    }

    Result pause(DomainEvent event) {
        return pause(event, Instant.now());
    }

    Result pause(DomainEvent event, Instant when) {
        if (isActive() && enoughDaysSinceLastPause(when) && anyPauseAvailable()) {
            availablePauses--;
            lastPause = when;
            status = Status.Paused;
            return Result.success(event);
        }
        return Result.failure(event);
    }

    Result resume(DomainEvent event) {
        if (isPaused()) {
            status = Status.Activated;
            return Result.success(event);
        }
        return Result.failure(event);
    }

    Result markAsPastDue(DomainEvent event) {
        status = Status.PastDue;
        return Result.success(event);
    }

    private boolean anyPauseAvailable() {
        return availablePauses > 0;
    }

    private boolean enoughDaysSinceLastPause(Instant when) {
        if (lastPause == null) {
            return true;
        }
        return Duration.between(lastPause, when).toDays() >= 10;
    }

    private boolean isActive() {
        return status == Status.Activated;
    }

    private boolean isPaused() {
        return status == Status.Paused;
    }
}
