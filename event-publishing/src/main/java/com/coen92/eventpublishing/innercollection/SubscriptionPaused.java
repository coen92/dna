package com.coen92.eventpublishing.innercollection;

import java.time.Instant;

class SubscriptionPaused implements DomainEvent {
    public SubscriptionPaused(SubscriptionId id, Instant pausedAt, SubscriberId subscriberId) {
    }
}
