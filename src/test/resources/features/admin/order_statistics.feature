@aggregate @order_per_day
Feature: Order Statistics per Day API - Admin

  @positive
  Scenario: Admin GET order statistics per day prior successfully
    Given user has already had login token as "admin"
    When admin send GET order statistics for 5 days prior
    Then status response code should be 200
    And report for today and 5 days prior will be shown
    And return body is matched with "success_per_day.json" from "admin" schema

  @negative
  Scenario: Admin GET order statistics per day prior unsuccessfully
    Given user has already had login token as "admin"
    When admin send GET order statistics for -1 days prior
    Then status response code should be 200
    But "data[0].total_order" should be empty
    And return body is matched with "failed_minus.json" from "admin" schema

  @negative
  Scenario Outline: Admin GET order statistics per day prior unsuccessfully by using string for days
    Given user has already had login token as "admin"
    When admin send GET order statistics for "<day>" days prior
    Then status response code should be 400
    And return body is matched with "failed_statistics.json" from "admin" schema

  Examples:
    | day |
    | one |
    | !!! |

  Scenario Outline: GET order statistics per day prior unsuccessfully by using invalid credentials
    Given user has already had login token as "<role>"
    When "<role>" send GET order statistics for 5 days prior
    Then status response code should be 401
    And return body is matched with "<schema>" from "admin" schema

    Examples:
      | role     | schema                 |
      | customer | failed_statistics.json |
      | driver   | failed_statistics.json |
      | noLogin  | nologin_failure.json   |