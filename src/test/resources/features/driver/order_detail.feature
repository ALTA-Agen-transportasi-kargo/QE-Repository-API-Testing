@driver_detail_order
Feature: Order Detail API - Driver

  @positive
  Scenario: driver GET order detail successfully
    Given user has already had login token as "driver4"
    When driver send GET request order detail for id 23
    Then status response code should be 200
    And return body is matched with "success_detail_order.json" from "driver" schema

  @negative
  Scenario Outline: driver GET order detail unsuccessfully by using invalid id
    Given user has already had login token as "driver4"
    When driver send GET request order detail for id <id>
    Then status response code should be 400
    And return body is matched with "failed_detail_order.json" from "driver" schema

    Examples:
      | id | note      |
      | 0  | x         |
      | -1 | x         |
      | 30 | max id +1 |

  @negative
  Scenario Outline: driver GET order detail unsuccessfully by using string as id
    Given user has already had login token as "driver4"
    When driver send GET request order detail for id "<id>"
    Then status response code should be 400
    And return body is matched with "failed_detail_order.json" from "driver" schema

    Examples:
      | id |
      | a  |
      | <> |

  @negative
  Scenario Outline: driver GET order detail unsuccessfully by using invalid credentials
    Given user has already had login token as "<role>"
    When "<role>" send GET order detail
    Then status response code should be 401
    And return body is matched with "<schema>" from "driver" schema

    Examples:
      | role     | schema                   |
      | customer | failed_detail_order.json |
      | noLogin  | nologin_failure.json     |