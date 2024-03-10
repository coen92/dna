package com.coen92.domainservice.subscription.policy;

import java.time.Instant;

public interface PausingPolicy {
    Instant pauseEnd();
}
