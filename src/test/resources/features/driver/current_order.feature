@list_and_current_order @current_order
Feature: Current Order API - Driver

  @positive
  Scenario: Driver GET current successfully
    Given user has already had login token as "driver4"
    When driver send GET request current order
    Then status response code should be 200
    And return body is matched with "success_current_order.json" from "driver" schema

  @negative
  Scenario Outline: Driver GET current unsuccessfully by using invalid credentials
    Given user has already had login token as "<role>"
    When "<role>" send GET request driver current order
    Then status response code should be 401
    And return body is matched with "<schema>" from "driver" schema

    Examples:
      | role     | schema                    |
      | customer | failed_current_order.json |
      | admin    | failed_current_order.json |
      | noLogin  | nologin_failure.json      |