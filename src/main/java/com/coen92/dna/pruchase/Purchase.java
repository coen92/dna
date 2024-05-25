package com.coen92.dna.pruchase;

import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

// this is our aggregate
@EqualsAndHashCode
class Purchase {
    final PurchaseId id;
    private List<Product> products = new ArrayList<>();

    public Purchase(PurchaseId id) {
        this.id = id;
    }

    public String printView() {
        return products.stream()
                .map(Product::getName)
                .sorted()
                .collect(groupingBy(identity(), counting()))
                .toString();
    }

    public void addProduct(Product product) {
        products.add(product);
    }
}
