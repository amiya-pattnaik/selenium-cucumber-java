Feature: Automate Register and Login APIs for https://reqres.in/

Scenario: Register user for https://reqres.in/ using valid details
	Given the user have proper register request data 
	When the user sents a POST request to register API with valid request 
	Then register API should have status code as 201 and content-type as JSON 
	And the register API should return proper json response 