package com.coen92.domainservice.subscription;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public final class SubscriptionId {
    private final UUID id;
}
