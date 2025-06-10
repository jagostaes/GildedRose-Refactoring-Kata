package com.gildedrose.domain;

public abstract class ItemUpdater {
    protected final Item item;

    public ItemUpdater(Item item) {
        this.item = item;
    }

    public void updateItem(Item item) {
        decreaseSellIn();
        updateQuality();
        if (item.sellIn < 0) {
            expireItem();
        }
    }

    protected abstract void updateQuality();

    protected void decreaseSellIn() {
        --item.sellIn;
    }

    protected abstract void expireItem();

    public static void decreaseQuality(Item item, int amount) {
        item.quality = Math.max(item.quality - amount, 0);
    }

    public static void increaseQuality(Item item, int amount) {
        if (item.quality != 80) {
            item.quality = Math.min((item.quality + amount), 50);
        }
    }
}
