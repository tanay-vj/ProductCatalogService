package org.example.productcatalogservice_feb2025.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.example.productcatalogservice_feb2025.models.Category;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
    Long id;
    String name;
    String description;
    String imageURL;
    Double price;
    CategoryDTO category;
}
