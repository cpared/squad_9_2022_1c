package com.support.aninfosupportmodule.controller;

import com.support.aninfosupportmodule.dto.TicketRequest;
import com.support.aninfosupportmodule.entity.Ticket;
import com.support.aninfosupportmodule.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/soporte/tickets")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public Ticket createTicket(@RequestBody TicketRequest ticketRequest) {
        return ticketService.create(ticketRequest);
    }

    @PutMapping("{ticketId}")
    public Ticket updateTicket(@RequestBody TicketRequest ticketRequest, @PathVariable Long ticketId) {
        return ticketService.updateTicket(ticketRequest, ticketId);
    }

    @GetMapping
    public List<Ticket> getTickets(@RequestParam @Nullable Long taskId) {
        if (nonNull(taskId)) {
            return ticketService.getTicketByTaskId(taskId);
        }
        return ticketService.getTickets();
    }

    @GetMapping("{ticketId}")
    public Optional<Ticket> getTicketById(@PathVariable Long ticketId) {
        return ticketService.getTicketById(ticketId);
    }

}
