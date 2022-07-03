package com.support.aninfosupportmodule.constant;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Category {

    @JsonProperty("error")
    ERROR,
    @JsonProperty("incident")
    INCIDENT,
    @JsonProperty("question")
    QUESTION,
    @JsonProperty("suggestion")
    SUGGESTION
}

