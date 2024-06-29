package com.example.filterapi.dto.mapper;

import com.example.filterapi.domain.Filter;
import com.example.filterapi.dto.CriteriaDto;
import com.example.filterapi.dto.FilterDto;

import java.util.List;

public class FilterDtoMapper {

    public static FilterDto filterToDto(Filter entity) {
        return FilterDto.builder()
                .filterId(entity.getFilterId())
                .name(entity.getName())
                .criteriaDtoList(getFilterCriteriaDtos(entity))
                .build();
    }

    private static List<CriteriaDto> getFilterCriteriaDtos(Filter entity) {
        return entity.getFilterCriteria() != null ? CriteriaDtoMapper.criteriaToDtos(entity.getFilterCriteria()) : null;
    }
}
