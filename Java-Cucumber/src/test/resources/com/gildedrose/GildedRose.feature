Feature: Gilded Rose quality
  I want to know if the quality is updated properly

  Scenario: Checking if quality is updated
    Given The item as "foo" with quality 10
    When 1 days pass
    Then The quality is updated to 8

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
      | Conjured Mana Cake                        | 3      | 6       |
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
      | Conjured Mana Cake                        | 2      | 5       |
