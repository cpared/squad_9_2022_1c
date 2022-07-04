Feature: Obtener tickets creados

  Scenario: Se crea 1 ticket y este es pedido
    Given soy un usuario
    And se creo un ticket
    When quiera obtener el ticket creado
    Then se me devolvera el ticket creado

  Scenario: Se crean 3 ticket y estos son pedidos
    Given soy un usuario
    And se crearon 3 tickets
    When quiera obtener los tickets creados
    Then se me devolveran los tickets creados


  Scenario: No hay ticket creados y son pedidos los tickets
    Given soy un usuario
    And no hay tickets creados
    When quiera obtener los tickets creados
    Then se devolvera nada

