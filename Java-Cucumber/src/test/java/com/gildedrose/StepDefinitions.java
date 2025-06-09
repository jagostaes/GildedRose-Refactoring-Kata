package com.gildedrose;

import com.gildedrose.domain.Item;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class StepDefinitions {
    private List<Item> items = new ArrayList<>();
    private GildedRose app;

    @Given("the item as {string} with sellIn {int} and quality {int}")
    public void initial_sellin_is_and_quality_is(String name, int sellIn, int quality) {
        items = List.of(new Item(name, sellIn, quality));
        app = new GildedRose(items);
    }

    @Given("a variety of items:")
    public void a_variety_of_items(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            items.add(new Item(
                    row.get("name"),
                    Integer.parseInt(row.get("sellIn")),
                    Integer.parseInt(row.get("quality"))
            ));
        }
        app = new GildedRose(items);
    }

    @When("{int} days pass")
    public void x_days_pass(int days) {
        IntStream.range(0, days).forEach(x -> app.updateQuality());
    }

    @Then("I should get item as {string}")
    public void i_should_get_sellin_as_and_quality_as(String expected) {
        assertThat(expected).isEqualTo(app.getItems().getFirst().name);
    }

    @Then("the quality is updated to {int}")
    public void the_quality_is_updated_to(int expected) {
        assertThat(expected).isEqualTo(app.getItems().getFirst().quality);
    }

    @Then("the sellIn is updated to {int}")
    public void the_sellIn_is_updated_to(int expected) {
        assertThat(expected).isEqualTo(app.getItems().getFirst().sellIn);
    }

    @Then("the items should have the following values:")
    public void the_items_should_have_the_following_values(DataTable expectedTable) {
        List<Map<String, String>> expected = expectedTable.asMaps(String.class, String.class);
        for (int i = 0; i < expected.size(); i++) {
            Map<String, String> expectedItem = expected.get(i);
            Item actualItem = items.get(i);

            assertThat(expectedItem.get("name")).isEqualTo(actualItem.name);
            assertThat(Integer.parseInt(expectedItem.get("sellIn"))).isEqualTo(actualItem.sellIn);
            assertThat(Integer.parseInt(expectedItem.get("quality"))).isEqualTo(actualItem.quality);
        }
    }
}

