package com.coen92.eventpublishing.returnevents;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
public class IndividualSubscription {
    @Getter
    private final SubscriptionId id;
    Status status;

    enum Status {New, Activated, Deactivated, Paused, PastDue}

    public List<DomainEvent> pause() {
        status = Status.Paused;

        // immutable list with an event/events that happened during pause process in Aggregate
        // conscious breaking the CQS rule (Command Query Separation)
        return List.of(new SubscriptionPaused(id, Instant.now()));
    }
}
