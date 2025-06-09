package com.gildedrose;

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




    private void updateItems(Item... items) {
        GildedRose gildedRose = new GildedRose(List.of(items));
        gildedRose.updateQuality();
    }

}