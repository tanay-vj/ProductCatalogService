package org.example.productcatalogservice_feb2025.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDTO {
    private Long id;
    private String emailId;
    private Role role;
}
