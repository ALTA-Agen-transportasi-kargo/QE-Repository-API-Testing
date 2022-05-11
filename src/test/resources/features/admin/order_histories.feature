@admin_order_histories
Feature: Admin Order Histories API

  @positive
  Scenario: GET success admin order histories timeline
    Given user has already had login token as "admin"
    When admin send GET request order timeline with id: 4
    Then status response code should be 200
    And return body is matched with "success_admin_history.json" from "admin" schema

  @negative
  Scenario Outline: GET unsuccess admin order histories timeline
    Given user has already had login token as "admin"
    When admin send GET request order timeline with invalid id: <id>
    Then status response code should be 400
    And return body is matched with "failed_admin_history.json" from "admin" schema

    Examples:
      | id | note       |
      | 0  |            |
      | -1 |            |
      | 8  | (max id+1) |

  @negative
  Scenario Outline: GET unsuccess admin order histories timeline
    Given user has already had login token as "admin"
    When admin send GET request order timeline with invalid id: "<id>"
    Then status response code should be 400
    And return body is matched with "failed_admin_history.json" from "admin" schema

    Examples:
      | id  |
      | abc |
      | 2@c |

  Scenario: GET unsuccess admin order histories timeline by not log in
    Given user hasn't already had login token
    When admin send GET request order timeline
    Then status response code should be 401
    And return body is matched with "failed_admin_history_nologin.json" from "admin" schema