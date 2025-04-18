package com.swimmingpool.course;

import com.swimmingpool.common.entity.AuditTable;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "COURSE")
public class Course extends AuditTable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private String id;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "number_of_lesson")
    private Integer numberOfLesson;

    @Column(name = "short_description", length = 200)
    private String shortDescription;

    @Lob
    @Column(name = "description", columnDefinition = " longtext")
    private String description;

    @Column(name = "is_show_home")
    private Boolean is_Show_Home;

    @Column(name = "slug")
    private String slug;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "active")
    private Boolean active;
}
