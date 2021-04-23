Feature: user

  Background: login user
    * url 'https://hikers-of-iceland.herokuapp.com/rest/'
    * request { username: 'hikeadmin', password: 'admin' }
    * path '/login'
    * method post
    * def authToken = response

  Scenario: If the user is logged in he should get a reponse with his data
    Given path '/profile'
    When method GET
    Then status 200
    And match response ==
    """
    {
      "id": "#number",
      "password": "#string",
      "name": "#string",
      "admin": "#boolean",
      "completedAchievements": "#array",
      "age": "#number",
      "username": "#string"
     }
     """

  @ignore
  Scenario: patch age on profile should return updated profile
    Given path '/profile'
    When method PATCH
    And request { 'age': '998877' }
    Then status 200
    And match response.age == 998877
