package org.example.productcatalogservice_feb2025.TableInheritanceExamples.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "st_instructor")
@DiscriminatorValue(value = "2")
public class Instructor extends User {
    String company;
}
