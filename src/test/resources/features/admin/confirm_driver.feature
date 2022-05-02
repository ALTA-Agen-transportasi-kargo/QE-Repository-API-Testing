@admin_confirm_driver
Feature: Confirm Driver API - Admin

  @positive
  Scenario: POST Admin confirm driver account successfully
    Given user has already had login token as "admin"
    When admin send POST request to confirm driver with id 10
    Then status response code should be 200
    And return body is matched with "success_confirm_driver.json" from "admin" schema

  @negative
  Scenario Outline: POST admin confirm driver account unsuccessfully by using invalid id
    Given user has already had login token as "admin"
    When admin send POST request to confirm driver with id <id>
    Then status response code should be 400
    And return body is matched with "failed_confirm_driver.json" from "admin" schema

  Examples:
    | id | note       |
    | 0  |            |
    | -1 |            |
    | 12 | max id + 1 |

  @negative
  Scenario Outline: POST admin confirm driver account unsuccessfully by using string id
    Given user has already had login token as "admin"
    When admin send POST request to confirm driver with id "<id>"
    Then status response code should be 400
    And return body is matched with "failed_confirm_driver.json" from "admin" schema

    Examples:
      | id  |
      | abc |
      | !!! |

  @negative
  Scenario Outline: POST admin confirm driver account unsuccessfully by using invalid credentials
    Given user has already had login token as "<role>"
    When "<role>" send POST request to confirm driver
    Then status response code should be 401
    And return body is matched with "<schema>" from "admin" schema

    Examples:
      | role     | schema                     |
      | customer | failed_confirm_driver.json |
      | driver   | failed_confirm_driver.json |
      | noLogin  | nologin_failure.json       |