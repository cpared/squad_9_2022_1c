package com.support.aninfosupportmodule.service;

import com.support.aninfosupportmodule.dto.TicketRequest;
import com.support.aninfosupportmodule.entity.Ticket;
import com.support.aninfosupportmodule.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public Ticket create(TicketRequest ticketRequest) {
        Ticket ticket = new Ticket(ticketRequest);
        ticketRepository.save(ticket);
        return ticket;
    }

    public List<Ticket> getTickets(){
        List<Ticket> tickets = (List<Ticket>) ticketRepository.findAll();
        return tickets;
    }

    public Optional<Ticket> getTicketById(Integer ticketId) {
        return ticketRepository.findById(ticketId);
    }
}
