@finish_order_list
Feature: Finish Order List API - Driver

  @positive
  Scenario: Driver GET finish order list successfully
    Given user has already had login token as "driver4"
    When driver send GET request finish order list
    Then status response code should be 200
    And return body is matched with "success_finish_list.json" from "driver" schema

  @negative
  Scenario Outline: Driver GET finish order list unsuccessfully by using invalid credentials
    Given user has already had login token as "<role>"
    When "<role>" send GET request driver finish order list
    Then status response code should be 401
    And return body is matched with "<schema>" from "driver" schema

  Examples:
    | role     | schema                  |
    | customer | failed_finish_list.json |
    | admin    | failed_finish_list.json      |
    | noLogin  | nologin_failure.json    |