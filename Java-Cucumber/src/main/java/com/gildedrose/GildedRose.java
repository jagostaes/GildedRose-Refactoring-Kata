package com.gildedrose;

import com.gildedrose.domain.Item;

import java.util.List;

import static java.lang.Integer.valueOf;

class GildedRose {
    List<Item> items;

    public GildedRose(List<Item> items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!isSulfuras(item)) {
                decreaseSellIn(item);
            }

            if (isRegularItem(item) && item.quality > 0) {
                decreaseQuality(item, 1);
            } else if (isBackstagePasses(item)) {
                updateQualityOfBackstagePasses(item);
            } else {
                increaseQuality(item, 1);
            }


            if (item.sellIn < 0) {
                if (!isAgedBrie(item)) {
                    if (!isBackstagePasses(item)) {
                        if (item.quality > 0) {
                            if (!isSulfuras(item)) {
                                decreaseQuality(item, 1);
                            }
                        }
                    } else {
                        resetQuality(item);
                    }
                } else {
                    if (item.quality < 50) {
                        increaseQuality(item, 1);
                    }
                }
            }
        }
    }

    private static void updateQualityOfBackstagePasses(Item item) {
        switch (valueOf(item.sellIn)) {
            case Integer s when s >= 10 -> increaseQuality(item, 1);
            case Integer s when s >= 5 -> increaseQuality(item, 2);
            case Integer s when s >= 0 -> increaseQuality(item, 3);
            default -> resetQuality(item);
        }
    }

    private static void resetQuality(Item item) {
        item.quality = 0;
    }

    private static void decreaseQuality(Item item, int amount) {
        item.quality = item.quality - amount;
    }

    private static void increaseQuality(Item item, int amount) {
        if (!isSulfuras(item)) {
            item.quality = Math.min((item.quality + amount), 50);
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

