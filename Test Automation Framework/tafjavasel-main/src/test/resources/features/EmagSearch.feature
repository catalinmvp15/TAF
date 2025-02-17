Feature: eMAG Search Functionality

  Scenario: User searches for a product on eMAG
    Given I open the eMAG website
    When I search for "laptop"
    Then I should see search results
