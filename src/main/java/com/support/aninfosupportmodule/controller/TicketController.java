package com.support.aninfosupportmodule.controller;

import com.support.aninfosupportmodule.dto.TicketResponse;
import com.support.aninfosupportmodule.dto.request.TicketCreationRequest;
import com.support.aninfosupportmodule.dto.request.TicketUpdateRequest;
import com.support.aninfosupportmodule.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<TicketResponse> getTickets(@RequestParam @Nullable Long taskId,
                                           @RequestParam @Nullable Long productId,
                                           @RequestParam @Nullable String version) {
        return ticketService.getTickets(taskId, productId, version);
    }

    @GetMapping("/task/{ticketId}")
    public List<Long> getRelatedTasks(@PathVariable Long ticketId) {
        return ticketService.getRelatedTasks(ticketId);
    }

    @GetMapping("{ticketId}")
    public TicketResponse getTicketById(@PathVariable Long ticketId) {
        return ticketService.getWrappedTicketById(ticketId);
    }
}
