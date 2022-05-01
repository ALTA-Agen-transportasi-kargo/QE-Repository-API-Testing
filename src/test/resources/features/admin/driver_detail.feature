@admin_driver_detail @admin_get_driver_customer
Feature: Driver Detail API - Admin

  @positive
  Scenario: GET success driver detail
    Given user has already had login token as "admin"
    When admin send GET request to show driver detail for id 2
    Then status response code should be 200
    And response data id should be match with 2 as requested
    And return body is matched with "success_driver_detail.json" from "admin" schema

  @negative
  Scenario Outline: Get unsuccess driver detail by using invalid id
    Given user has already had login token as "admin"
    When admin send GET request to show driver detail for id <id>
    Then status response code should be 400
    And return body is matched with "failed_driver_detail.json" from "admin" schema

    Examples:
      | id |
      | 0  |
      | -1 |
      | 5  |

  Scenario Outline: Get unsuccess driver detail by using string id
    Given user has already had login token as "admin"
    When admin send GET request to show driver detail for id "<id>"
    Then status response code should be 400
    And return body is matched with "failed_driver_detail.json" from "admin" schema

    Examples:
      | id |
      | a  |
      | %& |

  Scenario Outline: Get unsuccess driver detail by using string id
    Given user has already had login token as "<role>"
    When "<role>" send GET request to show driver detail for id 4
    Then status response code should be 401
    And return body is matched with "<schema>" from "admin" schema

    Examples:
      | role     | schema                            |
      | customer | failed_driver_detail.json         |
      | driver   | failed_driver_detail.json         |
      | noLogin  | failed_driver_detail_nologin.json |