package com.gildedrose.domain;

import static java.lang.Integer.valueOf;

class BackstagePassesUpdater extends ItemUpdater{
    protected BackstagePassesUpdater(Item item) {
        super(item);
    }

    @Override
    protected void updateQuality() {
        switch (valueOf(item.sellIn)) {
            case Integer s when s >= 10 -> increaseQuality(item, 1);
            case Integer s when s >= 5 -> increaseQuality(item, 2);
            case Integer s when s >= 0 -> increaseQuality(item, 3);
            default -> expireItem();
        }
    }

    @Override
    protected void expireItem() {
        item.quality = 0;
    }
}
