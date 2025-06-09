Feature: Gilded Rose quality
  I want to know if the quality is updated properly

  Scenario: Checking if quality is updated
    Given The item as "foo" with quality 10
    When I update the quality
    Then The quality is updated to 8

