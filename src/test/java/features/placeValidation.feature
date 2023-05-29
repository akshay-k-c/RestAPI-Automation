
Feature: validate google place apis

 @addPlace @Regression @API @test
  Scenario Outline: Add a place using add place api and validate response
    Given add place payload with <Accuracy> "<name>" "<phone>" values 
    When user calls with "post" request by passing "addPlaceAPI"
    Then validate the response code as 200
    And check "status" in the resposne body is "OK"
    And check "scope" in the respose as "APP"
    And Verify the name from get response as "<name>" using "getPlaceAPI"
    

 Examples:
 | Accuracy   |  name     | phone                |
 |    40      | akshay    | (+91) 973 893 3937   |
# |    50      | david     | (+91) 456 893 3937   |

@deletePlace 
Scenario: delete a place using delete api and validate response
    Given delete the place by passing payload
    When user calls with "delete" request by passing "deletePlaceAPI"
    Then validate the response code as 200
    And check "status" in the resposne body is "OK"
    
    
 @jsonBody
Scenario: Add a place by passing json directly
Given add place by passing "AddPlace.json" request
 When user calls with "post" request by passing "addPlaceAPI"
 Then validate the response code as 200
 And check "status" in the resposne body is "OK"
 And check "scope" in the respose as "APP"
 

