@wilayahAPI
Feature: Wilayah API - Cities

  @positive @all-cities
  Scenario: GET success all cities by province id
    When user send GET request to show all cities for province id: 11
    Then status response code should be 200
    And "data[0].CityName" should contain "JAKARTA UTARA"
    And return body is matched with "success_all_cities.json" from "wilayah" schema

  @negative @all-cities
  Scenario Outline: GET unsuccess all cities by province Id with invalid id
    When user send GET request to show all cities for province id: "<invalid_id>"
    Then status response code should be 400
    And return body is matched with "error_wilayah.json" from "wilayah" schema

  Examples:
    | invalid_id |
    | 0          |
    | -1         |
    | 35         |

  @negative @all-cities
  Scenario Outline: GET unsuccess all cities by province id with string id
    When user send GET request to show all cities for province id: "<invalid_id>"
    Then status response code should be 400
    And return body is matched with "error_wilayah.json" from "wilayah" schema

    Examples:
      | invalid_id |
      | a          |
      | !@         |
      | string     |
