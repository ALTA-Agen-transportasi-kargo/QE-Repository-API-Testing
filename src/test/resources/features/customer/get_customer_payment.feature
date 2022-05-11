@list_cust_payment
Feature: GET list customer payment

  @positive
  Scenario: GET success customer payment detail
    Given user has already had login token as "customer"
    When customer send GET request to show customer payment detail for id 15
    Then status response code should be 200
    And return body is matched with "success_cust_payment_detail.json" from "customer" schema

  @negative
  Scenario Outline: GET unsuccess customer payment detail by invalid id
    Given user has already had login token as "customer"
    When customer send GET request to show customer payment detail for id <id>
    Then status response code should be 400
    And return body is matched with "failed_list_customer_payment.json" from "customer" schema

    Examples:
      | id | note               |
      | 0  |                    |
      | -1 |                    |
      | 8  | total records + 27 |

  @negative
  Scenario Outline: GET unsuccess customer payment detail by string id
    Given user has already had login token as "customer"
    When customer send GET request to show customer payment detail for id "<string>"
    Then status response code should be 400
    And return body is matched with "failed_list_customer_payment.json" from "customer" schema

    Examples:
      | string |
      | abc    |
      | $%^    |

  @negative
  Scenario Outline: GET unsuccessfully customer payment detail by using invalid credentials
    Given user has already had login token as "<role>"
    When "<role>" send GET request show customer payment detail for id 15
    Then status response code should be 401
    And return body is matched with "<schema>" from "customer" schema

    Examples:
      | role     | schema                            |
      | admin    | failed_list_customer_payment.json |
      | driver4   | failed_list_customer_payment.json |
      | noLogin  | nologin_failure.json              |