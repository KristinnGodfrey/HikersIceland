Feature: hike

  Background: login user
    * url 'https://hikers-of-iceland.herokuapp.com/rest/'
    * request { username: 'hikeuser', password: 'user' }
    * path '/login'
    * method post
    * def authToken = response

  Scenario: get on hike should return a list of all hikes
    Given path '/hikes/'
    When method GET
    Then status 200
    And match response == '#[]'

  Scenario: Get hikes with id
    Given url 'https://hikers-of-iceland.herokuapp.com/rest/hikes/2/'
    When method GET
    Then status 200
    And match response ==
    """
    {
      "id": "#number",
      "achievements": "#array",
      "items": "#array",
      "reviews": "#array",
      "name": "#string",
      "description": "#string",
      "location": "#string",
      "image": "##string"
    }
    """

  Scenario: get on invalid hike url should return a 404 status
    Given path '/hikes/123456'
    When method GET
    Then status 404






