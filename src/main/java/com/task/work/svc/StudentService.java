package com.task.work.svc;

import com.task.work.core.Group;
import com.task.work.core.Student;
import com.task.work.repo.GroupRepository;
import com.task.work.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    public boolean deleteStudent(Integer id){
        Group group = groupRepository.getOne(studentRepository.getOne(id).getGroup().getId());
        group.setNumerus_students(group.getNumerus_students()-1);
        this.groupRepository.save(group);
        this.studentRepository.deleteById(id);
        return true;
    }

    public List<Student> getAllStudentsForGroup(Group group){
        return studentRepository.findAllByGroup(group);
    }

    public Student save(Student student){
        Group group = groupRepository.getOne(student.getGroup().getId());
        group.setNumerus_students(group.getNumerus_students()+1);
        this.groupRepository.save(group);
        return studentRepository.save(student);
    }

}
