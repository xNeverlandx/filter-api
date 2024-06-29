package com.example.filterapi.service;

import com.example.filterapi.domain.Criteria;
import com.example.filterapi.domain.Filter;
import com.example.filterapi.dto.CriteriaDto;
import com.example.filterapi.dto.FilterDto;
import com.example.filterapi.repository.CriteriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CriteriaService {
    private final CriteriaRepository criteriaRepository;

    public void updateCriteria(Filter filter, FilterDto filterDto) {
        List<Criteria> softDeleteCriteria = softDeleteCriteria(filter.getFilterCriteria(), filterDto.getCriteriaDtoList());
        filter.setFilterCriteria(softDeleteCriteria);

        List<Criteria> newCriteria = addNewCriteria(filterDto.getCriteriaDtoList(), filter);
        filter.getFilterCriteria().addAll(newCriteria);

        List<Criteria> updateCriteria = updateCriteria(filter.getFilterCriteria(), filterDto.getCriteriaDtoList());
        filter.setFilterCriteria(updateCriteria);

        criteriaRepository.saveAll(filter.getFilterCriteria());
    }

    public List<Criteria> softDeleteCriteria(List<Criteria> entities, List<CriteriaDto> criteriaDtoList) {
        for (Criteria entity : entities) {
            boolean existsInDto = false;

            for (CriteriaDto dto : criteriaDtoList) {
                if (dto.getCriteriaId() == null) {
                    continue;
                }
                if (dto.getCriteriaId().equals(entity.getCriteriaId())) {
                    existsInDto = true;
                    break;
                }
            }

            if (!existsInDto) {
                entity.setDateDeleted(Instant.now());
            }
        }
        return entities;
    }

    public List<Criteria> addNewCriteria(List<CriteriaDto> criteriaDtoList, Filter filter) {
        if (criteriaDtoList == null) {
            return new ArrayList<>();
        }
        return criteriaDtoList.stream()
                .filter(dto -> dto.getCriteriaId() == null || dto.getCriteriaId() == 0)
                .map(dto -> createNewCriteria(dto, filter))
                .toList();
    }

    private List<Criteria> updateCriteria(List<Criteria> entities, List<CriteriaDto> criteriaDtoList) {
        for (Criteria entity : entities) {

            for (CriteriaDto dto : criteriaDtoList) {
                if (dto.getCriteriaId() == null) {
                    continue;
                }
                if (dto.getCriteriaId().equals(entity.getCriteriaId())) {
                    entity.setCriteriaValue(dto.getCriteriaValue());
                    entity.setType(dto.getType());
                    entity.setCondition(dto.getCondition());
                    entity.setSelected(dto.getSelected());
                }
            }
        }
        return entities;
    }

    public Criteria createNewCriteria(CriteriaDto dto, Filter filter) {
        Criteria newCriteria = Criteria.builder()
                .type(dto.getType())
                .condition(dto.getCondition())
                .criteriaValue(dto.getCriteriaValue())
                .selected(dto.getSelected())
                .filter(filter)
                .build();

        return criteriaRepository.save(newCriteria);
    }
}
