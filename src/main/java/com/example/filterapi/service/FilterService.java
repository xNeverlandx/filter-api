package com.example.filterapi.service;

import com.example.filterapi.domain.Criteria;
import com.example.filterapi.domain.Filter;
import com.example.filterapi.dto.FilterDto;
import com.example.filterapi.dto.mapper.FilterDtoMapper;
import com.example.filterapi.repository.FilterRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilterService {
    private final CriteriaService criteriaService;
    private final FilterRepository filterRepository;

    @Transactional
    public FilterDto createFilter(FilterDto dto) {
        Filter filter = Filter.builder()
                .name(dto.getName())
                .build();

        filter = filterRepository.save(filter);

        List<Criteria> filterCriteria = criteriaService.addNewCriteria(dto.getCriteriaDtoList(), filter);
        filter.setFilterCriteria(filterCriteria);

        return FilterDtoMapper.filterToDto(filter);
    }

    public FilterDto updateFilter(Long filterId, FilterDto filterDto) {
        Filter filter = filterRepository.findById(filterId).orElseThrow(() ->
                new EntityNotFoundException(String.format("Filter with id %s does not exist.", filterId)));

        filter.setName(filterDto.getName());

        criteriaService.updateCriteria(filter, filterDto);

        return FilterDtoMapper.filterToDto(filter);
    }

    public FilterDto deleteFilter(Long filterId) {
        Filter filter = filterRepository.findById(filterId).orElseThrow(() ->
                new EntityNotFoundException(String.format("Filter with id %s does not exist.", filterId)));

        filter.setDateDeleted(Instant.now());
        criteriaService.softDeleteCriteria(filter.getFilterCriteria(), new ArrayList<>());
        filterRepository.save(filter);
        return FilterDtoMapper.filterToDto(filter);
    }

    public List<FilterDto> getFilters() {
        List<Filter> filters = filterRepository.findAll();

        return filters.stream()
                .map(FilterDtoMapper::filterToDto)
                .collect(Collectors.toList());
    }
}
