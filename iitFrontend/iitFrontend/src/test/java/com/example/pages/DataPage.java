package com.example.pages;

import com.codeborne.selenide.SelenideElement;
import com.example.components.CategoryItemDataPage;
import com.example.components.ItemCategory;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DataPage extends AbstractPage {
    public DataPage() {
        super();
        this.url = "https://data.mos.ru/opendata";
    }

    public AbstractPage navigate() {
        return super.navigate(this.getClass());
    }

    @Override
    public AbstractPage waitPageLoaded() {
        $(".loader-block").waitWhile(visible, 30000);
        return this;
    }

    public CategoryItemDataPage getSelectedItem() {
        CategoryItemDataPage category = new CategoryItemDataPage();
        category.setSelf($(".items-list .selected"));
        return category;
    }

    public ItemCategory getItemCategory() {
        ItemCategory categories = new ItemCategory();
        categories.setSelf($("#datasets > div.sticky-list.scroll > ul > li:nth-child(7) > ul"));
        return categories;
    }

}
