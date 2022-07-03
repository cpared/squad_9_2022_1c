package com.support.aninfosupportmodule.service;

import com.support.aninfosupportmodule.dto.Client;
import com.support.aninfosupportmodule.dto.Employee;
import com.support.aninfosupportmodule.dto.TicketRequest;
import com.support.aninfosupportmodule.dto.TicketResponse;
import com.support.aninfosupportmodule.entity.Ticket;
import com.support.aninfosupportmodule.entity.TicketsTasks;
import com.support.aninfosupportmodule.repository.TicketRepository;
import com.support.aninfosupportmodule.repository.TicketsTasksRepository;
import com.support.aninfosupportmodule.rest.ClientService;
import com.support.aninfosupportmodule.rest.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.support.aninfosupportmodule.dto.TicketResponse.newTicketResponse;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final ClientService clientService;
    private final EmployeeService employeeService;
    private final TicketRepository ticketRepository;
    private final TicketsTasksRepository ticketsTasksRepository;

    public TicketResponse create(TicketRequest ticketRequest) {
        Ticket ticket = new Ticket(ticketRequest);
        ticketRepository.save(ticket);
        return mapTicketToTicketResponse(ticket);
    }

    public List<TicketResponse> getTickets() {
        List<Ticket> tickets = (List<Ticket>) ticketRepository.findAll();
        return tickets.stream()
                .map(this::mapTicketToTicketResponse)
                .collect(Collectors.toList());
    }

    public TicketResponse getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .map(this::mapTicketToTicketResponse)
                .orElse(null);
    }

    public List<TicketResponse> getTicketByTaskId(Long taskId) {
        List<TicketsTasks> data = (List<TicketsTasks>) ticketsTasksRepository.findAll();
        List<Long> ticketIds = data.stream()
                .filter(ticketsTasks -> taskId.equals(ticketsTasks.getTaskId()))
                .map(TicketsTasks::getTicketId)
                .collect(Collectors.toList());

        List<Ticket> tickets = (List<Ticket>) ticketRepository.findAllById(ticketIds);
        return tickets.stream()
                .map(this::mapTicketToTicketResponse)
                .collect(Collectors.toList());
    }

    public Ticket updateTicket(TicketRequest ticketRequest, Long ticketId) {
        Ticket ticket = new Ticket(ticketRequest);
        ticket.setId(ticketId);
        ticket.setLastUpdate(ZonedDateTime.now());
        return ticketRepository.save(ticket);
    }

    private TicketResponse mapTicketToTicketResponse(Ticket ticket) {
        Client client = clientService.getClient(ticket.getClientId());
        Employee employee = employeeService.getEmployee(ticket.getClientId());
        return newTicketResponse(ticket, employee, client);
    }
}
