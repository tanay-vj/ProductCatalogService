package org.example.productcatalogservice_feb2025.repos;

import jakarta.transaction.Transactional;
import org.example.productcatalogservice_feb2025.models.Category;
import org.example.productcatalogservice_feb2025.models.Product;
import org.example.productcatalogservice_feb2025.models.Scope;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepoTest {

    @Autowired
    private  ProductRepo productRepo;

    @Test
    public void insertDummyDataIntoDB() {
        Product product = new Product();
        product.setId(92L);
        product.setName("Iphone15");
        product.setPrice(100000D);
        product.setScope(Scope.UNLISTED);
        Category category = new Category();
        category.setId(20L);
        category.setName("smartphones");
        product.setCategory(category);


        productRepo.save(product);
    }

    @Test
    @Transactional
    public void testQueries() {

//        List<Product> productList = productRepo.findProductByOrderByPriceDesc();
//
//        for(Product product : productList) {
//            System.out.println(product.getName());
//        }

//        List<Product> productList = productRepo.findProductsByPriceBetween(500.0,80000.0);
//
//        for(Product product : productList) {
//            System.out.println(product.getName());
//        }

//        System.out.println(productRepo.findProductNameById(1l));
        System.out.println(productRepo.findCategoryNameFromProductId(1l));
//        System.out.println(productRepo.existsProductById(1L));

    }

}