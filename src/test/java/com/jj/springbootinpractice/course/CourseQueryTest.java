package com.jj.springbootinpractice.course;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CourseQueryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void givenCourseCreatesWhenLoadCoursesWithQueryThenExpectCorrectCourseDetails() {
        courseRepository.saveAll(CoursesMock.getCourseList());

        Assertions.assertThat(courseRepository.findAllByCategory("Spring")).hasSize(3);
        Assertions.assertThat(courseRepository.findAllByRating(3)).hasSize(2);
        Assertions.assertThat(courseRepository.findAllByCategoryAndRatingGreaterThan("Spring", 3)).hasSize(2);

        courseRepository.updateCourseRatingByName(4, "Getting Started with Spring Cloud Kubernetes");

        Assertions.assertThat(courseRepository.findAllByCategoryAndRatingGreaterThan("Spring", 3)).hasSize(3);
    }
}
