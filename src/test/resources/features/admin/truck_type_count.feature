@aggregate
Feature: Truck Type Count API - Admin Statistics

  @positive
  Scenario: admin GET all truck type count statistics successfully
    Given user has already had login token as "admin"
    When admin send GET request to count truck types
    Then status response code should be 200
    And return body is matched with "success_statistics.json" from "admin" schema

  @negative
  Scenario Outline: GET truck type count unsuccessfully
    Given user has already had login token as "<role>"
    When "<role>"send GET request to count truck types
    Then status response code should be 401
    And return body is matched with "<schema>" from "admin" schema

  Examples:
    | role     | schema                 |
    | customer | failed_statistics.json |
    | driver   | failed_statistics.json |
    | noLogin  | nologin_failure.json   |