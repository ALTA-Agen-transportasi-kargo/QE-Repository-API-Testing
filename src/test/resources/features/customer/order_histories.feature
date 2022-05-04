@cust_order_histories
Feature: Customer Order Histories API

  @positive
  Scenario: GET success customer order histories timeline
    Given user has already had login token as "customer"
    When customer send GET request order timeline with id: 4
    Then status response code should be 200
    And return body is matched with "success_customer_history.json" from "customer" schema

  @negative
  Scenario Outline: GET unsuccess customer order histories timeline
    Given user has already had login token as "customer"
    When customer send GET request order timeline with invalid id: <id>
    Then status response code should be 400
    And return body is matched with "failed_customer_history.json" from "customer" schema

    Examples:
      | id | note       |
      | 0  |            |
      | -1 |            |
      | 8  | (max id+1) |

  @negative
  Scenario Outline: GET unsuccess customer order histories timeline
    Given user has already had login token as "customer"
    When customer send GET request order timeline with invalid id: "<id>"
    Then status response code should be 400
    And return body is matched with "failed_customer_history.json" from "customer" schema

    Examples:
      | id  |
      | abc |
      | @#$ |

  Scenario: GET unsuccess customer order histories timeline by not log in
    Given user hasn't already had login token
    When customer send GET request order timeline
    Then status response code should be 401
    And return body is matched with "failed_customer_history_nologin.json" from "customer" schema