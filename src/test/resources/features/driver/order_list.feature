@list_and_current_order @order_list
Feature: Order List API - Driver

  @positive
  Scenario: Driver GET order list successfully
    Given user has already had login token as "driver4"
    When driver send GET request order list
    Then status response code should be 200
    And all the order is matched with driver's truck id
    And return body is matched with "success_order_list.json" from "driver" schema

  @negative
  Scenario Outline: Driver GET order list unsuccessfully by using invalid credentials
    Given user has already had login token as "<role>"
    When "<role>" send GET request driver order list
    Then status response code should be 401
    And return body is matched with "<schema>" from "driver" schema

  Examples:
    | role     | schema                 |
    | customer | failed_order_list.json |
    | admin    | failed_order_list.json |
    | noLogin  | nologin_failure.json   |