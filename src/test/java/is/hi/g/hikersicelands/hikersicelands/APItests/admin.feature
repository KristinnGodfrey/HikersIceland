Feature: admin

  Background: login admin
    * url 'https://hikers-of-iceland.herokuapp.com/rest/'
    * request { username: 'hikeadmin', password: 'admin' }
    * path '/login'
    * method post
    * def authToken = response

  @ignore
  Scenario: patch age on other profiles should return updated profile
    Given path '/profile/20'
    When method PATCH
    And request { 'age': '12345678' }
    Then status 200
    And match response.age == 12345678
