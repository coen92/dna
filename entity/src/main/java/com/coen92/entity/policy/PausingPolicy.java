package com.coen92.entity.policy;

import java.time.Instant;

public interface PausingPolicy {
    Instant pauseEnd();
}
