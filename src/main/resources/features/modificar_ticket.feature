Feature: Modificar un ticket creado

  Scenario: Se modifica un ticket creado
    Given soy un usuario
    When intente modificar un ticket
    Then el ticket sera modificado