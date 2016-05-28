Feature: Regression Tests

  # @testCaseId:6247
  Scenario: Verify user can access the Service Now application - @testCaseId:6247
    Given I am on hbs secure login page
    When I login with valid credentials
    Then I land on Service now home page


  # @testCaseId:6248
  Scenario: Verify user can access the Defect form - @testCaseId:6248
    Given I am on service now home page 
    When I type the term Defect in filter text box
    Then I see Defect module and other links inside the left pane window
    When I click on Create New link in the left navigation menu
    Then I see a new Defect form opens in the right pane enabling the user to enter a defect


  # @testCaseId:6249
  Scenario: Verify all the required fields of Defect form - @testCaseId:6249
    Given The defect creation form is open in the default state
    When I click on the Submit Button
    Then I observe that an error message pop-up window appears with the following text: The following mandatory fields are not filled in: Short description, Description, Environment, Application/Service
