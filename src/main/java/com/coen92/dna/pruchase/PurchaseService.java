package com.coen92.dna.pruchase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseRepository repository;
    // it would be reasonable for multiple policies to use PolicyFactory here
    private final ExtraProductPolicy policy;

    void addProduct(PurchaseId purchaseId, Product product) {
        var purchase = repository.findById(purchaseId);
        if (purchase == null)
            throw new IllegalStateException("No valid purchase found!");
        purchase.addProduct(product, policy);
        repository.save(purchase);
    }

    void removeProduct(PurchaseId purchaseId, Product product) {
        var purchase = repository.findById(purchaseId);
        if (purchase == null)
            throw new IllegalStateException("No valid purchase found!");
        if (!purchase.getProducts().contains(product)) {
            log.warn("No product of this kind in the purchase!");
            return;
        }
        purchase.removeProduct(product, policy);
        repository.save(purchase);
    }

    void removeFreeProductIntentionally(PurchaseId purchaseId, Product product) {
        var purchase = repository.findById(purchaseId);
        if (purchase == null)
            throw new IllegalStateException("No valid purchase found!");
        purchase.intentionallyRemoveFreeProduct(product);
        repository.save(purchase);
    }

    void restoreFreeProduct(PurchaseId purchaseId, Product product) {

    }
}
