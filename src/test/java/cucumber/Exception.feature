@tag
Feature: Icorrect message on login page
  I want to use this template for my feature file

  @Exception
  Scenario Outline: Icorrect message on login
		Given I landed on Ecommerce page
    When Logged in with username <userName> and password <password>
    Then "Incorrect1 email or password." message is displayed on login page

    Examples: 
      |userName  					|password				|
      |p3211@gmail.com 		|P@ssword12345	|
      |test0901@gmail.com	|P@ssword12345	|
