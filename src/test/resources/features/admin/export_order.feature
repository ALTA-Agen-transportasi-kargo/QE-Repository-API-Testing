@export_orders
Feature: Export Orders API - Admin

  @positive
  Scenario: Admin POST export orders successfully
    Given user has already had login token as "admin"
    When admin send POST request export orders for month 5 and year 2022
    Then status response code should be 200
    And response body length should be greater than 204 bytes

  @negative
  Scenario Outline: Admin POST export orders unsuccessfully by using invalid month or year
    Given user has already had login token as "admin"
    When admin send POST request export orders for month <month> and year <year>
    Then status response code should be 200
    But response body length should be equal or lower than 204 bytes

  Examples:
    | month | year | note          |
    | 1     | 2022 | invalid month |
    | 5     | 2021 | invalid year  |

  @negative
  Scenario Outline: Admin POST export orders unsuccessfully by neglecting month and/or year
    Given user has already had login token as "admin"
    When admin send POST request export orders for month <month> and year <year>
    Then status response code should be 400
    But return body is matched with "failed_export.json" from "admin" schema

    Examples:
      | month | year | note                   |
      | 0     | 2022 | without month          |
      | 5     | 0    | without year           |
      | 0     | 0    | without month and year |

  @negative
  Scenario Outline: Admin POST export orders unsuccessfully by using string in month and/or year
    Given user has already had login token as "admin"
    When admin send POST request export orders for month "<month>" and year "<year>"
    Then status response code should be 400
    But return body is matched with "failed_export.json" from "admin" schema

    Examples:
      | month | year                     | note                          |
      | May   | 2022                     | string in month               |
      | 5     | Two Thousands Twenty Two | string in year                |
      | May   | Two Thousands Twenty Two | string in both month and year |

  @negative
  Scenario Outline: POST export orders unsuccessfully by using invalid credentials
    Given user has already had login token as "<role>"
    When "<role>" send POST request export orders for month 5 and year 2022
    Then status response code should be 401
    But return body is matched with "<schema>" from "admin" schema

    Examples:
      | role     | schema               |
      | customer | failed_export.json   |
      | driver   | failed_export.json   |
      | noLogin  | nologin_failure.json |
