Feature: Error validation for blank submission of Defect Form

 Scenario: Verify all the required fields of Defect form
 	Given The defect creation form is open in the default state
	When I click on the Submit Button
	Then I observe that an error message pop-up window appears with the following text: The following mandatory fields are not filled in: Short description, Description, Environment, Application/Service
 