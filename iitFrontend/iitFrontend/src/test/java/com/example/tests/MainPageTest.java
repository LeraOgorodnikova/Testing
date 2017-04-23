package com.example.tests;

import com.codeborne.selenide.SelenideElement;
import  com.codeborne.selenide.testng.annotations.Report;
import com.example.BaseTest;
import com.example.components.*;
import com.example.pages.DataPage;
import com.example.pages.InfoParkPage;
import com.example.pages.MainPage;
import com.example.pages.MapPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Set;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.AssertJUnit.assertTrue;

@Test
@Report
public class MainPageTest extends BaseTest {

    private static final String CATEGORY_NAME = "Досуг и отдых";
    private static final String CATEGORY_ID = "162";
    private static final String ITEM_LIST_NAME = "Парковые территории";

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

        DataPage dataPage = page(DataPage.class);
        dataPage.shouldBeOpened();
        dataPage.getSelectedItem().getTextElement().shouldHave(text(CATEGORY_NAME));
    }

    @Test
    public void DMru20_infoAboutCategory(){
        openPage();

        SelenideElement item = $(By.xpath("//*[@id=\"datasets\"]/div[2]/ul/li[7]/ul/li[1]"));
        item.findElement(By.id("descLink")).click();

        boolean displayed = $(By.xpath("//*[@id=\"dropDesc\"]")).isDisplayed();

        Assert.assertTrue(displayed);
    }

    @Test
    public void DMru21_exportData() {
        openPage();

        SelenideElement item = $(By.xpath("//*[@id=\"datasets\"]/div[2]/ul/li[7]/ul/li[1]"));

        WebElement export = item.findElement(By.className("export"));
        export.click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement exportJson = export.findElement(By.xpath("//*[@id=\"dropExport\"]/li[1]/div"));
        Assert.assertTrue(exportJson.isDisplayed());

        WebElement passport = item.findElementById("dropPassportLink");
        passport.click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement passportJson = export.findElement(By.xpath("//*[@id=\"dropPass\"]/li[1]/a"));
        Assert.assertTrue(passportJson.isDisplayed());
    }

    @Test
    public void DMru22_filterCategory(){
        openPage();

        $(By.xpath("//*[@id=\"datasets\"]/div[2]/ul/li[7]/ul/li[1]")).click();

        String handlewindow = (String) getWebDriver().getWindowHandles().toArray()[1];
        switchTo().window(handlewindow);

        $(By.className("settings")).click();
        $("#dropColumns > div > ul > li:nth-child(5)").click();

        Assert.assertFalse($("#rows-content > thead > tr:nth-child(1) > th:nth-child(6)").isDisplayed());
    }

    @Test
    public void DMru23_mapCategory(){
        openPage();

        $(By.xpath("//*[@id=\"datasets\"]/div[2]/ul/li[7]/ul/li[1]")).click();

        String handlewindow = (String) getWebDriver().getWindowHandles().toArray()[1];
        switchTo().window(handlewindow);

        $(By.xpath("//*[@id=\"app\"]/div[3]/ul/li[2]")).click();

        SelenideElement map = $(By.id("map"));

        Assert.assertTrue(map.isDisplayed());
    }

    @Test
    public void DMru25_exportAllData(){
        openPage();

        $(By.xpath("//*[@id=\"datasets\"]/div[2]/ul/li[7]/ul/li[1]")).click();

        String handlewindow = (String) getWebDriver().getWindowHandles().toArray()[1];
        switchTo().window(handlewindow);

        $(By.id("dropDepartmentsLink")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        $(By.cssSelector("#dropDownloads > li:nth-child(2)")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SelenideElement downloadFormats = $(By.cssSelector("#dropDownloads"));

        Assert.assertFalse(downloadFormats.isDisplayed());
    }

}
