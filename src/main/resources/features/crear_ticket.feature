Feature: Crear un ticket

  Scenario: Un usuario crea un ticket
    Given  Soy un usuario
    When se reporte un nuevo evento
    Then se podra crear un ticket con la informacion necesaria

