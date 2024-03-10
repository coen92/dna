package com.coen92.domainservice.subscription;

import lombok.Getter;

import java.util.UUID;

@Getter
public final class SubscriptionId {
    private final UUID id;

    private SubscriptionId(UUID id) {
        this.id = id;
    }

    public static SubscriptionId of(UUID id) {
        return new SubscriptionId(id);
    }
}
