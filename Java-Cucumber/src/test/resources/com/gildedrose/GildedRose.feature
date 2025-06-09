Feature: Gilded Rose quality
  I want to know if the quality is updated properly

  Scenario: Checking if quality is updated
    Given the item as "Elixir of the Mongoose" with sellIn 0 and quality 10
    When 1 days pass
    Then the quality is updated to 8

  Scenario: Items have name, sellIn, and quality attributes
    Given a variety of items:
      | name                                      | sellIn | quality |
      | +5 Dexterity Vest                         | 10     | 20      |
      | Aged Brie                                 | 2      | 0       |
      | Elixir of the Mongoose                    | 5      | 7       |
      | Sulfuras, Hand of Ragnaros                | 0      | 80      |
      | Sulfuras, Hand of Ragnaros                | -1     | 80      |
      | Backstage passes to a TAFKAL80ETC concert | 15     | 20      |
      | Backstage passes to a TAFKAL80ETC concert | 10     | 49      |
      | Backstage passes to a TAFKAL80ETC concert | 5      | 49      |
    When 1 days pass
    Then the items should have the following values:
      | name                                      | sellIn | quality |
      | +5 Dexterity Vest                         | 9      | 19      |
      | Aged Brie                                 | 1      | 1       |
      | Elixir of the Mongoose                    | 4      | 6       |
      | Sulfuras, Hand of Ragnaros                | 0      | 80      |
      | Sulfuras, Hand of Ragnaros                | -1     | 80      |
      | Backstage passes to a TAFKAL80ETC concert | 14     | 21      |
      | Backstage passes to a TAFKAL80ETC concert | 9      | 50      |
      | Backstage passes to a TAFKAL80ETC concert | 4      | 50      |

  Scenario: Quality of an item can never negative
    Given the item as "Elixir of the Mongoose" with sellIn 0 and quality 0
    When 1 days pass
    Then the quality is updated to 0

  Scenario: Aged Brie increased in quality the older it gets
    Given the item as "Aged Brie" with sellIn 0 and quality 0
    When 3 days pass
    Then the quality is updated to 6

  Scenario: The quality of an item is never more than 50
    Given the item as "Aged Brie" with sellIn 0 and quality 50
    When 1 days pass
    Then the quality is updated to 50

  Scenario: Sulfuras sellIn and quality don't change
    Given the item as "Sulfuras, Hand of Ragnaros" with sellIn 10 and quality 80
    When 1 days pass
    Then the quality is updated to 80
    And the sellIn is updated to 10

  Scenario: Backstage passes increase value by 2 when there are between 10 and 5 days remaining
    Given the item as "Backstage passes to a TAFKAL80ETC concert" with sellIn 8 and quality 10
    When 1 days pass
    Then the quality is updated to 12
    And the sellIn is updated to 7

  Scenario: Backstage passes increase value by 3 when there are less than 5 days remaining
    Given the item as "Backstage passes to a TAFKAL80ETC concert" with sellIn 3 and quality 10
    When 1 days pass
    Then the quality is updated to 13
    And the sellIn is updated to 2

  Scenario: Backstage passes quality drops to 0 after the concert
    Given the item as "Backstage passes to a TAFKAL80ETC concert" with sellIn 0 and quality 10
    When 1 days pass
    Then the quality is updated to 0
    And the sellIn is updated to -1
