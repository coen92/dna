package com.coen92.eventpublishing.staticpublishingclass;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@RequiredArgsConstructor
public class IndividualSubscription {
    @Getter
    private final SubscriptionId id;
    Status status;
    int numOfPauses;

    enum Status {New, Activated, Deactivated, Paused, PastDue}
    private static final int MAX_NUM_OF_PAUSES = 5;

    public void pause(SubscriberId subscriberId) {
        // rules
        this.status = Status.Paused;
        DomainEvents.publish(new SubscriptionPaused(id, Instant.now(), subscriberId));

        if(maxPausedReached()) {
            DomainEvents.publish(new MaxPausesNumberReached(id, Instant.now()));
        }
    }

    private boolean maxPausedReached() {
        return numOfPauses > MAX_NUM_OF_PAUSES;
    }
}
