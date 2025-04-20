package org.example.productcatalogservice_feb2025.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.productcatalogservice_feb2025.dtos.ProductDTO;
import org.example.productcatalogservice_feb2025.models.Product;
import org.example.productcatalogservice_feb2025.services.IProductService;
import org.example.productcatalogservice_feb2025.services.ProductServiceStub;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void Test_GetProducts_RunSuccessfully() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Iphone");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Macbook");

        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product2);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Iphone");

        when(productService.getAllProducts()).thenReturn(products);

        ProductDTO productDTO2 = new ProductDTO();
        productDTO2.setId(2L);
        productDTO2.setName("Macbook");

        List<ProductDTO> productDTOs = new ArrayList<>();
        productDTOs.add(productDTO);
        productDTOs.add(productDTO2);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productDTOs)));
    }


    @Test
    public void Test_CreateProducts_RunSuccessfully() throws Exception {
        //Arrange
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(10L);
        productDTO.setPrice(100000D);
        productDTO.setName("SuperComputer");

        Product product = new Product();
        product.setId(10L);
        product.setPrice(100000D);
        product.setName("SuperComputer");

        when(productService.createProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/products").content(objectMapper.writeValueAsString(productDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(productDTO)));

    }

    //Using STUBS
    @Test
    public void testCreateAndGetProduct() {
        IProductService productService = new ProductServiceStub();

        // Create Product
        Product product = new Product();
        product.setName("iPhone");

        Product createdProduct = productService.createProduct(product);
        Long productId = createdProduct.getId();

        // Get Product
        Product fetchedProduct = productService.getProductById(productId);

        // Assertions
        assertNotNull(fetchedProduct);
        assertEquals("iPhone", fetchedProduct.getName());
    }

    @Test
    public void testGetAllProducts() {
        IProductService productService = new ProductServiceStub();

        // Add multiple products
        Product product1 = new Product();
        product1.setName("iPhone");
        productService.createProduct(product1);

        Product product2 = new Product();
        product2.setName("Macbook");
        productService.createProduct(product2);

        // Get all products
        List<Product> products = productService.getAllProducts();

        // Assertions
        assertEquals(2, products.size());
    }

    @Test
    public void testReplaceProduct() {
        IProductService productService = new ProductServiceStub();

        // Create Product
        Product product = new Product();
        product.setName("iPhone");
        product.setPrice(100000D);
        Product createdProduct = productService.createProduct(product);
        Long productId = createdProduct.getId();

        // Replace Product
        Product updatedProduct = new Product();
        updatedProduct.setName("iPhone Pro");
        updatedProduct.setPrice(120000D);
        Product replacedProduct = productService.replaceProduct(updatedProduct, productId);

        // Assertions
        assertNotNull(replacedProduct);
        assertEquals("iPhone Pro", replacedProduct.getName());
        assertEquals(120000D, replacedProduct.getPrice());
    }

    @Test
    public void testDeleteProduct() {
        IProductService productService = new ProductServiceStub();

        // Create Product
        Product product = new Product();
        product.setName("Macbook");
        Product createdProduct = productService.createProduct(product);
        Long productId = createdProduct.getId();

        // Delete Product
        Boolean isDeleted = productService.deleteProduct(productId);
        Product deletedProduct = productService.getProductById(productId);

        // Assertions
        assertTrue(isDeleted);
        assertNull(deletedProduct);
    }

}
