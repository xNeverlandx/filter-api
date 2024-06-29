package com.example.filterapi.controller;

import com.example.filterapi.dto.FilterDto;
import com.example.filterapi.service.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/filter")
public class FilterController {
    private final FilterService fIlterService;

    @PostMapping()
    @ManagedOperation(description = "Create filter")
    public ResponseEntity<FilterDto> createFilter(@RequestBody FilterDto filterDto) {
        return ResponseEntity.ok(fIlterService.createFilter(filterDto));
    }

    @PutMapping("/{filterId}")
    @ManagedOperation(description = "Update filter")
    public ResponseEntity<FilterDto> updateFilter(@PathVariable Long filterId, @RequestBody FilterDto filterDto) {
        return ResponseEntity.ok(fIlterService.updateFilter(filterId, filterDto));
    }

    @GetMapping("/get-all")
    @ManagedOperation(description = "Get all filters")
    public ResponseEntity<List<FilterDto>> getFilters() {
        return ResponseEntity.ok(fIlterService.getFilters());
    }
}
