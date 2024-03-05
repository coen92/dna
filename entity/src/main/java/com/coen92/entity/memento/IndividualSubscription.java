package com.coen92.entity.memento;

import com.coen92.entity.subscription.SubscriptionId;
import lombok.AllArgsConstructor;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class IndividualSubscription {
    SubscriptionId id;
    Status status;
    PauseInformation pauseInfo;

    private static final Integer DEFAULT_DAYS_FOR_PAUSE = 10;

    enum Status {Paused, Active, Disabled}

    public void disable() {
        this.status = Status.Disabled;
    }

    public void pause() {
        this.status = Status.Paused;
        this.pauseInfo = PauseInformation.pauseUntil(Instant.now().plus(DEFAULT_DAYS_FOR_PAUSE, ChronoUnit.DAYS));
    }

    @AllArgsConstructor
    private static class PauseInformation {
        String description;
        Instant pausedAt;
        Instant pausedUntil;

        static IndividualSubscription.PauseInformation pauseUntil(Instant until) {
            return new IndividualSubscription.PauseInformation("Subscription paused...", Instant.now(), until);
        }
    }


    // Memento: implementation of Snapshot class to not break Encapsulation of main Entity, yet use the data from variables in f.e. persistence layer
    public IndividualSubscriptionSnapshot snapshot() {
        return new IndividualSubscriptionSnapshot(this.id, this.status, this.pauseInfo);
    }

    public record IndividualSubscriptionSnapshot(SubscriptionId id, Status status, PauseInformation pauseInfo) {
    }
}
