package com.jj.springbootinpractice.course;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class CourseTest {
    @Autowired
    private CourseRepository courseRepository;

    private Course createOneCourse() {
        return new Course(
                "Rapid Spring Boot Application Development",
                "Spring",
                4,
                "Spring Boot gives all the power of the Spring Framework without all of the complexities");
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
        courseRepository.saveAll(getCourseList());
        Assertions.assertThat(courseRepository.findAllByCategory("Spring")).hasSize(3);
        Assertions.assertThat(courseRepository.existsByName("JavaScript for All")).isTrue();
        Assertions.assertThat(courseRepository.existsByName("Mastering JavaScript")).isFalse();
        Assertions.assertThat(courseRepository.countByCategory("Python")).isEqualTo(2);
        Assertions.assertThat(courseRepository.findByNameStartsWith("Getting Started")).hasSize(3);
    }

    private List<Course> getCourseList() {
        Course rapidSpringBootCourse = new Course("Rapid Spring Boot Application Development", "Spring", 4,"Spring Boot gives all the power of the Spring Framework without all of the complexity");
        Course springSecurityDslCourse = new Course("Getting Started with Spring Security DSL", "Spring", 5, "Learn Spring Security DSL in easy steps");
        Course springCloudKubernetesCourse = new Course("Getting Started with Spring Cloud Kubernetes", "Spring", 3, "Master Spring Boot application deployment with Kubernetes");
        Course rapidPythonCourse = new Course("Getting Started with Python", "Python", 5, "Learn Python concepts in easy steps");
        Course gameDevelopmentWithPython = new Course("Game Development with Python", "Python", 3, "Learn Python by developing 10 wonderful games");
        Course javaScriptForAll = new Course("JavaScript for All", "JavaScript", 4, "Learn basic JavaScript syntax that can apply to anywhere");
        Course javaScriptCompleteGuide = new Course("JavaScript Complete Guide", "JavaScript", 5, "Master JavaScript with Core Concepts and Web Development");

        return Arrays.asList(rapidSpringBootCourse, springSecurityDslCourse, springCloudKubernetesCourse, rapidPythonCourse, gameDevelopmentWithPython, javaScriptForAll, javaScriptCompleteGuide);
    }
}
