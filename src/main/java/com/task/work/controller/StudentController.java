package com.task.work.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.task.work.core.Group;
import com.task.work.core.Student;
import com.task.work.core.json.Views;
import com.task.work.svc.GroupService;
import com.task.work.svc.StudentService;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping("/create")
    public ResponseEntity<Message> addStudent(@RequestBody Student student){
        try{
            studentService.save(student);
            return new ResponseEntity<Message> (HttpStatus.OK);
        }
        catch(Exception exception){
            return new ResponseEntity<Message> (HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> deleteStudent(@PathVariable Integer id){
        try {
            studentService.deleteStudent(id);
            return new ResponseEntity<Message> (HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<Message> (HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
