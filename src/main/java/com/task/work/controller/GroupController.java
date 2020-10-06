package com.task.work.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.task.work.core.Group;
import com.task.work.core.json.Views;
import com.task.work.svc.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class GroupController {
    private final GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping(path = "/")
    public String listGroups(Model model){
        return "groups";
    }

    @GetMapping(path = "/groups/table", produces = {MediaType.APPLICATION_JSON_VALUE})
    @JsonView(Views.ForTable.class)
    public ResponseEntity<List<Group>> getGroupsForTable() {
        return ResponseEntity.ok(groupService.findAll());
    }

    @GetMapping(path = "/group/{id}/students")
    public String getPageForEditGroup(@PathVariable("id") Group group, Model model){
        model.addAttribute(group);
        return "group";
    }

}
