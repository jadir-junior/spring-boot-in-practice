package com.jj.springbootinpractice.course;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "COURSES")
@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Course {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NonNull
    @Column(name = "NAME")
    private String name;

    @NonNull
    @Column(name = "CATEGORY")
    private String category;

    @Min(value = 1, message = "A course should have a minimum of 1 rating")
    @Max(value = 5, message = "A course should have a maximum of 5 rating")
    @Column(name = "RATING")
    @NonNull
    private Integer rating;

    @NonNull
    @Column(name = "DESCRIPTION")
    private String description;
}
