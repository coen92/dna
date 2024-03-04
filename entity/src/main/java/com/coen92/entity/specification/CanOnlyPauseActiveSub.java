package com.coen92.entity.specification;

public class CanOnlyPauseActiveSub implements Specification<IndividualSubscription> {
    @Override
    public boolean isSatisfiedBy(IndividualSubscription sub) {
        return sub.isActive();
    }
}
