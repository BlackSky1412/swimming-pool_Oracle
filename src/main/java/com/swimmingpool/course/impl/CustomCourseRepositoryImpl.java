package com.swimmingpool.course.impl;

import com.swimmingpool.common.constant.AppConstant;
import com.swimmingpool.common.dto.PageResponse;
import com.swimmingpool.common.util.PagingUtil;
import com.swimmingpool.course.CourseRowMapper;
import com.swimmingpool.course.request.CourseSearchRequest;
import com.swimmingpool.course.response.CourseImageDTO;
import com.swimmingpool.course.response.CourseResponse;
import com.swimmingpool.course.response.CourseSearchResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CustomCourseRepositoryImpl {

    private final EntityManager entityManager;

    public PageResponse<CourseSearchResponse> searchCourse(CourseSearchRequest courseSearchRequest) {
        StringBuilder sqlBuilder = new StringBuilder("SELECT c.id AS id, c.code AS code, c.name AS name, c.created_date AS createdDate")
                .append(", c.modified_date AS modifiedDate, c.price AS price, c.number_of_lesson AS numberOfLesson")
                .append(", c.discount AS discount, c.short_description AS shortDescription")
                .append(", c.avatar AS avatar, c.active AS active, c.slug AS slug")
                .append(" FROM course c");

        if (Objects.nonNull(courseSearchRequest.getDayOfWeek())) {
            sqlBuilder.append(" LEFT JOIN assignment a ON a.course_id = c.id");
        }

        sqlBuilder.append(" WHERE 1 = 1");

        Map<String, Object> params = new HashMap<>();

        if (StringUtils.hasLength(courseSearchRequest.getCodeName())) {
            sqlBuilder.append(" AND (c.code LIKE :codeName OR c.name LIKE :codeName)");
            params.put("codeName", "%" + courseSearchRequest.getCodeName() + "%");
        }

        if (courseSearchRequest.getActive() != null) {
            sqlBuilder.append(" AND (c.active = :active)");
            params.put("active", courseSearchRequest.getActive());
        }

        if (courseSearchRequest.getMinPrice() != null) {
            sqlBuilder.append(" AND (c.price >= :minPrice)");
            params.put("minPrice", courseSearchRequest.getMinPrice().longValue());
        }

        if (courseSearchRequest.getMaxPrice() != null) {
            sqlBuilder.append(" AND (c.price <= :maxPrice)");
            params.put("maxPrice", courseSearchRequest.getMaxPrice().longValue());
        }

        if (Objects.nonNull(courseSearchRequest.getDayOfWeek())) {
            sqlBuilder.append(" AND (a.day_of_week = :dayOfWeek)");
        }

        sqlBuilder.append(" ORDER BY c.created_date DESC");

        String query = sqlBuilder.toString();

        if (Objects.nonNull(courseSearchRequest.getDayOfWeek())) {
            query = "SELECT * FROM (" + query + ") AS subquery GROUP BY id";
            params.put("dayOfWeek", courseSearchRequest.getDayOfWeek().getValue());
        }

        return PagingUtil.paginate(query, params, courseSearchRequest, CourseRowMapper.searchCourseMapper())
                .apply(this.entityManager);
    }


//    public List<CourseImageDTO> getCourseImage(int limit) {
//        String sql = "SELECT * FROM GetActiveCourses WHERE ROWNUM <= " + limit;
//        Query query = entityManager.createNativeQuery(sql, Tuple.class);
//        List<Tuple> resultList = query.getResultList();
//        return resultList.stream()
//                .map(tuple -> CourseImageDTO.builder()
//                        .id(tuple.get(0, String.class))
//                        .name(tuple.get(1, String.class))
//                        .code(tuple.get(2, String.class))
//                        .imageUrl(tuple.get(3, String.class))
//                        .slug(tuple.get(4, String.class))
//                        .build()
//                )
//                .collect(Collectors.toList());
//    }


//    public List<CourseResponse> getBestSeller(int limit) {
//        String sql = new StringBuilder ("SELECT * FROM (")
//                .append(" SELECT")
//                .append(" c.id, c.code, c.name, c.avatar, c.price, c.slug, c.short_description, COUNT(*) AS count")
//                .append(" FROM")
//                .append(" course c")
//                .append(" JOIN assignment a ON c.id = a.course_id")
//                .append(" JOIN orders o ON a.id = o.assignment_id")
//                .append(" WHERE c.active = '1'")
//                .append(" GROUP BY c.id, c.code, c.name, c.avatar, c.price, c.slug, c.short_description")
//                .append(" ORDER BY COUNT(*) DESC")
//                .append(")") // Loại bỏ dấu chấm phẩy dư thừa ở đây
//            .toString();
//        return this.getCourseInUserHome(sql, limit);
//    }


//    public List<CourseResponse> getNewest(int limit) {
//        String sql = new StringBuilder("SELECT c.id, c.code, c.name, c.avatar, c.price, c.slug, c.short_description")
//                .append(" FROM course c")
//                .append(" WHERE c.active = true AND DATEDIFF(now(), c.created_date) <= ?1")
//                .append(" ORDER BY c.created_date DESC")
//                .toString();
//        return this.getCourseInUserHome(sql, limit, AppConstant.DATE_INDICATE_NEWEST);
//    }

//    public List<CourseResponse> getHotSales(int limit) {
//        String sql = new StringBuilder("SELECT p.id, p.code, p.name, p.avatar, p.price, p.slug, p.short_description")
//                .append(" FROM course p")
//                .append(" WHERE p.active = true AND p.discount  >= ?1")
//                .append(" ORDER BY p.discount DESC")
//                .toString();
//        return this.getCourseInUserHome(sql, limit, AppConstant.PERCENT_INDICATE_HOT_SALE);
//    }

//    private List<CourseResponse> getCourseInUserHome(String sql, int limit, Object... params) {
//        String modifiedSql = sql.replaceAll("FETCH FIRST \\? ROWS ONLY", "");
//
//        StringBuilder modifiedSqlBuilder = new StringBuilder(modifiedSql);
//        int lastIndexOfClosingParenthesis = modifiedSql.lastIndexOf(")");
//        modifiedSqlBuilder.insert(lastIndexOfClosingParenthesis + 1, " WHERE ROWNUM <= " + limit + ";");
//        String finalSql = modifiedSqlBuilder.toString();
//
//        Object[] finalParams = Arrays.copyOf(params, params.length + 1);
//        finalParams[params.length] = limit;
//
//        Function<Tuple, CourseResponse> mapper = tuple -> CourseResponse.builder()
//                .id(tuple.get(0, String.class))
//                .code(tuple.get(1, String.class))
//                .name(tuple.get(2, String.class))
//                .image(tuple.get(3, String.class))
//                .price(tuple.get(4, BigDecimal.class))
//                .slug(tuple.get(5, String.class))
//                .shortDescription(tuple.get(6, String.class))
//                .build();
//
//        return this.getCourseInUserHome(finalSql, limit, mapper, finalParams);
//    }

//    private List<CourseResponse> getCourseInUserHome(String sql, int limit, Function<Tuple, CourseResponse> mapper, Object...params) {
//        Query query = entityManager.createNativeQuery(sql, Tuple.class);
//        if (Objects.nonNull(params)) {
//            for (int i = 0; i < params.length; i++) {
//                query.setParameter(i + 1, params[i]);
//            }
//        }
//        //query.setMaxResults(limit);
//        List<Tuple> resultList = query.getResultList();
//
//        return resultList.stream()
//                .map(mapper)
//                .collect(Collectors.toList());
//    }
}