package com.jj.springbootinpractice.course;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;

@DataJpaTest
public class CustomizedCourseTests {
    @Autowired
    private CustomizedCourseRepository customizedCourseRepository;

    @Test
    public void givenCreateCourseWhenFindAllCoursesThenExpectOneCourse() {
        Course course = new Course(
                "Rapid Spring Boot Application Development",
                "Spring",
                4,
                "Spring Boot gives all the power of the Spring Framework without all of the complexities");
        customizedCourseRepository.save(course);
        Assertions.assertThat(Arrays.asList(customizedCourseRepository.findAll()).size()).isEqualTo(1);
    }
}
