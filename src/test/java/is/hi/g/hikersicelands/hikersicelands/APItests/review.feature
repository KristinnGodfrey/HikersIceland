Feature: API tester

#  @ignore """Cant delete the specific review we make automatically"""
#  Scenario: Passes if it can create a review
#    Given url 'https://hikers-of-iceland.herokuapp.com/rest/hikes/5/reviews'
#    And request { "userId": null,"reviewText": "Test case that creates reviews", "rating": 5 }
#    When method POST
#    And match response.reviews[0].reviewText == "Test case that creates reviews"

  #TODO userId should not be not null
  Scenario: Get reviews with hike id
    Given url 'https://hikers-of-iceland.herokuapp.com/rest/hikes/2/reviews'
    When method GET
    Then status 200
    And match each response[*].userId == '##number'
    And match each response[*].reviewText == '#string'
    And match each response[*].rating == '#number'

  #Its possible to delete reviews even though you are not logged in which is not intentional
#  @ignore
  Scenario: DELETE on review should return 401 status if not logged in
    Given url 'https://hikers-of-iceland.herokuapp.com/rest/hikes/5/reviews/32'
    When method DELETE
    Then status 401
