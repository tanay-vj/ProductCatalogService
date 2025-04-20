package org.example.productcatalogservice_feb2025.repos;

import org.example.productcatalogservice_feb2025.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {

    Page<Product> findByNameEquals(String name, Pageable pageable);

    Product save(Product product);

    Optional<Product> findById(Long id);

    void deleteById(Long id);

    List<Product> findAll();

    List<Product> findProductByOrderByPriceDesc();

    List<Product> findProductsByPriceBetween(Double from, Double to);

    Boolean existsProductById(Long id);

    @Query("Select p.name from Product p where p.id =?1")
    String findProductNameById(Long id);

    @Query("select c.name from Category c join Product p ON p.category.id= c.id WHERE p.id=:pid")
    String findCategoryNameFromProductId(Long pid);

}
