package com.coen92.domainservice.subscription.policy;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class StandardPausingPolicy implements PausingPolicy {
    @Override
    public Instant pauseEnd() {
        return Instant.now().plus(3, ChronoUnit.DAYS);
    }
}
