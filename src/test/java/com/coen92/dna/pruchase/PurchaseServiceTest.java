package com.coen92.dna.pruchase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.UUID;

class PurchaseServiceTest {
    private static final PurchaseId PURCHASE_ID = new PurchaseId(UUID.randomUUID());
    private final Product CODING_SUB = new Product("CODING SUB");
    private final Product DDD_BOOK = new Product("DDD BOOK");

    PurchaseRepository repository = new PurchaseRepositoryImpl();
    PurchaseService service = new PurchaseService(repository);

    @Test
    void can_add_product_to_existing_purchase() {
        // given
        var purchase = new Purchase(PURCHASE_ID);
        repository.save(purchase);

        // when
        service.addProduct(PURCHASE_ID, CODING_SUB);

        // then
        Assertions.assertEquals("{CODING SUB=1}", purchase.printView());
    }

    @Test
    void cannot_add_product_to_non_existing_purchase() {
        // given
        var purchase = new Purchase(PURCHASE_ID);

        // when
        Executable result = () -> service.addProduct(PURCHASE_ID, CODING_SUB);

        // then
        Assertions.assertThrows(IllegalStateException.class, result, "No valid purchase found!");
    }

    @Test
    void can_remove_a_subscription() {
        // given
        var purchase = new Purchase(PURCHASE_ID);
        repository.save(purchase);

        // and
        service.addProduct(PURCHASE_ID, CODING_SUB);

        // when
        service.removeProduct(PURCHASE_ID, CODING_SUB);

        // then
        Assertions.assertEquals("{}", purchase.printView());
    }

    @Test
    void can_add_two_same_subscription() {
        // given
        var purchase = new Purchase(PURCHASE_ID);
        repository.save(purchase);

        // when
        service.addProduct(PURCHASE_ID, CODING_SUB);
        // and
        service.addProduct(PURCHASE_ID, CODING_SUB);

        // then
        Assertions.assertEquals("{CODING SUB=2}", purchase.printView());
    }
}
