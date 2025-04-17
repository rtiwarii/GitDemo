Feature: Validating Place API

  @AddPlace @Regression
  Scenario Outline: Verify if place successfully added using AddPlaceAPI
    Given Add place payload with "<name>" "<address>" "<language>"
    When user calls "AddPlaceAPI" with "Post" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created for "<name>" using "GetPlaceAPI"

    Examples: 
      | name                 | address                     | language |
      | Coimbatore house     | 29, side layout, Coimbatore | English  |
      #| Coimbatore house old | 92, side layout, Coimbatore | French   |

  @DeletePlace @Regression
  Scenario: Verify if place successfully deleted using DeletePlaceAPI
    Given Add Delete place payload
    When user calls "DeletePlaceAPI" with "Post" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
