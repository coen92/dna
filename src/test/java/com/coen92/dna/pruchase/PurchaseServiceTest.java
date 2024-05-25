package com.coen92.dna.pruchase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class PurchaseServiceTest {
    private static final PurchaseId PURCHASE_ID = new PurchaseId(UUID.randomUUID());
    private final Product CODING_SUB = new Product("CODING SUB");
    private final Product DDD_BOOK = new Product("DDD BOOK");

    PurchaseRepository repository = new PurchaseRepositoryImpl();
    PurchaseService service = new PurchaseService(repository);

    @Test
    void can_add_product() {
        // given
        var purchase = new Purchase(PURCHASE_ID);

        // when
        service.addProduct(PURCHASE_ID, CODING_SUB);

        // then
        Assertions.assertEquals("{CODING SUB=1}", purchase.printView());
    }
}
