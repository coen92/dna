package com.coen92.entity.specification;

public class DaysSinceLastPauseCheck implements Specification<IndividualSubscription> {
    @Override
    public boolean isSatisfiedBy(IndividualSubscription sub) {
        return sub.isEnoughDaysSinceLastPause();
    }
}
