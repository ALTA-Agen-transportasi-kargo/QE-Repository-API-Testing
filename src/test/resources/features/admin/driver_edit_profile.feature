@admin-edit-driver-profile
Feature: Edit Driver API - Admin

  @positive @success-edit-driver
  Scenario: PUT admin success edit profile driver
    Given user has already had login token as "admin"
    When admin send PUT request to edit profile driver for id 14
    Then status response code should be 200
    And return body is matched with "success_edit_profile_driver.json" from "admin" schema



  @negative @failed-edit-driver-invalid-id
  Scenario Outline: PUT admin unsuccess edit profile driver by using invalid id
    Given user has already had login token as "admin"
    When admin send PUT request to edit profile driver for id <id>
    Then status response code should be 500
    And return body is matched with "failed_edit_profile_driver.json" from "admin" schema

    Examples:
      | id | note      |
      | 0  | x         |
      | -1 | x         |
      | 20 | max id +1 |



  @negative @failed-edit-not-login-driver
  Scenario: PUT admin unsuccess edit profile by not logging in
    Given user hasn't already had login token
    When admin send PUT request to edit profile driver but not log in
    Then status response code should be 401
    And return body is matched with "failed_edit_profile_driver_noToken.json" from "admin" schema