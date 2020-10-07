package com.task.work.svc;

import com.task.work.core.Group;
import com.task.work.core.Student;
import com.task.work.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public boolean deleteStudent(Integer id){
        this.studentRepository.deleteById(id);
        return true;
    }

    public List<Student> getAllStudentsForGroup(Group group){
        return studentRepository.findAllByGroup(group);
    }

    public Student save(Student student){
        return studentRepository.save(student);
    }

}
