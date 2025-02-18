@an
Feature: Altex flow

    Scenario Outline: Altex search flow
        Given I am on the Altex homepage
        When I search for a product with ID "<productID>"
        Then I should see the product details


    Examples: 
        |   productID        |
        |   JOCPS5FC25       |