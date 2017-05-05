package com.example.components;



import com.codeborne.selenide.ElementsContainer;
import org.openqa.selenium.By;

public class ItemCategory extends ElementsContainer {

    public ItemCategoryDataPage getCategoryByXPath(String xpath) {
        ItemCategoryDataPage categoryItem = new ItemCategoryDataPage();
        categoryItem.setSelf(getSelf().find(By.xpath("//*[@id=\"datasets\"]/div[2]/ul/li[7]/ul/li[14]/a")));
        return categoryItem;
    }

    public ItemCategoryDataPage getCategoryByName(String name) {
        ItemCategoryDataPage categoryItem = new ItemCategoryDataPage();
        categoryItem.setSelf(getSelf().find(By.xpath(String.format("//*[text()='%s']/../..", name))));
        return categoryItem;
    }
}
