@admin_list_detail_order @admin_list_order
Feature: List Order API - Admin

  @positive
  Scenario: Admin GET all orders successfully
    Given user has already had login token as "admin"
    When admin send GET request all orders
    Then status response code should be 200
    And return body is matched with "success_list_order.json" from "admin" schema

  @positive
  Scenario: Admin GET orders by status successfully
    Given user has already had login token as "admin"
    When admin send GET request orders with status "CONFIRMED"
    Then status response code should be 200
    And All order status should be "CONFIRMED"
    And return body is matched with "success_list_order.json" from "admin" schema

  @negative
  Scenario: Admin GET orders unsuccessfully by using invalid status
    Given user has already had login token as "admin"
    When admin send GET request orders with status "ABCDEFG"
    Then status response code should be 200
    And return body is matched with "empty_data_list.json" from "admin" schema
    But data should be empty

  @negative
  Scenario Outline: Admin GET orders unsuccessfully by using invalid credentials
    Given user has already had login token as "<role>"
    When "<role>" send GET request all orders
    Then status response code should be 401
    And return body is matched with "<schema>" from "admin" schema

  Examples:
    | role     | schema                 |
    | customer | failed_list_order.json |
    | driver   | failed_list_order.json |
    | noLogin  | nologin_failure.json   |