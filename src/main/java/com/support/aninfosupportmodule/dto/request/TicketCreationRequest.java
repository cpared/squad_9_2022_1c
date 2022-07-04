package com.support.aninfosupportmodule.dto.request;

import com.support.aninfosupportmodule.constant.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketCreationRequest {

    private String title;
    private String description;
    private Integer severity;
    private Long assignedEmployeeId;
    private Category category;
    private Long clientId;
    private Long productId;
    private String version;
}
