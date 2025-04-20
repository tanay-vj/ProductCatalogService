package org.example.productcatalogservice_feb2025.services;


import org.example.productcatalogservice_feb2025.dtos.FakeStoreProductDTO;
import org.example.productcatalogservice_feb2025.models.Category;
import org.example.productcatalogservice_feb2025.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fkps")
//@Primary
public class FakeStoreProductService implements IProductService{

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Override
    public Product getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTO =
                restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",
                        FakeStoreProductDTO.class, id);
        if (fakeStoreProductDTO.getBody()!=null &&
                fakeStoreProductDTO.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
            return from(fakeStoreProductDTO.getBody());
        }
        return null;

    }

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate =restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDTO[]> response =
                restTemplate.getForEntity("https://fakestoreapi.com/products",
                        FakeStoreProductDTO[].class);
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDTO fakeStoreProductDTO : response.getBody()) {
            products.add(from(fakeStoreProductDTO));
        }

        return products;
    }

    @Override
    public Product replaceProduct(Product product, Long id) {
        FakeStoreProductDTO input = from(product);
        FakeStoreProductDTO output = requestForEntity("https://fakestoreapi.com/products/{id}",
                HttpMethod.PUT, input, FakeStoreProductDTO.class, id).getBody();

        return from(output);
    }

    @Override
    public Boolean deleteProduct(Long id) {

        ResponseEntity<Void> response = requestForEntity("https://fakestoreapi.com/products/{id}",
                HttpMethod.DELETE,null, Void.class, id);

        return response.getStatusCode().is2xxSuccessful();

    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDTO input = from(product);
        FakeStoreProductDTO output = requestForEntity("https://fakestoreapi.com/products",
                HttpMethod.POST, input, FakeStoreProductDTO.class).getBody();

        return from(output);
    }

    public <T> ResponseEntity<T> requestForEntity(String url, HttpMethod httpMethod, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate =restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    //Mapper for PUT request
    private FakeStoreProductDTO from(Product product) {
        //Need to implement
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setId(product.getId());
        fakeStoreProductDTO.setTitle(product.getName());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setCategory(product.getCategory().getName());

        fakeStoreProductDTO.setImage(product.getImageURL());
        return fakeStoreProductDTO;
    }





    //Mapper for GET request
    private Product from(FakeStoreProductDTO fakeStoreProductDTO) {
        Product product = new Product();
        product.setId(fakeStoreProductDTO.getId());
        product.setName(fakeStoreProductDTO.getTitle());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setImageURL(fakeStoreProductDTO.getImage());

        Category category = new Category();
        category.setName(fakeStoreProductDTO.getCategory());
        product.setCategory(category);
        return product;
    }
}
