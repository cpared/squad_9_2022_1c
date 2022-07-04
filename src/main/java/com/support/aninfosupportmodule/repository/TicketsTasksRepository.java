package com.support.aninfosupportmodule.repository;

import com.support.aninfosupportmodule.entity.TicketTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TicketsTasksRepository extends CrudRepository<TicketTask, Long> {
}
