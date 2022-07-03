package com.support.aninfosupportmodule.dto;

import com.support.aninfosupportmodule.constant.Category;
import com.support.aninfosupportmodule.constant.TicketStatus;
import com.support.aninfosupportmodule.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketResponse {

    private Long id;
    private String title;
    private String description;
    private Integer severity;
    private Employee assignedEmployee;
    private Category category;
    private TicketStatus status;
    private Client client;
    private Long productId;
    private String version;
    private List<Long> relatedTasks;
    private ZonedDateTime creationDate;
    private ZonedDateTime lastUpdate;
    private ZonedDateTime closingDate;

    public static TicketResponse newTicketResponse(Ticket ticket, Employee employee, Client client, List<Long> tasksList){
        return TicketResponse.builder()
                .id(ticket.getId())
                .title(ticket.getTitle())
                .description(ticket.getDescription())
                .severity(ticket.getSeverity())
                .assignedEmployee(employee)
                .category(ticket.getCategory())
                .status(ticket.getStatus())
                .client(client)
                .productId(ticket.getProductId())
                .version(ticket.getVersion())
                .relatedTasks(tasksList)
                .creationDate(ticket.getCreationDate())
                .lastUpdate(ticket.getLastUpdate())
                .closingDate(ticket.getClosingDate())
                .build();
    }
}
