package com.coen92.domainservice.subscription;

import com.coen92.domainservice.subscription.pause.PauseResult;
import com.coen92.domainservice.subscription.policy.PausingPolicy;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

public class IndividualSubscription implements Subscription {
    @Getter
    private final SubscriptionId id;
    Status status;
    PauseInformation pauseInfo;

    public IndividualSubscription() {
        this.id = new SubscriptionId(UUID.randomUUID());
    }

    enum Status {Paused, Active, Disabled}

    @Override
    public Result pause(PausingPolicy policy) {
        Instant pauseUntil = policy.pauseEnd();
        this.status = Status.Paused;
        this.pauseInfo = PauseInformation.pauseUntil(pauseUntil);
        return PauseResult.execute(this);
    }

    @Getter
    private static class PauseInformation {
        private final String description;
        private final Instant pausedAt;
        private final Instant pausedUntil;

        public PauseInformation(String description, Instant pausedAt, Instant pausedUntil) {
            this.description = description;
            this.pausedAt = pausedAt;
            this.pausedUntil = pausedUntil;
        }

        static PauseInformation pauseUntil(Instant until) {
            return new PauseInformation("Subscription paused...", Instant.now(), until);
        }
    }
}
