Feature: User launches Service now application and is able to log in successfully 


 Scenario: Verify user can access the Service Now application
 	Given I am on hbs secure login page
	When I login with valid credentials
	Then I land on Service now home page