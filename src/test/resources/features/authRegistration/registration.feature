@registrationAPI
Feature: Registration API

    @positive
    Scenario Outline: POST success registration
      When user send POST request to register as "<role>"
      Then status response code should be 201
      And return body is matched with "<schema>" from "authRegistration" schema

      Examples:
        | role     | schema                    |
        | customer | success_reg_customer.json |
        | driver   | success_reg_driver.json   |


    @negative
    Scenario Outline: POST failed registration
      When user send POST request to register as "<role>" with case: "<case>"
      Then status response code should be <code>
      And return body is matched with "<schema>" from "authRegistration" schema

      Examples:
        | role     | case                   | schema                   | code |
        | customer | without_email_password | failed_registration.json | 400  |
        | customer | without_name_gender    | failed_registration.json | 400  |
        | customer | with_registered_email  | failed_regis_500.json    | 500  |
        | driver   | without_email_password | failed_registration.json | 400  |
        | driver   | without_stnk           | failed_registration.json | 400  |
        | driver   | with_registered_email  | failed_regis_500.json    | 500  |