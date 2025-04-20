package org.example.productcatalogservice_feb2025.TableInheritanceExamples.JoinedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "jc_ta")
@PrimaryKeyJoinColumn(name = "user_id")
public class TA extends User {

    int numOfHelpRequests;
}
