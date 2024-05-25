package com.coen92.dna.pruchase;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PurchaseServiceTest {
    private static final PurchaseId PURCHASE_ID = new PurchaseId(UUID.randomUUID());
    private final Product CODING_SUB = new Product("CODING SUB");
    private final Product DDD_BOOK = new Product("DDD BOOK");

    PurchaseRepository repository = new PurchaseRepositoryImpl();
    ExtraProductPolicy.BuyOneGetOtherFreePolicy policy = new ExtraProductPolicy.BuyOneGetOtherFreePolicy();
    PurchaseService service = new PurchaseService(repository, policy);

    @AfterEach
    void cleanup() {
        repository.deleteAll();
    }

    @Test
    void can_add_product_to_existing_purchase() {
        // given
        var purchase = new Purchase(PURCHASE_ID);
        repository.save(purchase);

        // when
        service.addProduct(PURCHASE_ID, CODING_SUB);

        // then
        assertEquals("{CODING SUB=1}", purchase.printView());
    }

    @Test
    void cannot_add_product_to_non_existing_purchase() {
        // given
        var purchase = new Purchase(PURCHASE_ID);

        // when
        Executable result = () -> service.addProduct(PURCHASE_ID, CODING_SUB);

        // then
        assertThrows(IllegalStateException.class, result, "No valid purchase found!");
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
        assertEquals("{}", purchase.printView());
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
        assertEquals("{CODING SUB=2}", purchase.printView());
    }

    @Test
    void buy_one_subscription_and_get_free_ebook() {
        // given
        var purchase = new Purchase(PURCHASE_ID);
        repository.save(purchase);
        // and
        policy.addNewExtraProduct(new ExtraProductPolicy.ExtraProduct(CODING_SUB, DDD_BOOK));

        // when
        service.addProduct(PURCHASE_ID, CODING_SUB);

        // then
        assertEquals("{CODING SUB=1, [FREE] DDD BOOK=1}", purchase.printView());
    }

    @Test
    void removing_subscription_removes_free_ebook() {
        // given
        var purchase = new Purchase(PURCHASE_ID);
        repository.save(purchase);
        // and
        policy.addNewExtraProduct(new ExtraProductPolicy.ExtraProduct(CODING_SUB, DDD_BOOK));

        // when
        service.addProduct(PURCHASE_ID, CODING_SUB);
        service.removeProduct(PURCHASE_ID, CODING_SUB);

        // then
        assertEquals("{}", purchase.printView());
    }

    @Test
    void can_remove_free_ebook_but_keep_subscription() {
        // given
        var purchase = new Purchase(PURCHASE_ID);
        repository.save(purchase);
        // and
        policy.addNewExtraProduct(new ExtraProductPolicy.ExtraProduct(CODING_SUB, DDD_BOOK));
        service.addProduct(PURCHASE_ID, CODING_SUB);

        // when
        service.removeFreeProductIntentionally(PURCHASE_ID, DDD_BOOK);

        // then
        assertEquals("{CODING SUB=1}", purchase.printView());
    }
}
