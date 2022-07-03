insert into ticket(id, title, description, severity, assigned_employee_id, category, status, client_id, product_id, version, creation_date, last_update, closing_date) values (999, 'Usuario no cuenta el ultimo', 'Esta es una descripcion', 2, 1, 'ERROR', 'PENDING', 1, 1, '1.0.0', now(), now(), null)

insert into ticket(id, title, description, severity, assigned_employee_id, category, status, client_id, product_id, version, creation_date, last_update, closing_date) values (1000, 'Error de login', 'Esta es una descripcion', 5, 2, 'ERROR', 'CLOSED', 3, 2, '1.2.3', '2022-06-02T23:00:19.44862-03:00', now(), now())

insert into tickets_tasks(id, task_id, ticket_id) values (1, 1, 999)