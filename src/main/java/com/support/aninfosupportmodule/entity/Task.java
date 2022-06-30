package com.support.aninfosupportmodule.entity;

import com.support.aninfosupportmodule.constant.State;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Data
@Entity
public class Task {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private ZonedDateTime creationDate;
    private String description;
    private State state;
    private Long proyectId;
}
