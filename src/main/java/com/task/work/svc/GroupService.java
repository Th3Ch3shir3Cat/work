package com.task.work.svc;

import com.task.work.core.Group;
import com.task.work.repo.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group getGroupById(Integer id){
        return groupRepository.getOne(id);
    }

    public List<Group> findAll(){
        return groupRepository.findAll();
    }

}
