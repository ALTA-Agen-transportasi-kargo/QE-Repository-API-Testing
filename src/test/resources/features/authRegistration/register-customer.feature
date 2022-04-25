Feature: register customer

  Background:
    Given user is on registration page

    @positive
    Scenario: Success Register Customer
      When user send POST request with body register_customer_body.json
      Then status response code should be 201
      And return body is matched with success_register_customer.json

    @negative
    Scenario: Unsuccess Register Customer
      When user send POST request with body: "<body>"
      Then status response code should be 400
      And return body is matched with failed_register_customer.json

      Example:

        |body|
        | withoutemail_customer_body.json                |
        | withoutpass_customer_body.json                 |
        | withoutname_customer_body.json                 |
        | without_email_and_password_customer_body.json  |