Feature: User access Defect management area in service now application

 Scenario: Verify user can access the Defect form
	Given I am on service now home page 
	When I type the term Defect in filter text box
	Then I see Defect module and other links inside the left pane window
	When I click on Create New link in the left navigation menu
	Then I see a new Defect form opens in the right pane enabling the user to enter a defect