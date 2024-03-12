package com.coen92.eventpublishing.innercollection.domain.subscription;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IndividualSubscriptionRepository {

    // implementation in particular classes of Infrastructure layer
    Optional<IndividualSubscription> findBy(SubscriptionId id);

    void save(IndividualSubscription individualSubscription);
}
