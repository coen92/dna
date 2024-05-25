package com.coen92.dna.pruchase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository repository;

    void addProduct(PurchaseId purchaseId, Product product) {
        var purchase = repository.findById(purchaseId);
        if (purchase == null)
            throw new IllegalStateException("No valid purchase found!");
        purchase.addProduct(product);
        repository.save(purchase);
    }

    void removeProduct(PurchaseId purchaseId, Product product) {

    }

    void removeFreeProductIntentionally(PurchaseId purchaseId, Product product) {

    }

    void restoreFreeProduct(PurchaseId purchaseId, Product product) {

    }
}
