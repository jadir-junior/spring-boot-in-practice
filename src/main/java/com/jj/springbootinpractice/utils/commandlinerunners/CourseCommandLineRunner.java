package com.jj.springbootinpractice.utils.commandlinerunners;

import com.jj.springbootinpractice.course.Course;
import com.jj.springbootinpractice.course.CourseRepository;
import com.jj.springbootinpractice.user.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Order(1)
@Component
public class CourseCommandLineRunner implements CommandLineRunner {
    @Autowired
    CourseRepository courseRepository;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run(String... args) throws Exception {
        createCourse();
        createUsers();
    }

    public void createCourse() {
        Course course = new Course();
        course.setRating(0);
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Course>> violations = validator.validate(course);
        violations.forEach(courseConstraintViolation -> logger.error(
                "A constraint violation has occurred. Violation details: [{}].",
                courseConstraintViolation)
        );
    }

    public void createUsers() {
        User user1 = new User("sbip01", "sbip");
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user1);
        logger.error("Password for user do not adhere to the password policy");
        violations.forEach(constraintViolation -> logger.error("Violatiion details: [{}]", constraintViolation.getMessage()));

        User user2 = new User("sbip02", "Sbip01$4UDfg");
        violations = validator.validate(user2);
        if(violations.isEmpty()) {
            logger.info("Password for user2 adhere to the password policy");
        }

        User user3 = new User("sbip03", "Sbip01$4UDfgggg");
        violations = validator.validate(user3);
        logger.error("Password for user3 violates maximun repetitive rule");
        violations.forEach(constraintViolation -> logger.error("Violation details: [{}]", constraintViolation.getMessage()));

        User user4 = new User("sbip04", "Sbip014UDfgggg");
        violations = validator.validate(user4);
        logger.error("Password for user4 violates special character rule");
        violations.forEach(constraintViolation -> logger.error("Violation details: [{}]", constraintViolation.getMessage()));
    }

    public void createCourses() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Java", 5));
        courses.add(new Course("Spring Boot", 4));
        courses.add(new Course("Angular", 5));

        courses.forEach((course) -> {
            courseRepository.save(course);
            logger.info("Course was add to database " + course.toString());
        });
    }
}
