package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.HeaderMenuItem;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    static WebDriver driver;

    public static void setDriver(WebDriver wd) {
        driver = wd;
    }

    @FindBy(xpath = "//div[contains(@class,'error')]")
    List<WebElement> errorElements;
    @FindBy(xpath = "//mat-dialog-container//button")
    WebElement btnOkPopUp;

    public static <T extends BasePage> T clickButtonHeader(HeaderMenuItem item) throws IllegalAccessException {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.elementToBeClickable(By.xpath(item.getLocator())));
        element.click();
        switch (item) {
            case SEARCH -> {
                return (T) new HomePage(driver);
            }
            case LETTHECARWORK -> {
                return (T) new LetTheCarWorkPage(driver);
            }
            case TERMSOFUSE -> {
                return (T) new TermsOfUsePage(driver);
            }
            case SIGNUP -> {
                return (T) new SignUpPage(driver);
            }
            case LOGIN -> {
                return (T) new LoginPage(driver);
            }
            case LOGOUT -> {
                return (T) new HomePage(driver);
            }
            case DELLETE_ACCOUNT -> {
                return (T) new HomePage(driver);
            }
            default -> throw new IllegalAccessException("Invalid parameter headerMenuItem");
        }
    }

    //    @FindBy(css = "div[class= 'error']")
//    List<WebElement> errorElements;

    public boolean elementIsDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public static void pause(int time) {
        try {
            Thread.sleep(time * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    void clickWait(WebElement element, int time) {
        new WebDriverWait(driver, Duration.ofSeconds(time))
                .until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public boolean isTextInErrorPresent(String text) {
        if (errorElements == null || errorElements.isEmpty())
            return false;
        for (WebElement element : errorElements) {
            if (element.getText().contains(text))
                return true;
        }
        return false;
    }

}
