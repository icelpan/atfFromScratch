Feature: Wikipedia Search Functionality

  @Search
  Scenario: User can search for a term on Wikipedia
    Given user is on the Wikipedia home page
    When user searches for "Test Automation Framework"
    Then the article for "Test Automation Framework" should be displayed