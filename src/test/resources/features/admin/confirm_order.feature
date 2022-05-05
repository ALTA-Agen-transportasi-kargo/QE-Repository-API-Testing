@admin_confirm_order_fix_price @admin_confirm_order
Feature: Confirm Order API - Admin

  @positive
  Scenario: Admin POST confirm order successfully
    Given user has already had login token as "admin"
    When admin send POST request to confirm order with id: 13
    Then status response code should be 200
    And order status changed to "CONFIRMED"
    And return body is matched with "success_confirm_order.json" from "admin" schema

  @negative
  Scenario Outline: Admin POST confirm order unsuccessfully by using invalid id
    Given user has already had login token as "admin"
    When admin send POST request to confirm order with id <id>
    Then status response code should be 400
    And return body is matched with "failed_confirm_order.json" from "admin" schema

  Examples:
    | id |
    | 0  |
    | -1 |
    | 14 |

  @negative
  Scenario Outline: Admin POST confirm order unsuccessfully by using string id
    Given user has already had login token as "admin"
    When admin send POST request to confirm order with id "<id>"
    Then status response code should be 400
    And return body is matched with "failed_confirm_order.json" from "admin" schema

    Examples:
      | id |
      | a  |
      | <> |

  @negative
  Scenario Outline: Admin POST confirm order unsuccessfully by using invalid credentials
    Given user has already had login token as "<role>"
    When "<role>" send POST request to confirm order for id 13
    Then status response code should be 401
    And return body is matched with "<schema>" from "admin" schema

  Examples:
    | role     | schema                    |
    | customer | failed_confirm_order.json |
    | driver   | failed_confirm_order.json |
    | noLogin  | nologin_failure.json      |