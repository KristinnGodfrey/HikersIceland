Feature: Tests for AchievementController

  Background: login user
    * url 'https://hikers-of-iceland.herokuapp.com/rest/'
    * request { username: 'dummy', password: 'pass' }
    * path '/login'
    * method post
    * def authToken = response

  Scenario: Get all achievements
    Given path '/achievements'
    When method GET
    Then status 200
    And match response == { "name": "#string",  "description": "#string",  "difficulty": #number }

  Scenario: Get achievement with id
    Given path '/hikes/5/achievements/32'
    When method GET
    Then status 200
    And match response == { "name": "#string",  "description": "#string",  "difficulty": #number }

  Scenario: Post on achievement does not work if not logged in
    Given path '/hike'
    When method POST
    Then status 401

    #TODO achievement post on achievement if logged in (positive)
