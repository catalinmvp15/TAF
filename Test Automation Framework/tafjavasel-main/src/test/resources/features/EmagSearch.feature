Feature: eMAG Search Functionality

  Scenario: User searches for a product on eMAG
    Given I open the eMAG website
    When I search for "laptop"
    Then I should see search results


  @AN
  Scenario: User searches for a product on eMAG scrap
    Given I open the eMAG website
    When I search for "G614JVR-N4081"
    When I select the first product
    Then I extract and display the product list with prices
