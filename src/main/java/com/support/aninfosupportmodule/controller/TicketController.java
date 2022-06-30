package com.support.aninfosupportmodule.controller;

import com.support.aninfosupportmodule.dto.TicketRequest;
import com.support.aninfosupportmodule.entity.Ticket;
import com.support.aninfosupportmodule.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/soporte/tickets")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public Ticket createTicket(@RequestBody TicketRequest ticketRequest) {
        return ticketService.create(ticketRequest);
    }
}
