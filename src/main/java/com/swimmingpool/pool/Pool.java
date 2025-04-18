package com.swimmingpool.pool;

import com.swimmingpool.common.entity.AuditTable;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "POOL")
public class Pool extends AuditTable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "number_of_student")
    private Integer numberOfStudent;

    @Column(name = "active")
    private Boolean active;
}
