package com.coen92.eventpublishing.returneither.result;

import com.coen92.eventpublishing.returneither.EventId;
import com.coen92.eventpublishing.returneither.SubscriptionId;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class SubscriptionPaused extends Success<SubscriptionId> {
    Instant pausedAt;

    public SubscriptionPaused(SubscriptionId subscriptionId) {
        super(new EventId(UUID.randomUUID()), subscriptionId);
        this.pausedAt = Instant.now();
    }

    @Override
    public SubscriptionId get() {
        return this.success;
    }
}
