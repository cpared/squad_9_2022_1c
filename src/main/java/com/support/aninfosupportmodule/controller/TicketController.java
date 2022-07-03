package com.support.aninfosupportmodule.controller;

import com.support.aninfosupportmodule.dto.TicketCreationRequest;
import com.support.aninfosupportmodule.dto.TicketResponse;
import com.support.aninfosupportmodule.dto.TicketUpdateRequest;
import com.support.aninfosupportmodule.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.nonNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/support/tickets")
@CrossOrigin
public class TicketController {

    private final TicketService ticketService;

    @DeleteMapping("{ticketId}")
    public TicketResponse deleteTicket(@PathVariable Long ticketId){
        return ticketService.deleteTicket(ticketId);
    }

    @PostMapping
    public TicketResponse createTicket(@RequestBody TicketCreationRequest ticketCreationRequest) {
        return ticketService.createTicket(ticketCreationRequest);
    }

    @PutMapping("{ticketId}")
    public TicketResponse updateTicket(@RequestBody TicketUpdateRequest ticketUpdateRequest, @PathVariable Long ticketId) {
        return ticketService.updateTicket(ticketUpdateRequest, ticketId);
    }

    @GetMapping
    public List<TicketResponse> getTickets(@RequestParam @Nullable Long taskId) {
        return nonNull(taskId) ? ticketService.getTicketByTaskId(taskId) : ticketService.getTickets();
    }

    @GetMapping("{ticketId}")
    public TicketResponse getTicketById(@PathVariable Long ticketId) {
        return ticketService.getWrappedTicketById(ticketId);
    }

}
