Feature:

  Scenario Outline: Login into Songs By Sinatra Page
    Given I navigate to Songs By Sinatra Page
    When I enter valid <user> and <password>
    Then I can see Songs By Sinatra Main Page

    Examples:
      | user  | password |
      | frank | sinatra  |