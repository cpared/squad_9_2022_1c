package com.support.aninfosupportmodule.rest;

import com.support.aninfosupportmodule.dto.Project;
import com.support.aninfosupportmodule.dto.Task;
import com.support.aninfosupportmodule.dto.request.ExternalTaskRequest;
import com.support.aninfosupportmodule.dto.request.TaskRequest;
import com.support.aninfosupportmodule.entity.TicketTask;
import com.support.aninfosupportmodule.exception.InternalServerException;
import com.support.aninfosupportmodule.repository.TicketsTasksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final static String BASE_URL = "https://api-psa-projects.herokuapp.com";
    private final static String URI_TEMPLATE_PROJECTS = "/proyectos";
    private final static String URI_TEMPLATE_TASKS = "/tareas";
    private final WebClient client = WebClient.create(BASE_URL);

    private final TicketsTasksRepository ticketsTasksRepository;

    public List<Project> getProjects() {
        return getProjectsInternal().stream()
                .sorted(Comparator.comparing(Project::getName))
                .collect(Collectors.toList());
    }

    public Task createTask(TaskRequest request) throws InternalServerException {
        Task task = createTaskInternal(request);
        if (!task.isSuccess()) {
            throw new InternalServerException("There was an error while creating the task, please try again");
        }
        TicketTask ticketsTask = new TicketTask(request.getRelatedTicketId(), task.getData().getId());
        ticketsTasksRepository.save(ticketsTask);
        return task;
    }

    private Task createTaskInternal(TaskRequest request) {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.POST);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(URI_TEMPLATE_TASKS);
        WebClient.RequestHeadersSpec<?> bodyValueSpec = bodySpec.bodyValue(buildTaskRequest(request));
        return bodyValueSpec.retrieve()
                .bodyToMono(Task.class).block();
    }

    private List<Project> getProjectsInternal() {
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.GET);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(URI_TEMPLATE_PROJECTS);
        return bodySpec.retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Project>>() {
                }).block();
    }

    private ExternalTaskRequest buildTaskRequest(TaskRequest request) {
        return ExternalTaskRequest.builder()
                .name(request.getName())
                .description(request.getDescription())
                .idAssigned(request.getAssigneeId())
                .idPriority(request.getPriorityId())
                .idProject(request.getProjectId())
                .idAsociatedTicket(request.getRelatedTicketId())
                .bySupportTeam(true)
                .build();
    }
}
