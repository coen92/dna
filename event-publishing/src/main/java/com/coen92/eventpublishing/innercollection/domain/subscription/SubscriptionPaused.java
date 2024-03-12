package com.coen92.eventpublishing.innercollection.domain.subscription;

import com.coen92.eventpublishing.innercollection.domain.event.DomainEvent;

import java.time.Instant;

class SubscriptionPaused implements DomainEvent {
    public SubscriptionPaused(SubscriptionId id, Instant pausedAt, SubscriberId subscriberId) {
    }
}
