package org.example.productcatalogservice_feb2025.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.productcatalogservice_feb2025.models.Product;

import java.util.List;

@Getter
@Setter
public class CategoryDTO {
    Long id;
    String name;
    String description;
    List<Product> products;
}
