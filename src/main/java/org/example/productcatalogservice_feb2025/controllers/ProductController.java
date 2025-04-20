package org.example.productcatalogservice_feb2025.controllers;

import org.example.productcatalogservice_feb2025.dtos.CategoryDTO;
import org.example.productcatalogservice_feb2025.dtos.FakeStoreProductDTO;
import org.example.productcatalogservice_feb2025.dtos.ProductDTO;
import org.example.productcatalogservice_feb2025.models.Category;
import org.example.productcatalogservice_feb2025.models.Product;
import org.example.productcatalogservice_feb2025.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
//    @Qualifier("sps")
    private IProductService productService;


    @GetMapping
    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> productDTOs  = new ArrayList<>();
        for (Product product : productService.getAllProducts()) {
            productDTOs.add(from(product));
        }

        return productDTOs;
    }

    @GetMapping("/{id}")
    public ProductDTO getProductDetails(@PathVariable Long id) {
        if(id<0) {
            throw new IllegalArgumentException("Please pass product id greater than 0");
        } else if(id==0) {
            throw new IllegalArgumentException("Please pass positive product id");
        }
//        id++;
        Product product = productService.getProductById(id);
        if (product == null) {
            return null;
        }
        return from(product);

    }

    @PatchMapping("/{id}")
    public ProductDTO updateProductDetails(@PathVariable Long id,
                                     @RequestBody ProductDTO productDTO) {
        return null;


    }

    @PutMapping("/{id}")
    public ProductDTO replaceProductDetails(@PathVariable Long id,
                                           @RequestBody ProductDTO productDTO) {
        return from(productService.replaceProduct(to(productDTO),id));
    }

    @PostMapping
    public ProductDTO createProductDetails( @RequestBody ProductDTO productDTO) {
        Product input = to(productDTO);
        Product response = productService.createProduct(input);
        return from(response);

    }

    @DeleteMapping("/{id}")
    public boolean deleteProductDetails(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }


    private ProductDTO from(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setImageURL(product.getImageURL());
        if(product.getCategory()!=null) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(product.getCategory().getId());
            categoryDTO.setName(product.getCategory().getName());
            categoryDTO.setDescription(product.getCategory().getDescription());
            productDTO.setCategory(categoryDTO);
        }
        return productDTO;
    }

    private Product to(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setImageURL(productDTO.getImageURL());
        product.setPrice(productDTO.getPrice());
        if(productDTO.getCategory()!=null) {
            Category category = new Category();
            category.setName(productDTO.getCategory().getName());
            category.setId(productDTO.getCategory().getId());
            category.setDescription(productDTO.getCategory().getDescription());
            product.setCategory(category);
        }


        return product;
    }


}
