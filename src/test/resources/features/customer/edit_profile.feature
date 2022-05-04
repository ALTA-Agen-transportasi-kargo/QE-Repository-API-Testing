@customer-edit-profile
Feature: Edit Customer API - customer

  @positive @success-edit
  Scenario: PUT customer success edit profile
    Given user has already had login token as "customer"
    When customer send PUT request to edit profile
    Then status response code should be 200
    And return body is matched with "success_edit_profile.json" from "customer" schema

  @negative @failed-edit
  Scenario Outline: PUT customer unsuccess edit profile
    Given user has already had login token as "customer"
    When customer send PUT request to edit profile without condition: "<condition>"
    Then status response code should be 400
    And return body is matched with "failed_edit_profile.json" from "customer" schema

    Examples:
      | condition           |
      | without_alldata     |


  @negative @failed-edit-not-login
  Scenario: PUT customer unsuccess edit profile by not logging in
    Given user hasn't already had login token
    When customer send PUT request to edit profile but not log in
    Then status response code should be 401
    And return body is matched with "failed_edit_profile_noToken.json" from "customer" schema