package com.example.filterapi.repository;

import com.example.filterapi.domain.Filter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilterRepository extends JpaRepository<Filter, Long> {
}
