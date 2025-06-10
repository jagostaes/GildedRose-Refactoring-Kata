package com.gildedrose.domain;

public class AgedBrieUpdater extends ItemUpdater {

    public AgedBrieUpdater(Item item) {
        super(item);
    }

    @Override
    protected void updateQuality() {
        if (item.sellIn >= 0) {
            increaseQuality(item, 1);
        } else {
            increaseQuality(item, 2);
        }
    }

    @Override
    protected void expireItem() {

    }
}
