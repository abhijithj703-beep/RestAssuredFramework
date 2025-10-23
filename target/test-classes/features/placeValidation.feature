Feature: Validating Place API's


#If you are using testdata by adding Examples keyword ,cahnge the Scenario keyword to Scenario Outline
@AddPlaceTest
Scenario Outline: Verify if Place is successfully added using AddPlaceAPI
	Given Add Place payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "POST" http Request
	Then API Call is success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created maps to "<name>" using "GetPlaceAPI" with "GET" http Request
	
#To add test data use below keyword(Example)
Examples:
  		|name	|language	|address	|
  		|ANBC	|English	|Hyderabad	|
  		|ABBBB	|Malayalam	|Kannur		|

@DeletePlaceTest
Scenario: Verify if Delete Place functionaily is working
	Given Delete Place payload
	When user calls "DeletePlaceAPI" with "POST" http Request
	Then API Call is success with status code 200
	And "status" in response body is "OK"
	

 