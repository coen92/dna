package com.coen92.dna.pruchase;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public interface ExtraProductPolicy {
    Set<Product> getExtraProductsFor(Product product);

    void addNewExtraProduct(ExtraProduct product);

    @Getter
    class BuyOneGetOtherFreePolicy implements ExtraProductPolicy {
        private Set<ExtraProduct> extraProducts = new HashSet<>();

        @Override
        public Set<Product> getExtraProductsFor(Product product) {
            return extraProducts.stream()
                    .filter(ep -> ep.isFreeFor(product))
                    .map(ep -> ep.freeProduct)
                    .collect(Collectors.toSet());
        }

        @Override
        public void addNewExtraProduct(ExtraProduct product) {
            extraProducts.add(product);
        }
    }


    @AllArgsConstructor
    class ExtraProduct {
        final Product forProduct;
        final Product freeProduct;


        public boolean isFreeFor(Product product) {
            return product.equals(forProduct);
        }
    }
}
