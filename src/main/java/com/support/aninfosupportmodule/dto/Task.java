package com.support.aninfosupportmodule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {

    private boolean success;
    private TicketData data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class TicketData {
        private Long id;
    }
}
