@bringeee-login
Feature: Login API

  @positive
  Scenario: user success login
    When user send POST request with body: login_body.json
    Then status response code should be 200
    And return body is matched with success-login.json

  @positive
  Scenario: user success get user auth data
    Given user has already had login token
    When user send GET request to see the current user data
    Then status response code should be 200
    And return body is matched with success-login.json

  @negative
  Scenario Outline: user unsuccess login
    When user send POST request with body: "<body>"
    Then status response code should be 400
    And return body is matched with failed-login.json

  Examples:
    | body                             |
    | no_email_login.json              |
    | no_password_login.json           |
    | no_email_password_login          |
    | unregistered_email_login.json    |
    | unregistered_password_login.json |
    | unregistered_login.json          |

    @negative
    Scenario: user unsuccess get user auth data
      Given user hasn't already had login token
      When user send GET request to see the current user data
      Then status response code should be 400
      And return body is matched with failed-login.json
