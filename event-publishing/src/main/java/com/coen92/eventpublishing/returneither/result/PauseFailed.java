package com.coen92.eventpublishing.returneither.result;

import com.coen92.eventpublishing.returneither.EventId;
import com.coen92.eventpublishing.returneither.SubscriptionId;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class PauseFailed extends Failure<SubscriptionId> {
    private final Instant failedAt;

    public PauseFailed(Throwable cause, SubscriptionId subscriptionId) {
        super(new EventId(UUID.randomUUID()), cause, subscriptionId);
        this.failedAt = Instant.now();
    }

    @Override
    public SubscriptionId get() {
        return this.failure;
    }
}
