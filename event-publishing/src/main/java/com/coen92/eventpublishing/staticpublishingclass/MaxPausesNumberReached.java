package com.coen92.eventpublishing.staticpublishingclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class MaxPausesNumberReached implements DomainEvent {
    SubscriptionId id;
    Instant reachedAt;
}
