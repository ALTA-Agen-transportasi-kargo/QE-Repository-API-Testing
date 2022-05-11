@driver_take_finish_order @driver_take_order
Feature: Take Order API - Driver

  @positive
  Scenario: Driver POST take orders successfully
    Given user has already had login token as "driver4"
    When driver send POST request to take an order with id 21
    Then status response code should be 200
    And order id 21 status should change to "ON_PROCESS"
    And return body is matched with "success_take_order.json" from "driver" schema

  @negative
  Scenario Outline: Driver POST take orders unsuccessfully by using invalid id
    Given user has already had login token as "driver4"
    When driver send POST request to take an order with id <id>
    Then status response code should be 400
    And return body is matched with "failed_take_order.json" from "driver" schema

  Examples:
    | id | note                |
    | 0  |                     |
    | -1 |                     |
    | 2  | invalid truck id    |
    | 26 | max all order id +1 |

  @negative
  Scenario Outline: Driver POST take orders unsuccessfully by using string id
    Given user has already had login token as "driver4"
    When driver send POST request to take an order with id "<id>"
    Then status response code should be 400
    And return body is matched with "failed_take_order.json" from "driver" schema

    Examples:
      | id |
      | a  |
      | <> |

  @negative
  Scenario Outline: POST take orders unsuccessfully by using invalid credentials
    Given user has already had login token as "<role>"
    When "<role>" send POST request to take an order
    Then status response code should be 401
    And return body is matched with "<schema>" from "driver" schema

  Examples:
    | role     | schema                 |
    | customer | failed_take_order.json |
    | admin    | failed_take_order.json |
    | noLogin  | nologin_failure.json   |