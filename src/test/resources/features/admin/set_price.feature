@admin_confirm_order_fix_price @admin_fix_price
Feature: Set Customer Order Price API - Admin

  @positive
  Scenario: Admin PATCH set order's price successfully
    Given user has already had login token as "admin"
    When admin send PATCH request to set price of customer order with id 11 to 100000
    Then status response code should be 200
    And order fix price for order id 11 should be equal to 100000
    And order status is "NEED_CUSTOMER_CONFIRM"
    And return body is matched with "success_fix_price.json" from "admin" schema

  @negative
  Scenario Outline: Admin PATCH set order's price unsuccessfully by using invalid id
    Given user has already had login token as "admin"
    When admin send PATCH request to set price of customer order with id <id> to 100000
    Then status response code should be 400
    And return body is matched with "failed_fix_price.json" from "admin" schema

  Examples:
    | id |
    | 0  |
    | -1 |
    | 14 |

  @negative
  Scenario Outline: Admin PATCH set order's price unsuccessfully by using string as id
    Given user has already had login token as "admin"
    When admin send PATCH request to set price of customer order with id "<id>" to 100000
    Then status response code should be 400
    And return body is matched with "failed_fix_price.json" from "admin" schema

  Examples:
    | id |
    | a  |
    | <> |

  @negative
  Scenario Outline: Admin PATCH set order's price unsuccessfully by using invalid credentials
    Given user has already had login token as "<role>"
    When "<role>" send PATCH request to set price of customer order with id 11 to 100000
    Then status response code should be 401
    And return body is matched with "<schema>" from "admin" schema

  Examples:
    | role     | schema                |
    | customer | failed_fix_price.json |
    | driver   | failed_fix_price.json |
    | noLogin  | nologin_failure.json  |