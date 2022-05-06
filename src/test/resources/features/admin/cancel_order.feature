@admin_confirm_order_fix_price @admin_cancel_order
Feature: Cancel Order API - Admin

  @positive
  Scenario: Admin POST cancel order successfully
    Given user has already had login token as "admin"
    When admin send POST request to cancel order with id 14
    Then status response code should be 200
    And order status with id 14 is changed to "CANCELLED"
    And return body is matched with "success_cancel_order.json" from "admin" schema

  @negative
  Scenario Outline: Admin POST cancel order unsuccessfully by using invalid id
    Given user has already had login token as "admin"
    When admin send POST request to cancel order with id <id>
    Then status response code should be 400
    And return body is matched with "failed_cancel_order.json" from "admin" schema

  Examples:
    | id | note      |
    | 0  |           |
    | -1 |           |
    | 17 | max id +1 |

  @negative
  Scenario Outline: Admin POST cancel order unsuccessfully by using string id
    Given user has already had login token as "admin"
    When admin send POST request to cancel order with id "<id>"
    Then status response code should be 400
    And return body is matched with "failed_cancel_order.json" from "admin" schema

    Examples:
      | id |
      | a  |
      | <> |

  @negative
  Scenario Outline: Admin POST cancel order unsuccessfully by using invalid credentials
    Given user has already had login token as "<role>"
    When "<role>" send POST request to cancel order for id 7
    Then status response code should be 401
    And return body is matched with "<schema>" from "admin" schema

    Examples:
      | role     | schema                   |
      | customer | failed_cancel_order.json |
      | driver   | failed_cancel_order.json |
      | noLogin  | nologin_failure.json     |