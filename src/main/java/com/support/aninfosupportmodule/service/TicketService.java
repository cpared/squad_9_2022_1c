package com.support.aninfosupportmodule.service;

import com.support.aninfosupportmodule.dto.TicketRequest;
import com.support.aninfosupportmodule.entity.Ticket;
import com.support.aninfosupportmodule.entity.TicketsTasks;
import com.support.aninfosupportmodule.repository.TicketRepository;
import com.support.aninfosupportmodule.repository.TicketsTasksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TicketsTasksRepository ticketsTasksRepository;

    public Ticket create(TicketRequest ticketRequest) {
        Ticket ticket = new Ticket(ticketRequest);
        ticketRepository.save(ticket);
        return ticket;
    }

    public List<Ticket> getTickets(){
        List<Ticket> tickets = (List<Ticket>) ticketRepository.findAll();
        return tickets;
    }

    public Optional<Ticket> getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId);
    }

    public List<Ticket> getTicketByTaskId(Long taskId){
        List<TicketsTasks> data = (List<TicketsTasks>) ticketsTasksRepository.findAll();
        List<TicketsTasks> filteredList = data.stream().filter(ticketsTasks -> ticketsTasks.getTaskId().equals(taskId)).collect(Collectors.toList());

        List<Long> ticketIds = new ArrayList<>();

        for (TicketsTasks ticketsTasks : filteredList) {
            ticketIds.add(ticketsTasks.getTicketId());
        }

        return (List<Ticket>) ticketRepository.findAllById(ticketIds);
    }

    public Ticket updateTicket(TicketRequest ticketRequest, Long ticketId) {
        Ticket ticket = new Ticket(ticketRequest);
        ticket.setId(ticketId);
        ticket.setLastUpdate(ZonedDateTime.now());
        return ticketRepository.save(ticket);
    }
}
