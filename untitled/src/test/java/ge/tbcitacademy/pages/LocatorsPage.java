package ge.tbcitacademy.pages;

import ge.tbcitacademy.data.Constants;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

// Page კლასი — აქ ვინახავთ ყველა ლოკატორს და საჭირო ელემენტებს
public class LocatorsPage {
    WebDriver driver;

    // კონსტრუქტორი, რომელიც იღებს WebDriver-ს და აინიციალებს ელემენტებს
    public LocatorsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // აგენერირებს @FindBy ელემენტებს
    }

    // გვერდზე გადასასვლელი მეთოდი — slider-ის URL
    public void openSliderPage() {
        driver.get(Constants.JQUERY_UI_SLIDER_URL);
    }

    // XPath-ით მოიძებნება sidebar (aside) სექცია, სადაც Effects წერია
    public WebElement getAsideElement() {
        return driver.findElement(By.xpath("//aside[h3[contains(@class,'widget-title') and text()='Effects']]"));
    }

    // იღებს ყველა li ელემენტს ამ aside-ის შიგნით
    public List<WebElement> getListItems() {
        return getAsideElement().findElements(By.cssSelector("ul > li"));
    }

    // გადადის მეორე გვერდზე: add/remove elements
    public void openButtonsPage() {
        driver.get(Constants.ADD_REMOVE_ELEMENTS_URL);
    }

    // "Add Element" ღილაკი, მოძებნილია ტექსტით
    @FindBy(xpath = "//button[text()='Add Element']")
    public WebElement addElementButton;

    // XPath-ით მოძებნის ბოლო Delete ღილაკს
    public WebElement getLastDeleteButton() {
        return driver.findElement(By.xpath("(//button[text()='Delete'])[last()]"));
    }

    // იღებს ყველა Delete ღილაკს, რომელსაც აქვს onclick ატრიბუტი
    public List<WebElement> getDeleteButtons() {
        return driver.findElements(By.cssSelector("button[onclick^='deleteElement']"));
    }

    // გადადის მესამე გვერდზე: challenging DOM
    public void openChallengingDomPage() {
        driver.get(Constants.CHALLENGING_DOM_URL);
    }

    // XPath-ით მოიძებნება Apeirian9-ის გვერდით მდგომი Lorem მნიშვნელობა
    public WebElement getLoremValueForApeirian9() {
        return driver.findElement(By.xpath("//td[text()='Apeirian9']/preceding-sibling::td[1]"));
    }
}
