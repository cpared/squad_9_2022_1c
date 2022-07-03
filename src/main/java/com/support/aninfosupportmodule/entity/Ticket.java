package com.support.aninfosupportmodule.entity;

import com.support.aninfosupportmodule.constant.Category;
import com.support.aninfosupportmodule.constant.TicketStatus;
import com.support.aninfosupportmodule.dto.TicketCreationRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Entity
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String title;
    private String description;
    private Integer severity;
    private Long assignedEmployeeId;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
    private Long clientId;
    private Long productId;
    private String version;
    private ZonedDateTime creationDate;
    private ZonedDateTime lastUpdate;
    private ZonedDateTime closingDate;

    public Ticket(TicketCreationRequest ticketCreationRequest){
        title = ticketCreationRequest.getTitle();
        description = ticketCreationRequest.getDescription();
        severity = ticketCreationRequest.getSeverity();
        assignedEmployeeId = ticketCreationRequest.getAssignedEmployeeId();
        category = ticketCreationRequest.getCategory();
        status = TicketStatus.PENDING;
        clientId = ticketCreationRequest.getClientId();
        productId = ticketCreationRequest.getProductId();
        version = ticketCreationRequest.getVersion();
        creationDate = ZonedDateTime.now();
        lastUpdate = ZonedDateTime.now();
    }
}
