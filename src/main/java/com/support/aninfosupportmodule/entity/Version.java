package com.support.aninfosupportmodule.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Data
@Entity
public class Version {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private ZonedDateTime releaseDate;
}
