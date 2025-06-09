package com.gildedrose;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class StepDefinitions {
    private Item[] items = new Item[1];
    private GildedRose app;

    @Given("The item as {string} with quality {int}")
    public void initial_sellin_is_and_quality_is(String name, int quality) {
        items[0] = new Item(name, 0, quality);
        app = new GildedRose(items);
    }

    @When("I update the quality")
    public void i_update_the_quality() {
        app.updateQuality();
    }

    @Then("I should get item as {string}")
    public void i_should_get_sellin_as_and_quality_as(String expected) {
        assertThat(expected).isEqualTo(app.items[0].name);
    }

    @Then("The quality is updated to {int}")
    public void the_quality_should_be_updated(int expected) {
        assertThat(expected).isEqualTo(app.items[0].quality);
    }
}

