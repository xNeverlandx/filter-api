package com.example.filterapi.service;

import com.example.filterapi.domain.Filter;
import com.example.filterapi.dto.FilterDto;
import com.example.filterapi.repository.FilterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Tag("unit")
public class FilterServiceTests {
    @Mock
    private CriteriaService criteriaService;

    @Mock
    private FilterRepository filterRepository;

    @InjectMocks
    private FilterService filterService;

    private FilterDto filterDto;
    private Filter filter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        filterService = new FilterService(
                criteriaService,
                filterRepository
        );

        filterDto = new FilterDto();
        filterDto.setName("Test Filter");

        filter = Filter.builder()
                .name("Test Filter")
                .build();
    }

    @Test
    void createFilter() {
        when(filterRepository.save(any())).thenReturn(filter);

        FilterDto result = filterService.createFilter(filterDto);

        assertNotNull(result);
        assertEquals(filterDto.getName(), result.getName());
    }

    @Test
    void getFilters() {
        when(filterRepository.findAll()).thenReturn(Collections.singletonList(filter));

        List<FilterDto> result = filterService.getFilters();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(filterRepository, times(1)).findAll();
    }
}
