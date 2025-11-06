package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertiesReader;

import java.time.Duration;
import java.time.LocalDate;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        setDriver(driver);
        //driver.get("https://ilcarro.web.app/search");
        driver.get(PropertiesReader.getProperty("base.properties", "baseUrl"));
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(css = "a[href='/login?url=%2Fsearch']")
    WebElement btnLoginHeader;

    @FindBy(xpath = "//a[text()='Sign up']")
    WebElement btnSignUpHeader;

    @FindBy(id = "city")
    WebElement inputCity;
    @FindBy(id = "dates")
    WebElement inputDates;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;

    @FindBy(xpath = "//h3[contains(text(),'available')]")
    WebElement textAvailable;
    @FindBy(xpath = "//div[text()=' You can't pick date before today ']")
    WebElement textDateBeforeToday;


    public void clickBtnLoginHeader() {
        btnLoginHeader.click();
    }

    public void clickBtnSignUpHeader() {
        btnSignUpHeader.click();
    }

    public void typeSearchForm(String city, LocalDate dateFrom, LocalDate dateTo) {
        inputCity.sendKeys(city);
        System.out.println(dateFrom.toString());
        String dates = dateFrom.getMonthValue() + "/" + dateFrom.getDayOfMonth() + "/" + dateFrom.getYear()
                + " - " + dateTo.getMonthValue() + "/" + dateTo.getDayOfMonth() + "/" + dateTo.getYear();
        inputDates.sendKeys(dates);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\").removeAttribute(\"disabled\")");
        clickWait(btnYalla, 3);
    }

    public void typeSearchFormWOJS(String city, LocalDate dateFrom, LocalDate dateTo) {
        inputCity.sendKeys(city);
        System.out.println(dateFrom.toString());
        String dates = dateFrom.getMonthValue() + "/" + dateFrom.getDayOfMonth() + "/" + dateFrom.getYear()
                + " - " + dateTo.getMonthValue() + "/" + dateTo.getDayOfMonth() + "/" + dateTo.getYear();
        inputDates.sendKeys(dates);
        btnYalla.click();
    }

    public boolean isCarAvailable() {
        return elementIsDisplayed(textAvailable);
    }

    public boolean wrongDate() {
        return elementIsDisplayed(textDateBeforeToday);
    }

    void clickWait(WebElement element, int time) {
        new WebDriverWait(driver, Duration.ofSeconds(time)).until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public boolean urlContains(String fraction, int time) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(time)).until(ExpectedConditions.urlContains(fraction));
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        }
    }
}
