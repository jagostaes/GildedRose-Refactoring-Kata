package com.gildedrose;

import com.gildedrose.domain.Item;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GildedRoseTest {

    @Test
    void whenDayPasses_sellInAndQualityDecrease() {
        Item item = new Item("Elixir of the Mongoose", 5, 7);

        updateItems(item);

        assertThat(item.sellIn).isEqualTo(4);
        assertThat(item.quality).isEqualTo(6);
    }

    @Test
    void whenSellInIsNegative_qualityDecreasedTwiceAsFast() {
        Item item = new Item("Elixir of the Mongoose", 0, 10);

        updateItems(item);

        assertThat(item.sellIn).isEqualTo(-1);
        assertThat(item.quality).isEqualTo(8);
    }

    @Test
    void whenQualityIsZero_qualityDoesNotDecrease() {
        Item item = new Item("Elixir of the Mongoose", 5, 0);

        updateItems(item);

        assertThat(item.sellIn).isEqualTo(4);
        assertThat(item.quality).isEqualTo(0);
    }

    @Test
    void whenItemIsAgedBrie_qualityIncreases() {
        Item item = new Item("Aged Brie", 5, 0);

        updateItems(item);

        assertThat(item.sellIn).isEqualTo(4);
        assertThat(item.quality).isEqualTo(1);
    }

    @Test
    void whenQualityIs50_qualityStopsIncreasing() {
        Item item = new Item("Aged Brie", 5, 50);

        updateItems(item);

        assertThat(item.sellIn).isEqualTo(4);
        assertThat(item.quality).isEqualTo(50);
    }

    @Test
    void whenItemIsSulfuras_sellInAndQualityDoNotDecrease() {
        Item item = new Item("Sulfuras, Hand of Ragnaros", 10, 80);

        updateItems(item);

        assertThat(item.sellIn).isEqualTo(10);
        assertThat(item.quality).isEqualTo(80);
    }

    @Test
    void whenItemIsBackstagePassAndSellInIsOver10_qualityIncreasesBy1() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 10);

        updateItems(item);

        assertThat(item.sellIn).isEqualTo(14);
        assertThat(item.quality).isEqualTo(11);
    }

    @Test
    void whenItemIsBackstagePassAndSellInIsBetween5And10_qualityIncreasesBy2() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 8, 10);

        updateItems(item);

        assertThat(item.sellIn).isEqualTo(7);
        assertThat(item.quality).isEqualTo(12);
    }

    @Test
    void whenItemIsBackstagePassAndSellInIsBetween0And5_qualityIncreasesBy3() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 4, 10);

        updateItems(item);

        assertThat(item.sellIn).isEqualTo(3);
        assertThat(item.quality).isEqualTo(13);
    }

    @Test
    void whenItemIsBackstagePassAndSellInExpires_qualityDropsTo0() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10);

        updateItems(item);

        assertThat(item.sellIn).isEqualTo(-1);
        assertThat(item.quality).isEqualTo(0);
    }

    @Test
    void whenDayPasses_allItemsAreUpdatedCorrectly() {
        Item[] items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                // fixme: this conjured item does not work properly yet
                new Item("Conjured Mana Cake", 3, 6)};

        updateItems(items);

        assertThat(items[0].name).isEqualTo("+5 Dexterity Vest");
        assertThat(items[0].sellIn).isEqualTo(9);
        assertThat(items[0].quality).isEqualTo(19);

        assertThat(items[1].name).isEqualTo("Aged Brie");
        assertThat(items[1].sellIn).isEqualTo(1);
        assertThat(items[1].quality).isEqualTo(1);

        assertThat(items[2].name).isEqualTo("Elixir of the Mongoose");
        assertThat(items[2].sellIn).isEqualTo(4);
        assertThat(items[2].quality).isEqualTo(6);

        assertThat(items[3].name).isEqualTo("Sulfuras, Hand of Ragnaros");
        assertThat(items[3].sellIn).isEqualTo(0);
        assertThat(items[3].quality).isEqualTo(80);

        assertThat(items[4].name).isEqualTo("Sulfuras, Hand of Ragnaros");
        assertThat(items[4].sellIn).isEqualTo(-1);
        assertThat(items[4].quality).isEqualTo(80);

        assertThat(items[5].name).isEqualTo("Backstage passes to a TAFKAL80ETC concert");
        assertThat(items[5].sellIn).isEqualTo(14);
        assertThat(items[5].quality).isEqualTo(21);

        assertThat(items[6].name).isEqualTo("Backstage passes to a TAFKAL80ETC concert");
        assertThat(items[6].sellIn).isEqualTo(9);
        assertThat(items[6].quality).isEqualTo(50);

        assertThat(items[7].name).isEqualTo("Backstage passes to a TAFKAL80ETC concert");
        assertThat(items[7].sellIn).isEqualTo(4);
        assertThat(items[7].quality).isEqualTo(50);

        // todo: assert conjured item after implementation
    }


    private void updateItems(Item... items) {
        GildedRose gildedRose = new GildedRose(List.of(items));
        gildedRose.updateQuality();
    }

}