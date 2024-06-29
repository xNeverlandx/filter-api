package com.example.filterapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "seq_criteria_id", sequenceName = "seq_criteria_id", allocationSize = 1, schema = "public")
@Table(name = "criteria", schema = "public")
public class Criteria extends AbstractDeletableEntity {

    @Id
    @Column(name = "criteria_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_criteria_id")
    private Long criteriaId;

    @Column(name = "type")
    private String type;

    @Column(name = "condition")
    private String condition;

    @Column(name = "criteria_value")
    private String criteriaValue;

    @Column(name = "selected")
    private Boolean selected;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "filter_id", nullable = false)
    private Filter filter;
}
