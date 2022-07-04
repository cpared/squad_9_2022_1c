Feature: Dar de baja un ticket

  Scenario: Se da de baja un ticket creado
    Given Soy un usuario
    When haya creado un ticket por error
    And quiera eliminar el ticket
    Then El ticket sera eliminado y no se daran de baja las tarea asociadas a este, si hubiera