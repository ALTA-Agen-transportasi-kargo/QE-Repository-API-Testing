@customer-delete-profile
Feature: Delete Profile API - Customer

  @positif
  Scenario: Customer success delete profile
    When customer send DELETE request to delete account
    Then status response code should be 200
    And return body is matched with "success_delete_profile.json" from "customer" schema

  @negatif
  Scenario: Customer unsuccess delete profile
    When customer send DELETE request to delete account but not login
    Then status response code should be 401
    And return body is matched with "failed_delete_profile.json" from "customer" schema