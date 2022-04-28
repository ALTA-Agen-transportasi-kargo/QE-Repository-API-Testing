@wilayahAPI
Feature: Wilayah API - Provinces

  @positive @all-provinces
  Scenario: GET success all provinces
    When user send GET request to show all provinces
    Then status response code should be 200
    And "data[0].ProvName" should contain "ACEH"
    And return body is matched with "success_all_provinces.json" from "wilayah" schema