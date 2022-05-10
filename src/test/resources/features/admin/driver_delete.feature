@admin-delete-driver-profile
Feature: Delete Profile Driver API - Admin

  @positive
  Scenario: DELETE admin success delete profile driver
    Given user has already had login token as "admin"
    When admin send DELETE request to delete driver account with id 15
    Then status response code should be 200
    And return body is matched with "success_delete_driver.json" from "admin" schema

  @negative @admin-delete-driver-invalid-id
  Scenario Outline: DELETE admin unsuccessfully delete profile driver by using invalid id
    Given user has already had login token as "admin"
    When admin send DELETE request to delete driver account with id <id>
    Then status response code should be 400
    And return body is matched with "failed_delete_driver.json" from "admin" schema

    Examples:
      | id | note       |
      | 0  |            |
      | -1 |            |
      | 16 | max id + 1 |

  @negative @admin-delete-driver-invalid-id
  Scenario Outline: DELETE admin unsuccessfully delete profile driver by using string id
    Given user has already had login token as "admin"
    When admin send DELETE request to delete driver account with id "<id>"
    Then status response code should be 400
    And return body is matched with "failed_delete_driver.json" from "admin" schema

    Examples:
      | id  |
      | abc |
      | !!! |

  @negative @admin-delete-driver-nologin
  Scenario: DELETE admin unsuccessfully delete profile driver
    Given user hasn't already had login token
    When admin send DELETE request to delete driver account but not login
    Then status response code should be 401
    And return body is matched with "failed_delete_driver_nologin.json" from "admin" schema