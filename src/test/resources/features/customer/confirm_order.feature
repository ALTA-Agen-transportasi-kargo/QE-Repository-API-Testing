@cust_confirm_cancel_order @cust_confirm_order
Feature: Confirm Order API - Customer

  @positive
  Scenario: Customer POST confirm order successfully
    Given user has already had login token as "customer"
    And order id 23 status is "NEED_CUSTOMER_CONFIRM"
    When customer send POST request to confirm order with id 23
    Then status response code should be 200
    And order id 23 status should change to "CONFIRMED"
    And return body is matched with "success_confirm_order.json" from "customer" schema

  @negative
  Scenario Outline: Customer POST confirm order unsuccessfully by using invalid ID
    Given user has already had login token as "customer"
    When customer send POST request to confirm order with id <id>
    Then status response code should be 400
    And return body is matched with "failed_confirm_order.json" from "customer" schema

  Examples:
    | id | note                               |
    | 0  |                                    |
    | -1 |                                    |
    | 3  | not in NEED_CUSTOMER_CONFIRM state |
    | 26 | max id +1                          |
    | 18 | order of another user              |

  @negative
  Scenario Outline: Customer POST confirm order unsuccessfully by using string as ID
    Given user has already had login token as "customer"
    When customer send POST request to confirm order with id "<id>"
    Then status response code should be 400
    And return body is matched with "failed_confirm_order.json" from "customer" schema

    Examples:
      | id | note |
      | a  | x    |
      | <> | x    |

  @negative
  Scenario Outline: Customer POST confirm order unsuccessfully by using invalid credentials
    Given user has already had login token as "<role>"
    When "<role>" send POST request to confirm order with id 17
    Then status response code should be 401
    And return body is matched with "<schema>" from "customer" schema

  Examples:
    | role    | schema                    |
    | driver  | failed_confirm_order.json |
    | noLogin | nologin_failure.json      |