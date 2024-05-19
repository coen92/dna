package com.coen92.eventpublishing.staticpublishingclass;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class SubscriptionPaused implements DomainEvent {
    private final SubscriptionId id;
    Instant pausedAt;
    SubscriberId subscriberId;
}
