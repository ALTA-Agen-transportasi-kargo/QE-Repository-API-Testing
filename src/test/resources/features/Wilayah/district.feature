@wilayahAPI
Feature: Wilayah API - Districts

  Background:
    Given user has set province to DKI JAKARTA - id 11

  @positive @all-districts
  Scenario: GET success all districts by cities id in DKI JAKARTA
    When user send GET request to show all districts for city id: 152
    Then status response code should be 200
    And "data[0].DisName" should contain "PADEMANGAN"
    And return body is matched with "success_all_districts.json" from "wilayah" schema

  @negative @all-districts
  Scenario Outline: GET unsuccess all districts by cities id in DKI JAKARTA by invalid id
    When user send GET request to show all districts for city id: "<invalid_id>"
    Then status response code should be 400
    And return body is matched with "error_wilayah.json" from "wilayah" schema

    Examples:
      | invalid_id | note                                    |
      | 0          |                                         |
      | -1         |                                         |
      | 151        | id-1 dari minimum cityId di DKI Jakarta |
      | 158        | id+1 dari maximum cityId di DKI Jakarta |

  @negative @all-districts
  Scenario Outline: GET unsuccess all districts by cities id in DKI JAKARTA by string id
    When user send GET request to show all districts for city id: "<invalid_id>"
    Then status response code should be 400
    And return body is matched with "error_wilayah.json" from "wilayah" schema

    Examples:
      | invalid_id |
      | a          |
      | !@         |
      | string     |
