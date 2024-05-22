package com.swimmingpool.course;

import com.swimmingpool.common.dto.PageResponse;
import com.swimmingpool.course.request.CourseSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.swimmingpool.course.response.CourseImageDTO;
import com.swimmingpool.course.response.CourseResponse;
import com.swimmingpool.course.response.CourseSearchResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {

    @Transactional
    Optional<Course> findByCode(String code);

//    @Query("SELECT c FROM Course c WHERE c.active = true")
//    List<Course> findActiveCourse();

    @Query(nativeQuery = true, value = "SELECT c FROM Course c WHERE c.active = '0'")
    static List<Course> findActiveCourse() {
        // Implementation of the static method
        // This method returns the best-selling courses based on the provided limit
        return List.of();
    }

    @Query(nativeQuery = true, value = "SELECT * FROM (SELECT c.id, c.code, c.name, c.avatar, c.price, c.slug, c.short_description, COUNT(*) AS count FROM course c JOIN assignment a ON c.id = a.course_id JOIN orders o ON a.id = o.assignment_id WHERE c.active = '1' GROUP BY c.id, c.code, c.name, c.avatar, c.price, c.slug, c.short_description ORDER BY COUNT(*) DESC) AS subquery LIMIT ?1")
    static List<CourseResponse> getBestSeller(int limit) {
        // Implementation of the static method
        // This method returns the best-selling courses based on the provided limit
        return List.of();
    }

    @Query(nativeQuery = true, value = "SELECT * FROM GetActiveCourses WHERE ROWNUM <= limit ?1")
    static List<CourseImageDTO> getCourseImage(int limit) {
        // Implementation of the static method
        // This method returns the best-selling courses based on the provided limit
        return List.of();
    }

    @Query(nativeQuery = true, value = "SELECT c.id, c.code, c.name, c.avatar, c.price, c.slug, c.short_description FROM course c WHERE c.active = true AND DATEDIFF(now(), c.created_date) <= ?1 ORDER BY c.created_date DESC")
    static List<CourseResponse> getNewest(int limit) {
        // Implementation of the static method
        // This method returns the best-selling courses based on the provided limit
        return List.of();
    }

    @Query(nativeQuery = true, value = "SELECT p.id, p.code, p.name, p.avatar, p.price, p.slug, p.short_description FROM course p WHERE p.active = true AND p.discount  >= ?1 ORDER BY p.discount DESC")
    static List<CourseResponse> getHotSales(int limit) {
        // Implementation of the static method
        // This method returns the best-selling courses based on the provided limit
        return List.of();
    }

    @Query(nativeQuery = true, value = "")
    static PageResponse<CourseSearchResponse>  searchCourse_codeName(String codeName) {
        // Implementation of the static method
        // This method returns the best-selling courses based on the provided limit
        return null;
    }
}



