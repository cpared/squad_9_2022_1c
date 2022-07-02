package com.support.aninfosupportmodule.repository;

import com.support.aninfosupportmodule.entity.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "tickets", collectionResourceRel = "tickets")
public interface TicketRepository extends CrudRepository<Ticket, Long> {
}
