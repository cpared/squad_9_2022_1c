package com.support.aninfosupportmodule.service;

import com.support.aninfosupportmodule.constant.TicketStatus;
import com.support.aninfosupportmodule.dto.*;
import com.support.aninfosupportmodule.entity.Ticket;
import com.support.aninfosupportmodule.entity.TicketsTasks;
import com.support.aninfosupportmodule.repository.TicketRepository;
import com.support.aninfosupportmodule.repository.TicketsTasksRepository;
import com.support.aninfosupportmodule.rest.ClientService;
import com.support.aninfosupportmodule.rest.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.support.aninfosupportmodule.constant.TicketStatus.CLOSED;
import static com.support.aninfosupportmodule.dto.TicketResponse.newTicketResponse;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final ClientService clientService;
    private final EmployeeService employeeService;
    private final TicketRepository ticketRepository;
    private final TicketsTasksRepository ticketsTasksRepository;

    public TicketResponse createTicket(TicketCreationRequest ticketCreationRequest) {
        Ticket ticket = new Ticket(ticketCreationRequest);
        ticketRepository.save(ticket);
        return mapTicketToTicketResponse(ticket);
    }

    public List<TicketResponse> getTickets() {
        List<Ticket> tickets = (List<Ticket>) ticketRepository.findAll();
        return tickets.stream()
                .map(this::mapTicketToTicketResponse)
                .collect(Collectors.toList());
    }

    public TicketResponse getWrappedTicketById(Long ticketId) {
        return getTicketById(ticketId)
                .map(this::mapTicketToTicketResponse)
                .orElse(null);
    }

    public Optional<Ticket> getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId);
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

    public TicketResponse updateTicket(TicketUpdateRequest ticketUpdateRequest, Long ticketId) {
        Ticket ticket = getTicketById(ticketId)
                .map(t -> updateTicket(t, ticketUpdateRequest))
                .orElseThrow(() -> new NotFoundException("Ticket not found: " + ticketId));
        ticketRepository.save(ticket);
        return mapTicketToTicketResponse(ticket);
    }

    private TicketResponse mapTicketToTicketResponse(Ticket ticket) {
        Client client = clientService.getClient(ticket.getClientId());
        Employee employee = employeeService.getEmployee(ticket.getAssignedEmployeeId());
        return newTicketResponse(ticket, employee, client);
    }

    private Ticket updateTicket(Ticket ticket, TicketUpdateRequest request) {
        ticket.setTitle(nonNull(request.getTitle()) ? request.getTitle() : ticket.getTitle());
        ticket.setDescription(nonNull(request.getDescription()) ? request.getDescription() : ticket.getDescription());
        ticket.setSeverity(nonNull(request.getSeverity()) ? request.getSeverity() : ticket.getSeverity());
        ticket.setAssignedEmployeeId(nonNull(request.getEmployeeId()) ? request.getEmployeeId() : ticket.getAssignedEmployeeId());
        ticket.setCategory(nonNull(request.getCategory()) ? request.getCategory() : ticket.getCategory());
        ticket.setStatus(nonNull(request.getStatus()) ? request.getStatus() : ticket.getStatus());

        ticket.setLastUpdate(ZonedDateTime.now());
        if (CLOSED.equals(request.getStatus())) {
            ticket.setClosingDate(ZonedDateTime.now());
        }

        return ticket;
    }
}
