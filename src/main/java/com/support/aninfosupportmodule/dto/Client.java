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
public class Client {

    private Long id;
    @JsonAlias("razon social")
    private String name;
    @JsonAlias("CUIT")
    private String cuit;
}
