package org.example.productcatalogservice_feb2025.TableInheritanceExamples.JoinedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity(name="jc_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    Long id;
}
