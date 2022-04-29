@cust_order_list
Feature: Customer Order List API

  @positive
  Scenario: GET success customer order list
    Given user has already had login token as "customer"
    When customer send GET request to show order list with parameter: "REQUESTED"
    Then status response code should be 200
    And order status should be matched with parameter: "REQUESTED"
    And return body is matched with "success_order_list.json" from "customer" schema

  @negative
  Scenario Outline: GET unsuccess customer order list by using integer as parameter
    Given user has already had login token as "customer"
    When customer send GET request to show order list with parameter: <parameter>
    Then status response code should be 400

    Examples:
      | parameter |
      | 0         |
      | -1        |
      | 1         |

  @negative
  Scenario Outline: GET unsuccess customer order list by using invalid parameter
    Given user has already had login token as "customer"
    When customer send GET request to show order list with parameter: "<parameter>"
    Then status response code should be 400

    Examples:
      | parameter        |
      | invalidParameter |
      | @$%!^@&#*)(      |

  @negative
  Scenario: GET unsuccess customer order list by using invalid credentials
    Given user hasn't already had login token
    When user send GET request to show order list
    Then status response code should be 401
    And return body is matched with "failed_order_list_nologin.json" from "customer" schema