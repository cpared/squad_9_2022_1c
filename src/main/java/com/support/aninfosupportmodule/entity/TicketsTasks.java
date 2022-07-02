package com.support.aninfosupportmodule.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class TicketsTasks {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private Long ticketId;
    private Long taskId;
}
