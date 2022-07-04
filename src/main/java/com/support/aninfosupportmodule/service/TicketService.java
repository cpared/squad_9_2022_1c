package com.support.aninfosupportmodule.service;

import com.support.aninfosupportmodule.dto.Client;
import com.support.aninfosupportmodule.dto.Employee;
import com.support.aninfosupportmodule.dto.TicketResponse;
import com.support.aninfosupportmodule.dto.request.TicketCreationRequest;
import com.support.aninfosupportmodule.dto.request.TicketUpdateRequest;
import com.support.aninfosupportmodule.entity.Ticket;
import com.support.aninfosupportmodule.entity.TicketTask;
import com.support.aninfosupportmodule.exception.NotFoundException;
import com.support.aninfosupportmodule.repository.TicketRepository;
import com.support.aninfosupportmodule.repository.TicketsTasksRepository;
import com.support.aninfosupportmodule.rest.ClientService;
import com.support.aninfosupportmodule.rest.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
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

    public List<TicketResponse> getTickets(Long taskId, Long productId, String version) {
        if (nonNull(taskId)) {
            return getTicketByTaskId(taskId);
        }
        if (nonNull(productId) && nonNull(version)) {
            return getFilteredTickets(productId, version);
        }
        return getTickets();
    }

    private List<TicketResponse> getFilteredTickets(Long productId, String version) {
        return getTickets().stream()
                .filter(t -> productId.equals(t.getProductId()))
                .filter(t -> version.equals(t.getVersion()))
                .collect(Collectors.toList());
    }

    public List<TicketResponse> getTickets() {
        List<Ticket> tickets = (List<Ticket>) ticketRepository.findAll();
        return tickets.stream()
                .map(this::mapTicketToTicketResponse)
                .collect(Collectors.toList());
    }

    public List<TicketResponse> getTicketByTaskId(Long taskId) {
        List<Long> ticketIds = getTicketTasks().stream()
                .filter(ticketTask -> taskId.equals(ticketTask.getTaskId()))
                .map(TicketTask::getTicketId)
                .collect(Collectors.toList());

        List<Ticket> tickets = (List<Ticket>) ticketRepository.findAllById(ticketIds);
        return tickets.stream()
                .map(this::mapTicketToTicketResponse)
                .collect(Collectors.toList());
    }

    public TicketResponse getWrappedTicketById(Long ticketId) {
        return mapTicketToTicketResponse(getTicketById(ticketId));
    }

    public TicketResponse updateTicket(TicketUpdateRequest ticketUpdateRequest, Long ticketId) {
        Ticket ticket = updateTicket(getTicketById(ticketId), ticketUpdateRequest);
        ticketRepository.save(ticket);
        return mapTicketToTicketResponse(ticket);
    }

    public TicketResponse deleteTicket(Long ticketId) {
        TicketResponse ticketResponse = mapTicketToTicketResponse(getTicketById(ticketId));
        ticketRepository.deleteById(ticketId);
        return ticketResponse;
    }

    public List<Long> getRelatedTasks(Long ticketId) {
        return getTicketTasks().stream()
                .filter(ticketTask -> ticketId.equals(ticketTask.getTicketId()))
                .map(TicketTask::getTaskId)
                .collect(Collectors.toList());
    }

    private List<TicketTask> getTicketTasks() {
        return (List<TicketTask>) ticketsTasksRepository.findAll();
    }

    private Ticket getTicketById(Long ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new NotFoundException("Ticket not found: " + ticketId));
    }

    private TicketResponse mapTicketToTicketResponse(Ticket ticket) {
        Client client = clientService.getClient(ticket.getClientId());
        Employee employee = employeeService.getEmployee(ticket.getAssignedEmployeeId());
        List<Long> tasksList = getRelatedTasks(ticket.getId());
        return newTicketResponse(ticket, employee, client, tasksList);
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
