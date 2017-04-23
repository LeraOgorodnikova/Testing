package com.example.pages;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MapPage extends AbstractPage{

    public MapPage() {
        super();
        this.url = "https://data.mos.ru/opendata/7710878000-parkovye-territorii/data/map?versionNumber=6&releaseNumber=15";
    }

    public AbstractPage navigate() {
        return super.navigate(this.getClass());
    }

    @Override
    public AbstractPage waitPageLoaded() {
        $(".loader-block").waitWhile(visible, 30000);
        return this;
    }
}
