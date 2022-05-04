@customer-delete-profile
Feature: Delete Profile API - Customer

  @positif @customer-delete-with-login
  Scenario: DELETE customer success delete profile
    Given user has already had login token as "customer"
    When customer send DELETE request to delete account
    Then status response code should be 200
    And return body is matched with "success_delete_profile.json" from "customer" schema

  @negatif @customer-delete-no-login
  Scenario: DELETE customer unsuccess delete profile
    Given user hasn't already had login token
    When customer send DELETE request to delete account but not login
    Then status response code should be 401
    And return body is matched with "failed_delete_profile.json" from "customer" schema