Feature: Visualizar detalles de un ticket

  Scenario: Se creo un ticket y quiero observar su detalle
    Given soy un usuario
    And se creo un ticket
    When quiera obtener el detalle del ticket creado
    Then se me devolvera el detalle del ticket creado
