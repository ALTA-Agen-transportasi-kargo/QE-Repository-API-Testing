@admin_driver_list @admin_get_driver_customer
Feature: Admin Driver List API

  @positive
  Scenario: GET success show driver list
    Given user has already had login token as "admin"
    When admin send GET request to show driver list
    Then status response code should be 200
    And return body is matched with "success_driver_list.json" from "admin" schema

  @negative
  Scenario Outline: GET unsucces show driver list
    Given user has already had login token as "<actor>"
    When "<actor>" send GET request to show driver list
    Then status response code should be 401
    And return body is matched with "<schema>" from "admin" schema

  Examples:
    | actor    | schema                          |
    | customer | failed_driver_list.json         |
    | driver   | failed_driver_list.json         |
    | noLogin  | failed_driver_list_nologin.json |
