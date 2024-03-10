package com.coen92.domainservice.subscription;

import com.coen92.domainservice.subscription.pause.PauseResult;
import com.coen92.domainservice.subscription.policy.PausingPolicy;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class IndividualSubscription implements Subscription {
    private final SubscriptionId id;
    Status status;
    PauseInformation pauseInfo;

    enum Status {Paused, Active, Disabled}

    private IndividualSubscription() {
        this.id = SubscriptionId.of(UUID.randomUUID());
    }

    private IndividualSubscription(Status status, PauseInformation pauseInfo) {
        this.id = SubscriptionId.of(UUID.randomUUID());
        this.status = status;
        this.pauseInfo = pauseInfo;
    }

    public static IndividualSubscription newSub() {
        return new IndividualSubscription(Status.Active, null);
    }


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
