package com.support.aninfosupportmodule.service;

import com.support.aninfosupportmodule.dto.TicketRequest;
import com.support.aninfosupportmodule.entity.Ticket;
import com.support.aninfosupportmodule.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public Ticket create(TicketRequest ticketRequest) {
        Ticket ticket = new Ticket(ticketRequest);
        ticketRepository.save(ticket);
        return ticket;
    }
}
