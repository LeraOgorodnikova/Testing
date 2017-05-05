package com.example.tests;

import com.codeborne.selenide.SelenideElement;
import  com.codeborne.selenide.testng.annotations.Report;
import com.example.BaseTest;
import com.example.components.*;
import com.example.pages.DataPage;
import com.example.pages.MainPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

@Test
@Report
public class MainPageTest extends BaseTest {

    private static final String CATEGORY_NAME = "Досуг и отдых";
    private static final String CATEGORY_ID = "162";

    @Test
    public void categoriesNumberCheck() {
        MainPage page = page(MainPage.class);
        page.navigate();
        Categories categories = page.getCategories();
        categories.getCategoriesCollection().shouldHaveSize(27);
    }

    @Test
    public void activeCitizenExistenceCheck() {
        MainPage page = page(MainPage.class);
        page.navigate();
        Categories categories = page.getCategories();
        CategoryItemMainPage activeCitizenCategory = categories.getCategoryByName(CATEGORY_NAME);
        activeCitizenCategory.getTextElement().shouldHave(text(CATEGORY_NAME))
                .shouldHave(attribute("data-id", CATEGORY_ID));
    }

    public void openPage(){

        MainPage page = page(MainPage.class);
        page.navigate();
        Categories categories = page.getCategories();
        categories.getCategoryByName(CATEGORY_NAME).getUnit().click();
    }

    @Test
    public void DMru20_infoAboutCategory(){
        openPage();

        DataPage dataPage = page(DataPage.class);
        dataPage.shouldBeOpened();
        dataPage.getSelectedItem().getTextElement().shouldHave(text(CATEGORY_NAME));

        ItemCategory itemCategories = dataPage.getItemCategory();

        SelenideElement item = itemCategories.getCategoryByName("Аттракционы в скверах и парках").getDescLink();
        Assert.assertTrue(item.isDisplayed());
        item.click();

        boolean displayed = $(By.xpath("//*[@id=\"dropDesc\"]")).isDisplayed();
        Assert.assertTrue(displayed);
    }

    @Test
    public void DMru21_exportData() {
        openPage();

        DataPage dataPage = page(DataPage.class);
        dataPage.shouldBeOpened();
        dataPage.getSelectedItem().getTextElement().shouldHave(text(CATEGORY_NAME));

        ItemCategory itemCategories = dataPage.getItemCategory();

        SelenideElement itemExport = itemCategories.getCategoryByName("Ботанические сады").getExport();
        Assert.assertTrue(itemExport.isDisplayed());
        itemExport.click();

        SelenideElement itemJson = $(By.xpath("//*[@id=\"dropExport\"]/li[1]/div/a"));
        Assert.assertEquals(itemJson.getText(),"json\n5 Kb");
        Assert.assertTrue(itemJson.isDisplayed());

    }

    @Test
    public void DMru22_filterCategory(){
        openPage();

        DataPage dataPage = page(DataPage.class);
        dataPage.shouldBeOpened();
        dataPage.getSelectedItem().getTextElement().shouldHave(text(CATEGORY_NAME));

        ItemCategory itemCategories = dataPage.getItemCategory();
        itemCategories.getCategoryByName("Аквапарки").getUnit().click();
        registerNewTab();

        SelenideElement itemConfig = $(By.cssSelector("#rows-caption > thead:nth-child(1) > tr:nth-child(1) > th:nth-child(1) > a:nth-child(3)"));
        Assert.assertTrue(itemConfig.isDisplayed());
        itemConfig.click();

        SelenideElement itemFilter = $(By.cssSelector("#dropColumns > div > ul > li:nth-child(4)"));
        Assert.assertTrue(itemFilter.isDisplayed());
        itemFilter.click();

        SelenideElement itemFilterRow = $(By.cssSelector("#rows-content > thead:nth-child(1) > tr:nth-child(1) > th:nth-child(5)"));
        Assert.assertTrue(itemFilterRow.isDisplayed());
        Assert.assertEquals(itemFilterRow.getText(),"Район");

    }

    @Test
    public void DMru23_mapCategory(){
        openPage();

        DataPage dataPage = page(DataPage.class);
        dataPage.shouldBeOpened();
        dataPage.getSelectedItem().getTextElement().shouldHave(text(CATEGORY_NAME));

        ItemCategory itemCategories = dataPage.getItemCategory();
        itemCategories.getCategoryByName("Весенние фестивали").getUnit().click();
        registerNewTab();


        SelenideElement itemMap = $(By.xpath("//*[@id=\"app\"]/div[3]/ul/li[2]/a"));
        Assert.assertTrue(itemMap.isDisplayed());
        itemMap.click();
        registerNewTab();

        Assert.assertTrue($(By.cssSelector("#map")).isDisplayed());

    }

    @Test
    public void DMru25_exportAllData(){
        openPage();

        DataPage dataPage = page(DataPage.class);
        dataPage.shouldBeOpened();
        dataPage.getSelectedItem().getTextElement().shouldHave(text(CATEGORY_NAME));

        ItemCategory itemCategories = dataPage.getItemCategory();
        itemCategories.getCategoryByName("Детские игровые площадки в парках").getUnit().click();

        registerNewTab();

        SelenideElement itemExport = $(By.cssSelector("#dropDepartmentsLink"));
        Assert.assertTrue(itemExport.isDisplayed());
        itemExport.shouldHave(text("Скачать"));
        itemExport.click();

        SelenideElement itemJson = $(By.cssSelector("#dropDownloads > li:nth-child(1) > div:nth-child(1) > a:nth-child(1)"));
        Assert.assertEquals(itemJson.getText(),".json\n1 Mb");
        Assert.assertTrue(itemJson.isDisplayed());

    }
}
