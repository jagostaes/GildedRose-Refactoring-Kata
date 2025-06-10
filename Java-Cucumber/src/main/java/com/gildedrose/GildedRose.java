package com.gildedrose;

import com.gildedrose.domain.*;

import java.util.List;

class GildedRose {
    List<Item> items;

    public GildedRose(List<Item> items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!isSulfuras(item) && !isAgedBrie(item) && !isBackstagePasses(item) && !isRegularItem(item)) {
                decreaseSellIn(item);
            }

            if (isRegularItem(item)) {
                ItemUpdater itemUpdater = new RegularItemUpdater(item);
                itemUpdater.updateItem();
            } else if (isBackstagePasses(item)) {
                ItemUpdater updater = new BackstagePassesUpdater(item);
                updater.updateItem();
            } else if (isAgedBrie(item)) {
                ItemUpdater updater = new AgedBrieUpdater(item);
                updater.updateItem();
            }
        }
    }

    private static void decreaseSellIn(Item item) {
        item.sellIn--;
    }

    private static boolean isRegularItem(Item item) {
        return !isSulfuras(item) && !isBackstagePasses(item) && !isAgedBrie(item);
    }

    private static boolean isSulfuras(Item item) {
        return item.name.startsWith("Sulfuras");
    }

    private static boolean isBackstagePasses(Item item) {
        return item.name.startsWith("Backstage passes");
    }

    private static boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }

    public List<Item> getItems() {
        return items;
    }
}

