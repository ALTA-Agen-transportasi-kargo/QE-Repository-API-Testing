@cust_order_detail
Feature: Customer Order Detail API

  @positive
  Scenario: GET success customer order detail
    Given user has already had login token as "customer"
    When customer send GET request to show order detail for id 4
    Then status response code should be 200
    And return body is matched with "success_order_detail.json" from "customer" schema

  @negative
  Scenario Outline: GET unsuccess customer order detail by invalid id
    Given user has already had login token as "customer"
    When customer send GET request to show order detail for id <id>
    Then status response code should be 400
    And return body is matched with "failed_order_detail.json" from "customer" schema

    Examples:
      | id | note              |
      | 0  |                   |
      | -1 |                   |
      | 8  | total records + 1 |

    @negative
    Scenario Outline: GET unsuccess customer order detail by string id
      Given user has already had login token as "customer"
      When customer send GET request to show order detail for id "<string>"
      Then status response code should be 400
      And return body is matched with "failed_order_detail_string.json" from "customer" schema

      Examples:
        | string |
        | abc    |
        | $%^    |

  @negative
    Scenario: GET unsuccess customer order detail by not log in
      Given user hasn't already had login token
      When customer send GET request to show order detail
      Then status response code should be 401
      And return body is matched with "failed_order_detail_nologin.json" from "customer" schema