Feature: Purchase feature
  I want to make a purchase on automationpractice website

  Scenario Outline: Purchase successfull scenario
    Given I want to go to automationpractice website
    And verify the mystore title
    When I click on sign in
    Then I should see the login page
    When I enter "<customer>" login credentials
    Then I should see the my account page
    When I choose the option women and choose tshirts
    Then I should see the tshirts section
    When I choose medium size
    And I add the faded short sleeve tshirt to the cart
    And I choose proceed to checkout on tshirts selection page
    Then I should see my order page
    When I choose proceed to checkout on order page
    And I choose the payment method cheque
    And I confirm my order
    Then I should see order confirmation page
    And validate the payment amount

    Examples: 
      | customer |
      | ishan  |
      
