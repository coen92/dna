package com.coen92.entity.subscription;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
class IndividualSubscription {
    @Getter
    private final SubscriptionId id;
    Status status;
    PauseInformation pauseInfo;
    DisableReason disableReason;

    private IndividualSubscription(SubscriptionId id) {
        this.id = id;
    }

    enum Status {Paused, Active, Disabled}

    // no getters, no setters -> mutability through methods following business rules
    // LIFE-CYCLE INVARIANTS
    public void disable(DisableReason reason) {
        if (isPaused()) {
            throw new IllegalStateException(STR."Not allowed to disable \{this.status.name().toLowerCase()} subscriptions!");
        }
        status = Status.Disabled;
        disableReason = reason;
    }

    public void activate() {
        status = Status.Active;
    }

    public void pause() {
        if (isDisabled()) {
            throw new IllegalStateException(STR."Not allowed to pause \{this.status.name().toLowerCase()} subscriptions!");
        }
        status = Status.Paused;
        pauseInfo = pauseInfo.now();
    }
    // if logic of above methods expands too much => EXTRACT to three classes: PausedSubscription, ActiveSubscription, DisabledSubscription


    private boolean isDisabled() {
        return this.status == Status.Disabled;
    }

    private boolean isPaused() {
        return this.status == Status.Paused;
    }

    @Getter
    private static class PauseInformation {
        private final String description;
        private final Instant pausedAt;

        public PauseInformation(String description, Instant pausedAt) {
            this.description = description;
            this.pausedAt = pausedAt;
        }

        PauseInformation now() {
            return new PauseInformation("Subscription paused...", Instant.now());
        }
    }

    enum DisableReason {
        BLOCKED,
        NO_PAYMENT,
        MALFORMED_USER

    }
}
