package com.support.aninfosupportmodule.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskRequest {

    private String name;
    private String description;
    private Long assigneeId;
    private Long priorityId;
    private Long projectId;
    private Long relatedTicketId;
}
