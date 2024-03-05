package com.coen92.entity.policy;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

class PremiumSubscriberPausingPolicy implements PausingPolicy {
    @Override
    public Instant pauseEnd() {
        return Instant.now().plus(30, ChronoUnit.DAYS);
    }
}
