package com.support.aninfosupportmodule.entity;

import com.support.aninfosupportmodule.constant.Category;
import com.support.aninfosupportmodule.dto.TicketRequest;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Entity
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;

    private String description;

    private Integer severity;

    private Integer assignedEmployee;

    private Category category;

    private ZonedDateTime creationDate;

    private ZonedDateTime lastUpdate;

    private ZonedDateTime closingDate;

    public Ticket(TicketRequest ticketRequest){
        title = ticketRequest.getTitle();
        description = ticketRequest.getDescription();
        severity = ticketRequest.getSeverity();
        category = ticketRequest.getCategory();
        creationDate = ZonedDateTime.now();
        lastUpdate = ZonedDateTime.now();
    }
}
