package com.coen92.eventpublishing.innercollection;

import lombok.Getter;

import java.util.UUID;

@Getter
final class SubscriberId {
    private final UUID id;
    private SubscriberId(UUID id) {
        this.id = id;
    }

    public static SubscriberId of(UUID id) {
        return new SubscriberId(id);
    }
}

