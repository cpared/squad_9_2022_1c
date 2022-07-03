package com.support.aninfosupportmodule.dto;

import com.support.aninfosupportmodule.constant.Category;
import com.support.aninfosupportmodule.constant.TicketStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketUpdateRequest {

    private String title;
    private String description;
    private Integer severity;
    private Long employeeId;
    private Category category;
    private TicketStatus status;
}
