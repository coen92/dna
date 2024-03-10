package com.coen92.domainservice.subscription.policy;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class PremiumPausingPolicy implements PausingPolicy {
    @Override
    public Instant pauseEnd() {
        return Instant.now().plus(30, ChronoUnit.DAYS);
    }
}
