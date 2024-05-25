package com.coen92.dna.pruchase;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
interface PurchaseRepository {
    Purchase findById(PurchaseId id);
    void save(Purchase purchase);
    void deleteAll();
}

class PurchaseRepositoryImpl implements PurchaseRepository {
    private final Map<PurchaseId, Purchase> purchases = new HashMap<>();

    @Override
    public Purchase findById(PurchaseId id) {
        return purchases.get(id);
    }

    @Override
    public void save(Purchase purchase) {
        purchases.put(purchase.id, purchase);
    }

    @Override
    public void deleteAll() {
        purchases.clear();
    }
}
