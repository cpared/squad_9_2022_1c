package com.support.aninfosupportmodule.controller;

import com.support.aninfosupportmodule.dto.ProjectData;
import com.support.aninfosupportmodule.dto.TaskData;
import com.support.aninfosupportmodule.dto.request.TaskRequest;
import com.support.aninfosupportmodule.exception.InternalServerException;
import com.support.aninfosupportmodule.rest.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/support/project")
@CrossOrigin
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping()
    public List<ProjectData> getProjects(){
        return projectService.getProjects();
    }

    @PostMapping("/task")
    public TaskData createTask(@RequestBody TaskRequest request) throws InternalServerException {
        return projectService.createTask(request);
    }
}
