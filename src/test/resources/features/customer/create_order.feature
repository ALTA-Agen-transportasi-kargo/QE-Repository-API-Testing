@customer-create-order
Feature: Create Order API - Customer

  @positive
  Scenario: POST customer success create order
    Given user has already had login token as "customer"
    When customer send POST request to create an order
    Then status response code should be 200
    And return body is matched with "success_create_order.json" from "customer" schema


  @negative
  Scenario Outline: POST customer unsuccess create order
    Given user has already had login token as "customer"
    When customer send POST request to create an order with condition: "<condition>"
    Then status response code should be 400
    And return body is matched with "failed_create_order.json" from "customer" schema

    Examples:
      | condition                 |
      | without_end_destination   |
      | without_order_picture     |
      | without_order_measurement |


  @negative
  Scenario: POST customer unsuccess create order by not logging in
    Given user hasn't already had login token
    When customer send POST request to create an order but not log in
    Then status response code should be 401
    And return body is matched with "failed_create_order_noToken.json" from "customer" schema