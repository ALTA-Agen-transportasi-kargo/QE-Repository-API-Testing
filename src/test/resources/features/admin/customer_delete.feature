@admin-delete-customer-profile
Feature: Delete Profile Customer API - Admin

  @positive
  Scenario: DELETE admin success delete profile customer
    Given user has already had login token as "admin"
    When admin send DELETE request to delete customer account with id 38
    Then status response code should be 200
    And return body is matched with "success_delete_customer.json" from "admin" schema

  @negative @admin-delete-customer-invalid-id
  Scenario Outline: DELETE admin unsuccessfully delete profile customer by using invalid id
    Given user has already had login token as "admin"
    When admin send DELETE request to delete customer account with id <id>
    Then status response code should be 400
    And return body is matched with "failed_delete_customer.json" from "admin" schema

    Examples:
      | id | note       |
      | 0  |            |
      | -1 |            |
      | 12 | max id + 1 |

  @negative @admin-delete-customer-invalid-id
  Scenario Outline: DELETE admin unsuccessfully delete profile customer by using string id
    Given user has already had login token as "admin"
    When admin send DELETE request to delete customer account with id "<id>"
    Then status response code should be 400
    And return body is matched with "failed_delete_customer.json" from "admin" schema

    Examples:
      | id  |
      | abc |
      | !!! |

  @negative @admin-delete-customer-nologin
  Scenario: DELETE admin unsuccessfully delete profile customer
    Given user hasn't already had login token
    When admin send DELETE request to delete customer account but not login
    Then status response code should be 401
    And return body is matched with "failed_delete_customer_nologin.json" from "admin" schema