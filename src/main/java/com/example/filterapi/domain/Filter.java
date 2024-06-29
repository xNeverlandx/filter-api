package com.example.filterapi.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@SuperBuilder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "seq_filter_id", sequenceName = "seq_filter_id", allocationSize = 1, schema = "public")
@Table(name = "filter", schema = "public")
public class Filter extends AbstractDeletableEntity {

    @Id
    @Column(name = "filter_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_filter_id")
    private Long filterId;

    @Column(name = "name")
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "filter", cascade = CascadeType.ALL)
    @SQLRestriction("date_deleted is null")
    private List<Criteria> filterCriteria;
}
