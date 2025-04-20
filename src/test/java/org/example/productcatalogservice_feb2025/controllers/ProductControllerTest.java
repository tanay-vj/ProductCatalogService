package org.example.productcatalogservice_feb2025.controllers;

import org.example.productcatalogservice_feb2025.dtos.ProductDTO;
import org.example.productcatalogservice_feb2025.models.Product;
import org.example.productcatalogservice_feb2025.services.IProductService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {

    @MockitoBean
    private IProductService productService;

    @Autowired
    private ProductController productController;

    @Captor
    private ArgumentCaptor<Long> idCaptor;


    @Test
    public void TestGetProductDetailsById_WithValidProductId_RunSuccessfully() {
        //Arrange
        Long id  = 3L;
        Product product = new Product();
        product.setId(3L);
        product.setName("Iphone");
        product.setPrice(100000D);
        when(productService.getProductById(id)).thenReturn(product);

        //Act
        ProductDTO productDto = productController.getProductDetails(id);

        //Assert
        assertNotNull(productDto);
        assertEquals(productDto.getName(),"Iphone");
        assertEquals(productDto.getId(),id);
        assertEquals(productDto.getPrice(),100000D);
    }

    @Test
    public void TestGetProductDetailsById_WithNegativeProductId_ResultsIllegalArgumentException() {
        Long id = -1L;

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> productController.getProductDetails(id));

        //Assert
        assertEquals("Please pass product id greater than 0", exception.getMessage());
        verify(productService,times(0)).getProductById(id);

    }

    @Test
    public void TestGetProductDetailsById_ProductServiceCalledWithCorrectArguments_RunSuccessfully() {
        //Arrange
        Long productId = 5L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Macbook");
        when(productService.getProductById(productId)).thenReturn(product);

        //Act
        productController.getProductDetails(productId);

        //Assert
        verify(productService).getProductById(idCaptor.capture());
        assertEquals(productId, idCaptor.getValue());
    }


    //Checking DELETE method
    @Test
    public void TestDeleteProductById_WithValidProductId_ShouldReturnTrue() {
        // Arrange
        Long id = 3L;
        when(productService.deleteProduct(id)).thenReturn(true);

        // Act
        boolean result = productController.deleteProductDetails(id);

        // Assert
        assertTrue(result);
        verify(productService, times(1)).deleteProduct(id);
    }

    //Checking CREATE product
    @Test
    public void testCreateProduct_WithValidProductDetails_ShouldReturnProduct() {
        // Arrange
        Long id = 3L;
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(id);
        productDTO.setName("Iphone");
        productDTO.setPrice(100000D);

        Product product = new Product();
        product.setId(id);
        product.setName("Iphone");
        product.setPrice(100000D);

        when(productService.createProduct(any(Product.class))).thenReturn(product);

        // Act
        ProductDTO responseDto = productController.createProductDetails(productDTO);

        // Assert
        assertNotNull(responseDto);
        assertEquals("Iphone", responseDto.getName());
        assertEquals(id, responseDto.getId());
        assertEquals(100000D, responseDto.getPrice());

        verify(productService, times(1)).createProduct(any(Product.class));
    }

    //Checking for REPLACE product
    @Test
    public void testReplaceProduct_WithValidProductDetails_ShouldReturnProduct() {
        // Arrange
        Long id = 3L;

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(id);
        productDTO.setName("Iphone");
        productDTO.setPrice(99100D);

        Product product = new Product();
        product.setId(id);
        product.setName("Iphone");
        product.setPrice(99100D);

        when(productService.replaceProduct(any(Product.class), eq(id))).thenReturn(product);
        //Mockito expects either all arguments to be exact values or all to be matchers.
        //If you mix any(Product.class) (a matcher) with a raw id (a literal), Mockito may not correctly match the function call.
        // Act
        ProductDTO responseDto = productController.replaceProductDetails(id, productDTO);

        // Assert
        assertNotNull(responseDto);
        assertEquals("Iphone", responseDto.getName());
        assertEquals(id, responseDto.getId());
        assertEquals(99100D, responseDto.getPrice());

        verify(productService, times(1)).replaceProduct(any(Product.class), eq(id));
    }

    @Test
    public void TestGetAllProducts_WithValidProductId_ShouldReturnAllProducts() {
        //Arrange
        Long productId = 5L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Macbook");

        when(productService.getAllProducts()).thenReturn(List.of(product));

        //Act
        List<ProductDTO> productDTOs = productController.getAllProducts();

        // Assert
        verify(productService, times(1)).getAllProducts();
        assertNotNull(productDTOs);
        assertEquals(1, productDTOs.size());
        assertEquals("Macbook", productDTOs.get(0).getName());
        assertEquals(productId, productDTOs.get(0).getId());

    }

}