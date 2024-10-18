
@Regression
Feature: Purchace order from Ecommerce website
  I want to use this template for my feature file

  Background:
  Given I landed on Ecommerce page
  @tag2
  Scenario Outline: Postive test to submit the order
    Given Logged in with username <userName> and password <password>
    When I add a product <productName> in cart
    And checkout <productName> and submit order
    Then "Thankyou for the order." message is displayed on confirmation page

    Examples: 
      |userName  						|password			|productName  		|
      |p321@gmail.com 			|P@ssword123	|ZARA COAT 3 			|
      |test090@gmail.com 		|P@ssword1234 |ADIDAS ORIGINAL  |
