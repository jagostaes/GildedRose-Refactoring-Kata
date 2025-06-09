package com.gildedrose.domain;

public abstract class ItemUpdater {
    private final Item item;

    protected ItemUpdater(Item item) {
        this.item = item;
    }

    protected void updateItem(Item item) {
        updateQuality();
        decreaseSellIn();
        if (item.sellIn < 0) {
            expireItem();
        }
    }

    protected abstract void updateQuality();

    protected void decreaseSellIn() {
        --item.sellIn;
    }

    protected abstract void expireItem();
}
