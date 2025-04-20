package org.example.productcatalogservice_feb2025.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortParam {
    private String paramName;
    private SortType sortType;
}
