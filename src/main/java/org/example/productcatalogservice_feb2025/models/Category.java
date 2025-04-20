package org.example.productcatalogservice_feb2025.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel implements Serializable {
    Long id;
    String name;
    String description;
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 20)
//    @JsonBackReference
    @JsonManagedReference
    List<Product> products;
}
