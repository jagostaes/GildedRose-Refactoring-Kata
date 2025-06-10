package com.gildedrose.domain;

public class ConjuredUpdater extends RegularItemUpdater {
    protected ConjuredUpdater(Item item) {
        super(item);
    }

    @Override
    protected void updateQuality() {
        super.updateQuality();
        super.updateQuality();
    }
}
