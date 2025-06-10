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
            ItemUpdater updater = TypeSelector.getUpdaterForItem(item);
            updater.updateItem();
        }
    }

    public List<Item> getItems() {
        return items;
    }
}

