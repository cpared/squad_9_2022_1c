package com.support.aninfosupportmodule.constant;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum State {

    @JsonProperty("Completada")
    COMPLETED,

    @JsonProperty("Pendiente")
    PENDING,
}

