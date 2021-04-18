Feature: user

  Background: login user
    * url 'https://hikers-of-iceland.herokuapp.com/rest/'

  @ignore
  Scenario: a user can create his profile
    Given path '/signup'
    And request { "username":"#(username)", "password":"#(password)" }
    When method POST
    Then status 200

  Scenario: a user can log in to his profile
    Given path '/login'
    And request { "username": "#string", "password": "#string" }
    When method POST
    Then status 200

  Scenario: log in failure should return a 401 status
    Given path '/login'
    And request { "username": "wronginfo", "password": "wrongpass" }
    When method POST
    Then status 401
