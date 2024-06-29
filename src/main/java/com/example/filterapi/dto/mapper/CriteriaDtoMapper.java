package com.example.filterapi.dto.mapper;

import com.example.filterapi.domain.Criteria;
import com.example.filterapi.dto.CriteriaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CriteriaDtoMapper {
    public static List<CriteriaDto> criteriaToDtos(List<Criteria> criteriaList) {
        return criteriaList.stream()
                .map(criteria -> new CriteriaDto(
                        criteria.getCriteriaId(),
                        criteria.getType(),
                        criteria.getCondition(),
                        criteria.getCriteriaValue(),
                        criteria.getSelected()
                )).toList();
    }
}
