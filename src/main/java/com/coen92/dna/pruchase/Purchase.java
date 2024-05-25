package com.coen92.dna.pruchase;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.StringTemplate.STR;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

// this is our aggregate
@EqualsAndHashCode
class Purchase {
    final PurchaseId id;
    @Getter
    private List<Product> products = new ArrayList<>();
    @Getter
    private List<Product> freeProducts = new ArrayList<>();

    public Purchase(PurchaseId id) {
        this.id = id;
    }

    public String printView() {
        Stream<String> products = this.products.stream()
                .map(Product::getName)
                .sorted();

        Stream<String> extra = this.freeProducts.stream()
                .map(free -> STR."[FREE] \{free.getName()}")
                .sorted();

        return Stream.concat(products, extra)
                .collect(groupingBy(identity(), counting()))
                .toString();
    }

    public void addProduct(Product product, ExtraProductPolicy policy) {
        products.add(product);
        policy.getExtraProductsFor(product)
                .forEach(extra -> freeProducts.add(extra));
    }

    public void removeProduct(Product product, ExtraProductPolicy policy) {
        products.remove(product);
        policy.getExtraProductsFor(product)
                .forEach(extra -> freeProducts.remove(extra));
    }

    public void intentionallyRemoveFreeProduct(Product product) {
        // assert product belongs to free products etc.
        freeProducts.remove(product);
    }
}
