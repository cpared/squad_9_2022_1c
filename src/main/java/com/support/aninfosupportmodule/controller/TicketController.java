package com.support.aninfosupportmodule.controller;

import com.support.aninfosupportmodule.dto.TicketRequest;
import com.support.aninfosupportmodule.entity.Ticket;
import com.support.aninfosupportmodule.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/soporte/tickets")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/v1/soporte/tickets")
    public Ticket createTicket(@RequestBody TicketRequest ticketRequest) {
        System.out.println(ticketRequest);
        return ticketService.create(ticketRequest);
    }

    @GetMapping
    public List<Ticket> getTickets() {
        return ticketService.getTickets();
    }

    @GetMapping("/:{ticketId}")
    public Optional<Ticket> getTicketById(@PathVariable Integer ticketId) {
        return ticketService.getTicketById(ticketId);
    }
}
