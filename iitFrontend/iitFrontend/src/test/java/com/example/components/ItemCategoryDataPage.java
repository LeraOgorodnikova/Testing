package com.example.components;


import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;

public class ItemCategoryDataPage extends ElementsContainer {

    public SelenideElement getUnit() {
        return getSelf();
        //return getSelf().find(".unit");
    }

    public SelenideElement getDescLink() {
        return getSelf().find("span#descLink");
    }

    public SelenideElement getExport() {
        return getSelf().find("#dropDepartmentsLink");
    }
}
