package com.coen92.entity.policy;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
class IndividualSubscription {
    Status status;
    PauseInformation pauseInfo;

    enum Status {Paused, Active, Disabled}

    // inject different policies depending on the context in contextual Domain Service implementation
    public void pause(PausingPolicy policy) {
        Instant pauseUntil = policy.pauseEnd();
        this.status = Status.Paused;
        this.pauseInfo = PauseInformation.pauseUntil(pauseUntil);
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
