package com.support.aninfosupportmodule.constant;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TicketStatus {

    @JsonProperty("pending")
    PENDING,
    @JsonProperty("in_progress")
    IN_PROGRESS,
    @JsonProperty("blocked")
    BLOCKED,
    @JsonProperty("resolved")
    RESOLVED,
    @JsonProperty("closed")
    CLOSED,
    @JsonProperty("cancelled")
    CANCELLED
}
