package com.gildedrose.domain;

public class TypeSelector {

    public static ItemUpdater getUpdaterForItem(Item item) {
        return switch (item) {
            case Item i when isSulfuras(i) -> new SulfurasUpdater(i);
            case Item i when isBackstagePasses(i) -> new BackstagePassesUpdater(i);
            case Item i when isAgedBrie(i) -> new AgedBrieUpdater(i);
            case Item i when isConjured(i) -> new ConjuredUpdater(i);
            default -> new RegularItemUpdater(item);
        };
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

    private static boolean isConjured(Item item) {
        return item.name.startsWith("Conjured");
    }
}
