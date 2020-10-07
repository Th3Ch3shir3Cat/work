package com.task.work.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.task.work.core.Group;
import com.task.work.core.Student;
import com.task.work.core.json.Views;
import com.task.work.svc.GroupService;
import com.task.work.svc.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class GroupController {

    private static final String BADMESSAGE_ATTRIBUTE_NAME = "badMessage";

    private final GroupService groupService;
    private final StudentService studentService;

    @Autowired
    public GroupController(GroupService groupService, StudentService studentService) {
        this.groupService = groupService;
        this.studentService = studentService;
    }

    @GetMapping(path = "/")
    public String listGroups(Model model){
        model.addAttribute("group", new Group());
        return "groups";
    }

    @GetMapping(path = "/groups/table", produces = {MediaType.APPLICATION_JSON_VALUE})
    @JsonView(Views.ForTable.class)
    public ResponseEntity<List<Group>> getGroupsForTable() {
        return ResponseEntity.ok(groupService.findAll());
    }

    @GetMapping("/group/{id}")
    public String getGroupPage(Model model, @PathVariable("id") Group group){
        model.addAttribute("group",group);
        model.addAttribute("student", new Student());
        return "group";
    }

    @GetMapping(path = "/group/{id}/students", produces = {MediaType.APPLICATION_JSON_VALUE})
    @JsonView(Views.ForTable.class)
    public ResponseEntity<List<Student>> getUsersForTable(@PathVariable("id") Group group) {
        return ResponseEntity.ok(studentService.getAllStudentsForGroup(group));
    }

    @PostMapping(value = "/group")
    public String saveNewGroup(Model model, @RequestBody Group group){
        try{
            group = groupService.save(group);
        }catch(Exception e){
            model.addAttribute(BADMESSAGE_ATTRIBUTE_NAME, "Возникла неизвестная ошибка при сохранении :(");
        }
        return getGroupPage(model, group);
    }

}
