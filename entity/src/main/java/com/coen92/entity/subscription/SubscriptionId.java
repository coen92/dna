package com.coen92.entity.subscription;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "id")
public final class SubscriptionId {
    private final UUID id;
    public SubscriptionId(UUID id) {
        this.id = id;
    }
}
