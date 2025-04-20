package org.example.productcatalogservice_feb2025.repos;

import jakarta.transaction.Transactional;
import org.example.productcatalogservice_feb2025.models.Category;
import org.example.productcatalogservice_feb2025.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepoTest {

    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    @Transactional
    public void testFetchTypes() {
        Category category = categoryRepo.findById(2L).get();
        System.out.println(category.getName());
//        for(Product product : category.getProducts()) {
//            System.out.println(product.getName());
//        }
    }

    //BatchType with SELECT is always better than SUBSELECT for N+1 queries

    @Test
    @Transactional
    public void testSomething() {
        List<Category> categoryList = categoryRepo.findAll();
        for (Category category : categoryList) {
            System.out.println("CategoryName="+category.getName());
            for (Product product : category.getProducts()) {
                System.out.print(product.getName()+", ");
            }
            System.out.println();
        }
    }

}