package com.example.filterapi.repository;

import com.example.filterapi.domain.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CriteriaRepository extends JpaRepository<Criteria, Long> {
}
