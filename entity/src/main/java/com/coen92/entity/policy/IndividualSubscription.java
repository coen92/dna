package com.coen92.entity.policy;

import lombok.AllArgsConstructor;

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

    @AllArgsConstructor
    private static class PauseInformation {
        String description;
        Instant pausedAt;
        Instant pausedUntil;

        static PauseInformation pauseUntil(Instant until) {
            return new PauseInformation("Subscription paused...", Instant.now(), until);
        }
    }
}
