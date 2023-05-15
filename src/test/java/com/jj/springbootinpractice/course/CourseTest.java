package com.jj.springbootinpractice.course;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class CourseTest {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CoursePagingRepository coursePagingRepository;

    @BeforeEach
    public void each() {
        courseRepository.deleteAll();
        courseRepository.saveAll(CoursesMock.getCourseList());
    }

    @Test
    public void givenCreateCourseWhenLoadTheCourseThenExpectSameCourse() {
        Course course = createOneCourse();
        Course savedCourse = courseRepository.save(course);
        Assertions.assertThat(courseRepository.findById(savedCourse.getId())).get().isEqualTo(course);
    }

    @Test
    public void givenUpdateCourseWhenLoadTheCourseThenExpectUpdatedCourse() {
        Course course = createOneCourse();
        courseRepository.save(course);
        course.setRating(5);
        Course savedCourse = courseRepository.save(course);
        Assertions.assertThat(courseRepository.findById(savedCourse.getId()).get().getRating()).isEqualTo(5);
    }

    @Test
    public void givenDeleteCourseWhenLoadTheCourseThenExpectNoCourse() {
        Course course = createOneCourse();
        Course savedCourse = courseRepository.save(course);
        Assertions.assertThat(courseRepository.findById(savedCourse.getId()).get()).isEqualTo(course);
        courseRepository.delete(course);
        Assertions.assertThat(courseRepository.findById(savedCourse.getId()).isPresent()).isFalse();
    }

    @Test
    public void givenCreateCourseWhenLoadTheCourseTheExpectSameCourse() {
        Assertions.assertThat(courseRepository.findAllByCategory("Spring")).hasSize(3);
        Assertions.assertThat(courseRepository.existsByName("JavaScript for All")).isTrue();
        Assertions.assertThat(courseRepository.existsByName("Mastering JavaScript")).isFalse();
        Assertions.assertThat(courseRepository.countByCategory("Python")).isEqualTo(2);
        Assertions.assertThat(courseRepository.findByNameStartsWith("Getting Started")).hasSize(3);
    }

    @Test
    public void givenDataAvailableWhenLoadFistPageThenGetFiveRecords() {
        Pageable pageable = PageRequest.of(0, 5);
        Assertions.assertThat(coursePagingRepository.findAll(pageable)).hasSize(5);
        Assertions.assertThat(pageable.getPageNumber()).isEqualTo(0);

        Pageable nextPageable = pageable.next();
        Assertions.assertThat(coursePagingRepository.findAll(nextPageable)).hasSize(2);
        Assertions.assertThat(nextPageable.getPageNumber()).isEqualTo(1);
    }

    @Test
    public void givenDataAvailableWhenSortsFirstPageTheGetSortedSData() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("Name")));
        Condition<Course> sortedFirstCourseCondition = new Condition<Course>() {
            @Override
            public boolean matches(Course course) {
                return course.getName().equals("Game Development with Python");
            }
        };

        Assertions.assertThat(coursePagingRepository.findAll(pageable)).first().has(sortedFirstCourseCondition);
    }

    @Test
    public void givenDataAvailableWhenApplyCustomSortThenGetSortedResult() {
        Pageable customSortPageable = PageRequest.of(0, 5, Sort.by("Rating")
                .descending().and(Sort.by("Name")));
        Condition<Course> customSortFirstCourseCondition = new Condition<Course>() {
            @Override
            public boolean matches(Course course) {
                return course.getName().equals("Getting Started with Python");
            }
        };

        Assertions.assertThat(coursePagingRepository.findAll(customSortPageable))
                .first().has(customSortFirstCourseCondition);
    }

    @Test
    public void givenCoursesCreatedWhenLoadCoursesBySpringCategoryThenExpectThreeCourses() {
        Assertions.assertThat(courseRepository.findAllByCategoryAndRating("Spring", 4)).hasSize(1);
    }

    private Course createOneCourse() {
        return new Course(
                "Rapid Spring Boot Application Development",
                "Spring",
                4,
                "Spring Boot gives all the power of the Spring Framework without all of the complexities");
    }
}
