package pages;

import dto.NewCar;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class LetTheCarWorkPage extends BasePage {
    public LetTheCarWorkPage(WebDriver driver) {
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(id = "serialNumber")
    WebElement serialNumber;
    @FindBy(id = "make")
    WebElement manufacture;
    @FindBy(id = "model")
    WebElement model;
    @FindBy(id = "year")
    WebElement year;
    @FindBy(id = "fuel")
    WebElement selectfuel;
    @FindBy(id = "seats")
    WebElement seats;
    @FindBy(id = "class")
    WebElement carClass;
    @FindBy(id = "price")
    WebElement pricePerDay;
    @FindBy(id = "about")
    WebElement about;
    @FindBy(id = "pickUpPlace")
    WebElement city;
    @FindBy(id = "photos")
    WebElement image;
    @FindBy(xpath = "//button[@type = 'submit']")
    WebElement btnSubmit;

    public void typeLetTheCarWorkForm(NewCar car) {
        serialNumber.sendKeys(car.getSerialNumber());
        manufacture.sendKeys(car.getManufacture());
        model.sendKeys(car.getModel());
        year.sendKeys(car.getYear());
        //selectfuel.sendKeys(car.getFuel());
        typeFuel(car.getFuel());
        seats.sendKeys(String.valueOf(car.getSeats()));
        carClass.sendKeys(car.getCarClass());
        pricePerDay.sendKeys(car.getPricePerDay() + "");
        about.sendKeys(car.getAbout());
        city.sendKeys(car.getCity());
        addPhoto(car.getImage());
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\").removeAttribute(\"disabled\")");
        clickWait(btnSubmit, 3);
    }

    public void typeLetTheCarWorkFormWOSelector(NewCar car) {
        serialNumber.sendKeys(car.getSerialNumber());
        manufacture.sendKeys(car.getManufacture());
        model.sendKeys(car.getModel());
        year.sendKeys(car.getYear());
        selectfuel.sendKeys(car.getFuel());
        //typeFuel(car.getFuel());
        seats.sendKeys(String.valueOf(car.getSeats()));
        carClass.sendKeys(car.getCarClass());
        pricePerDay.sendKeys(car.getPricePerDay() + "");
        about.sendKeys(car.getAbout());
        city.sendKeys(car.getCity());
        addPhoto(car.getImage());
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector(\"button[type='submit']\").removeAttribute(\"disabled\")");
        clickWait(btnSubmit, 3);
    }

    private void typeFuel(String fuel) {
        Select select = new Select(selectfuel);
        select.selectByValue(fuel);
    }

    private void addPhoto(String fileName) {
        image.sendKeys(new File("src/test/resources"
                + File.separator + fileName)
                .getAbsolutePath());
    }

    public boolean isBtnSubmitDisabled() {
        String disabledAttr = btnSubmit.getAttribute("disabled");
        return disabledAttr != null;
    }

    public boolean btnOkPopUpPresent(){
        return elementIsDisplayed(btnOkPopUp);
    }
}
