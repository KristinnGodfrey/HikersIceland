Feature: hike

  Background: login user
    * url 'https://hikers-of-iceland.herokuapp.com/rest/'
    * request { username: 'hikeuser', password: 'user' }
    * path '/login'
    * method post
    * def authToken = response

    #TODO response
  Scenario: get on hike should return a list of all hikes
    Given path '/hikes/'
    When method GET
    Then status 200

    #TODO response
  Scenario: get on specific hike should return the specified hike
    Given path '/hikes/5'
    When method GET
    Then status 200

  Scenario: get on invalid hike url should return a 404 status
    Given path '/hikes/123456'
    When method GET
    Then status 404
