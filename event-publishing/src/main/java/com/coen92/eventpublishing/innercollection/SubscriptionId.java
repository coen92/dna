package com.coen92.eventpublishing.innercollection;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "id")
final class SubscriptionId {
    private final UUID id;
    public SubscriptionId(UUID id) {
        this.id = id;
    }
}

