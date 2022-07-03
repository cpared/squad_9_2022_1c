package com.support.aninfosupportmodule.constant;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TicketStatus {

    @JsonProperty("pending")
    PENDING,
    @JsonProperty("resolved")
    RESOLVED,
    @JsonProperty("closed")
    CLOSED
}
