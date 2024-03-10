package com.coen92.domainservice.subscription;

import com.coen92.domainservice.subscription.policy.PausingPolicy;

public interface Subscription {
    Result pause(PausingPolicy policy);
}
