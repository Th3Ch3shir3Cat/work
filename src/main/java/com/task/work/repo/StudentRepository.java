package com.task.work.repo;

import com.task.work.core.Group;
import com.task.work.core.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findAllByGroupOrderByFio(Group group);
}
