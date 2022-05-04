@admin_customer_list @admin_get_driver_customer
Feature: Customer List API - Admin

  @positive
  Scenario: Admin GET customer list successfully
    Given user has already had login token as "admin"
    When admin send GET request to show customer list
    Then status response code should be 200
    And all result role is "customer"
    And return body is matched with "success_customer_list.json" from "admin" schema

  @positive
  Scenario: Admin GET customer list successfully using filter name
    Given user has already had login token as "admin"
    When admin send GET request to show customer list with filter name "budi"
    Then status response code should be 200
    And result customer name should contain "budi"
    And return body is matched with "success_customer_list.json" from "admin" schema

  @negative
  Scenario: Admin GET customer list successfully using filter name with invalid name
    Given user has already had login token as "admin"
    When admin send GET request to show customer list with filter name "candraxxx"
    Then status response code should be 400
    And return body is matched with "failed_customer_list.json" from "admin" schema

  @negative
  Scenario Outline: Admin GET customer list unsuccessfully
    Given user has already had login token as "<role>"
    When "<role>" send GET request to show customer list
    Then status response code should be 401
    And return body is matched with "<schema>" from "admin" schema

    Examples:
      | role     | schema                            |
      | customer | failed_customer_list.json         |
      | driver   | failed_customer_list.json         |
      | noLogin  | failed_customer_list_nologin.json |