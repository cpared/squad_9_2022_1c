Feature: Dar de baja un ticket

  Scenario: Se da de baja un ticket creado
    Given Soy un usuario
    When De de baja un ticket
    Then El ticket sera eliminado