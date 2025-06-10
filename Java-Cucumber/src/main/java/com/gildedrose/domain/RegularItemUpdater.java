package com.gildedrose.domain;

class RegularItemUpdater extends ItemUpdater {
    protected RegularItemUpdater(Item item) {
        super(item);
    }

    @Override
    protected void updateQuality() {
        if (item.sellIn > 0) {
            decreaseQuality(item, 1);
        } else {
            decreaseQuality(item, 2);
        }
    }

    @Override
    protected void expireItem() {
    }
}
