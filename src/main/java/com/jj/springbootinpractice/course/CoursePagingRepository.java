package com.jj.springbootinpractice.course;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CoursePagingRepository extends PagingAndSortingRepository<Course, UUID> {
}
