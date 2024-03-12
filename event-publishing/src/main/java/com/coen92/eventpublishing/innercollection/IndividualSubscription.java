package com.coen92.eventpublishing.innercollection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@RequiredArgsConstructor
class IndividualSubscription extends Entity {
    @Getter
    private final boolean disabled;
    @Getter
    private final SubscriptionId id;
    Status status;

    enum Status { New, Activated, Deactivated, Paused, PastDue }

    public void pause(SubscriberId subscriberId) { // command - Blue Card
        if (isDisabled()) { // business rule - Yellow Card
            throw new UnsupportedOperationException();
        }
        status = Status.Paused;
        raise(new SubscriptionPaused(id, Instant.now(), subscriberId)); // domain event - Orange Card
    }
}
