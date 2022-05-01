@admin_customer_detail @admin_get_driver_customer
Feature: Customer Detail API - Admin

  @positive
  Scenario: Admin GET customer detail successfully
    Given user has already had login token as "admin"
    When admin send GET request to get customer detail for id 3
    Then status response code should be 200
    And return body is matched with "success_customer_detail.json" from "admin" schema

  @negative
  Scenario Outline: Admin GET customer detail unsuccessfully by using invalid id number
    Given user has already had login token as "admin"
    When admin send GET request to get customer detail for invalid id "<id>"
    Then status response code should be 400
    And return body is matched with "failed_customer_list.json" from "admin" schema

    Examples:
      | id | note          |
      | 0  |               |
      | -1 |               |
      | 1  | minimum id -1 |
      | 6  | maximum id +1 |

  @negative
  Scenario Outline: Admin GET customer detail unsuccessfully by using string id
    Given user has already had login token as "admin"
    When admin send GET request to get customer detail for invalid id "<id>"
    Then status response code should be 400
    And return body is matched with "failed_customer_list.json" from "admin" schema

    Examples:
      | id |
      | a  |
      | @$ |

  @negative
  Scenario Outline: Admin GET customer detail unsuccessfully by using invalid credentials
    Given user has already had login token as "<role>"
    When "<role>" send GET request to show customer detail
    Then status response code should be 401
    And return body is matched with "<schema>" from "admin" schema

    Examples:
      | role     | schema                      |
      | customer | failed_customer_detail.json |
      | driver   | failed_customer_detail.json |
      | noLogin  | nologin_failure.json        |