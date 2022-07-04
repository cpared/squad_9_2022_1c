package com.support.aninfosupportmodule.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExternalTaskRequest {

    private String name;
    private String description;
    private Long idAssigned;
    private Long idPriority;
    private Long idProject;
    private Long idAsociatedTicket;
    private boolean bySupportTeam;
}
