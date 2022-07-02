Feature: Modificar un ticket creado

  Scenario: Se modifica un ticket creado
    Given se creo un ticket
    When intente modificarlo
    Then el ticket sera modificado