package com.coen92.subscriptionmodule.model;

import com.coen92.subscriptionmodule.Result;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
public class Subscription {
    // aggregate shouldn't contain required data but rules for business processes
    private Status status = Status.New;
    private final Pauses pauses = new Pauses();
    enum Status { New, Activated, Deactivated, Paused, PastDue }

    // business processes are reflected in methods driven by domain events like below
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
        if (isActive() && pauses.canPauseAt(when)) {
            pauses.markNewPauseAt(when);
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

    private boolean isActive() {
        return status == Status.Activated;
    }

    private boolean isPaused() {
        return status == Status.Paused;
    }
}
