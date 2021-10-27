Feature: Login saucedemo website
  As a user
  I want to login into my app
  So that

  Scenario Outline: Login successfully scenario
    And I want to go to saucedemo website
    When I login saucedemo with "<user>"


    Examples:
      | user |
      | standard  |
      
