@bringeee-login
Feature: Login

  @positive @login-users
  Scenario Outline: user success login
    When user send POST login request as "<actor>"
    Then status response code should be 200
    And return body is matched with "<schema>" from "authRegistration" schema
  Examples:
    | actor           | schema                      |
    | customer        | success_login_customer.json |
    | driver_verified | success_login_driver.json   |
    | admin           | success_login_admin.json    |

  @negative @login-users
  Scenario Outline: user unsuccess login
    When user send POST login request as "<case>"
    Then status response code should be <response>
    And return body is matched with "failed_login.json" from "authRegistration" schema

  Examples:
    | case               | response |
    | driver_pending     | 403      |
    | no_email           | 401      |
    | no_password        | 401      |
    | no_email_password  | 401      |
    | unregistered_email | 401      |

  @positive @get-auth
  Scenario Outline: user success get user auth data
    Given user has already had login token as "<actor>"
    When user send GET request to see the current user data
    Then status response code should be 200
    And user role should match with "<actor>"
    And return body is matched with "<schema>" from "authRegistration" schema

  Examples:
    | actor           | schema                      |
    | customer        | success_login_customer.json |
    | driver_verified | success_login_driver.json   |
    | admin           | success_login_admin.json    |

  @negative @get-auth
  Scenario: user unsuccess get user auth data
      Given user hasn't already had login token
      When user send GET request to see the current user data
      Then status response code should be 403
      And return body is matched with "failed-login.json" from "authRegistration" schema
