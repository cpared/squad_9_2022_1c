Feature: Crear un ticket

  Scenario: Un usuario crea un ticket
    Given  Soy un usuario
    When intente crear un ticket
    Then se creara un ticket

