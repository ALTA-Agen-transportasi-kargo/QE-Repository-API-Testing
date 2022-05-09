@customer_createpayment
Feature: Create Payment API-customer

  @positive
  Scenario: customer success create payment
    Given user has already had login token as "customer"
    When customer send POST request to create payment for order id 15 And use "BANK_TRANSFER_BNI"
    Then status response code should be 200
    And return body is matched with "success_create_payment.json" from "customer" schema

  @negative
  Scenario Outline: customer unsuccess create payment by using invalid id
    Given user has already had login token as "customer"
    When customer send POST request to create payment for order id <id> And use "<BANK>"
    Then status response code should be 400
    And return body is matched with "failed_create_payment.json" from "customer" schema

    Examples:
    |id   |BANK               |
    |0    |BANK_TRANSFER_BNI  |
    |-1   |BANK_TRANSFER_BNI  |
    |23   |BANK_TRANSFER_BNI  |
    |26   |BANK_TRANSFER_BNI  |
    |15   |BANK_TRANSFER_BTPN |
    |15   |                   |

  @negative
  Scenario Outline: customer unsuccess create payment by using string id
    Given user has already had login token as "customer"
    When customer send POST request to create payment for order id "<id>" And use "<BANK>"
    Then status response code should be 400
    And return body is matched with "failed_create_payment.json" from "customer" schema

    Examples:
      |id  |BANK               |
      |A   |BANK_TRANSFER_BNI  |
      |!!  |BANK_TRANSFER_BNI  |

  @negative
  Scenario Outline: customer unsuccess create payment by using invalid credential
    Given user has already had login token as "<role>"
    When "<role>" send POST request to create payment for order id 15 And use "BANK_TRANSFER_BNI"
    Then status response code should be 401
    And return body is matched with "<schema>" from "customer" schema

    Examples:
      |role    |schema                      |
      |driver  |failed_create_payment.json  |
      |admin   |failed_create_payment.json  |
      |noLogin |nologin_failure.json        |





