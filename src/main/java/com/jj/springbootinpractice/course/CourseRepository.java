package com.jj.springbootinpractice.course;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.stream.Stream;

@Repository
public interface CourseRepository extends CrudRepository<Course, UUID> {
    @Query("select c from Course c where c.category=?1")
    Iterable<Course> findAllByCategory(String category);

    Iterable<Course> findAllByCategoryOrderByName(String category);

    boolean existsByName(String name);

    long countByCategory(String category);

    Iterable<Course> findByNameOrCategory(String name, String category);

    Iterable<Course> findByNameStartsWith(String name);

    Stream<Course> streamAllByCategory(String category);

    Iterable<Course> findAllByCategoryAndRating(String category, Integer rating);

    @Query(value = "select * from COURSE where rating=?1", nativeQuery = true)
    Iterable<Course> findAllByRating(Integer rating);

    @Query("select c from Course c where c.category=:category and c.rating >:rating")
    Iterable<Course> findAllByCategoryAndRatingGreaterThan(@Param("category") String category,
            @Param("rating") Integer rating);

    @Modifying
    @Transactional
    @Query("update Course c set c.rating=:rating where c.name=:name")
    Integer updateCourseRatingByName(@Param("rating") Integer rating, @Param("name") String name);

}
