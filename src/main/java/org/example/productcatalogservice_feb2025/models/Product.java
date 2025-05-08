package org.example.productcatalogservice_feb2025.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product extends BaseModel implements Serializable {
    Long id;
    String name;
    String description;
    String imageURL;
    Double price;
    @ManyToOne(cascade = CascadeType.ALL)
//    @JsonManagedReference
    @JsonBackReference
    Category category;
    boolean isPrime;
    Scope scope;


    public Product() {
        this.setCreatedAt(new Date());
        this.setLastUpdatedAt(new Date());
        this.setState(State.ACTIVE);
    }
}
