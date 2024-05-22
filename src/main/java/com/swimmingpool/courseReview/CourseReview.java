package com.swimmingpool.courseReview;

import com.swimmingpool.common.entity.AuditTable;
import com.swimmingpool.course.Course;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "COURSE_REVIEW")
public class CourseReview extends AuditTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REVIEW_ID")
    @EqualsAndHashCode.Include
    private Long reviewId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COURSE_ID")
    private Course course;

    @Column(name = "RATING")
    private Integer rating;

    @Column(name = "CMT", columnDefinition = "TEXT")
    private String comment;

    @Column(name = "IS_UPDATED")
    private boolean updated = false;
}
