package com.unal.teacher;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository
        extends PagingAndSortingRepository<Teacher,Long> {
}
