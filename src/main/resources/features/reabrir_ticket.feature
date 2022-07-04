Feature:

  Scenario: Un ticket fue cerrado y se desea reabrirlo
    Given soy un usuario
    And un ticket fue cerrado
    When el cliente rechace la solución
    Then podré reabri el ticket