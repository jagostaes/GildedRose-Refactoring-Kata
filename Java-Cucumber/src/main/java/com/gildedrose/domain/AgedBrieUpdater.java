package com.gildedrose.domain;

class AgedBrieUpdater extends ItemUpdater {

    protected AgedBrieUpdater(Item item) {
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
