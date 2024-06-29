package com.example.filterapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilterDto {
    private Long filterId;
    private String name;
    private List<CriteriaDto> criteriaDtoList;
}
