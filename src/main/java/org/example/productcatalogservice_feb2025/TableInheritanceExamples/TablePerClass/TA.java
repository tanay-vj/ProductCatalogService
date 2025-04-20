package org.example.productcatalogservice_feb2025.TableInheritanceExamples.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name = "tpc_ta")
public class TA extends User{

    int numOfHelpRequests;
}
