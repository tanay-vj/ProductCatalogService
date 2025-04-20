package org.example.productcatalogservice_feb2025.TableInheritanceExamples.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "st_ta")
@DiscriminatorValue(value = "0")
public class TA extends User {

    int numOfHelpRequests;
}
