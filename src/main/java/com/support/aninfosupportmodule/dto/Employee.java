package com.support.aninfosupportmodule.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

    @JsonAlias("legajo")
    private Long id;
    @JsonAlias("Nombre")
    private String firstName;
    @JsonAlias("Apellido")
    private String lastName;
}
