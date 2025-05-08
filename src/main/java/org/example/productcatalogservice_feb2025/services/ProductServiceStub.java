package org.example.productcatalogservice_feb2025.services;

import java.util.*;
import org.example.productcatalogservice_feb2025.models.Product;

public class ProductServiceStub implements IProductService {
    private final Map<Long, Product> productMap = new HashMap<>();
    private long nextId = 1L;

    @Override
    public Product getProductById(Long id) {
        return productMap.get(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(productMap.values());
    }

    @Override
    public Product createProduct(Product product) {
        product.setId(nextId++);
        productMap.put(product.getId(), product);
        return product;
    }

    @Override
    public Product getProductBasedOnUserRole(Long productId, Long userId) {
        return null;
    }

    @Override
    public Product replaceProduct(Product input, Long id) {
        if (productMap.containsKey(id)) {
            input.setId(id);
            productMap.put(id, input);
            return input;
        }
        return null;
    }

    @Override
    public Boolean deleteProduct(Long id) {
        return productMap.remove(id) != null;
    }
}
