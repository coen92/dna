package com.coen92.eventpublishing.innercollection.application;

import com.coen92.eventpublishing.innercollection.domain.event.DomainEventPublisher;
import com.coen92.eventpublishing.innercollection.domain.subscription.IndividualSubscriptionRepository;
import com.coen92.eventpublishing.innercollection.domain.subscription.SubscriberId;
import com.coen92.eventpublishing.innercollection.domain.subscription.SubscriptionId;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.coen92.eventpublishing.innercollection.application.Result.failure;
import static com.coen92.eventpublishing.innercollection.application.Result.success;

@Service
@RequiredArgsConstructor
public class PauseIndividualSubscriptionService {

    private final IndividualSubscriptionRepository repository;
    private final DomainEventPublisher eventPublisher;

    @Transactional
    Result pause(SubscriptionId id, SubscriberId subscriberId) {
        try {
            // database transaction start
            var individualSubscription = repository.findBy(id)
                    .orElseThrow(() -> new RuntimeException(STR."Individual subscription of ID: \{id} NOT FOUND"));

            individualSubscription.pause(subscriberId);
            var events = individualSubscription.getChanges();
            eventPublisher.publish(events);
            individualSubscription.clearEvents();

            repository.save(individualSubscription);
            // database transaction commit
            return success();
        } catch (Exception e) {
            return failure();
        }
    }
}
