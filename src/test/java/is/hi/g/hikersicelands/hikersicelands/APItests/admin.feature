Feature: admin

  Background: login admin
    * url 'https://hikers-of-iceland.herokuapp.com/rest/'
    * request { username: 'hikeadmin', password: 'admin' }
    * path '/login'
    * method post
    * def authToken = response

   #TODO create new hike to try this test case
  @ignore
  Scenario: delete hike
    Given path '/hike/4'
    When method DELETE
    Then status 200

  @ignore
  Scenario: patch age on other profiles should return updated profile
    Given path '/profile/20'
    When method PATCH
    And request { 'age': '12345678' }
    Then status 200
    And match response.age == 12345678

  #TODO this is not implemented and doesn't patch but test passes
  Scenario: patch hike
    Given path '/hikes/5'
    And request { 'location': 'suðurland' }
    When method PATCH
    Then status 200
