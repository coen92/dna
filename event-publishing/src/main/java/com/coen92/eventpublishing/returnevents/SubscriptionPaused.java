package com.coen92.eventpublishing.returnevents;


import java.time.Instant;

class SubscriptionPaused implements DomainEvent {
    public SubscriptionPaused(SubscriptionId id, Instant pausedAt) {
    }
}
