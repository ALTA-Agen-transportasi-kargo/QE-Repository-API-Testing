@aggregate
Feature: Driver Count API - Admin Statistics

  @positive
  Scenario: admin GET all driver count statistics successfully
    Given user has already had login token as "admin"
    When admin send GET request to count drivers
    Then status response code should be 200
    And return body is matched with "success_statistics.json" from "admin" schema

  @positive
  Scenario: admin GET driver count statistics by status successfully
    Given user has already had login token as "admin"
    When admin send GET request to count drivers by status "IDLE"
    Then status response code should be 200
    And return body is matched with "success_statistics.json" from "admin" schema

  @positive
  Scenario: admin GET driver count statistics by account status successfully
    Given user has already had login token as "admin"
    When admin send GET request to count drivers by account status "VERIFIED"
    Then status response code should be 200
    And return body is matched with "success_statistics.json" from "admin" schema

  @positive
  Scenario: admin GET order count statistics by truck type id successfully
    Given user has already had login token as "admin"
    When admin send GET request to count drivers by truck type id 1
    Then status response code should be 200
    And return body is matched with "success_statistics.json" from "admin" schema

  @negative
  Scenario Outline: GET drivers count unsuccessfully
    Given user has already had login token as "<role>"
    When "<role>"send GET request to count drivers
    Then status response code should be 401
    And return body is matched with "<schema>" from "admin" schema

  Examples:
    | role     | schema                 |
    | customer | failed_statistics.json |
    | driver   | failed_statistics.json |
    | noLogin  | nologin_failure.json   |