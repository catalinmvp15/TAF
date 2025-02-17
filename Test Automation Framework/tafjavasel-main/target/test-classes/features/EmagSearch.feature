Feature: eMAG Search Functionality

  Scenario: User searches for a product on eMAG
    Given I open the eMAG website
    When I search for "laptop""laptop"
    Then I should see search results


  @AN
  Scenario: Căutare și extragere detalii produse eMAG
    Given I open the eMAG website
    When I search for "laptop gaming"
    Then I extract and display the product list with prices
    And I extract and display the product ratings and reviews
