@aggregate
Feature: Customer Count API - Admin Statistics

  @positive
  Scenario: admin GET all customer count statistics successfully
    Given user has already had login token as "admin"
    When admin send GET request to count all customers
    Then status response code should be 200
    And return body is matched with "success_statistics.json" from "admin" schema

  @negative
  Scenario Outline: GET customers count unsuccessfully
    Given user has already had login token as "<role>"
    When "<role>"send GET request to count customers
    Then status response code should be 401
    And return body is matched with "<schema>" from "admin" schema

  Examples:
    | role     | schema                 |
    | customer | failed_statistics.json |
    | driver   | failed_statistics.json |
    | noLogin  | nologin_failure.json   |