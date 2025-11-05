package pages;

import dto.NewCar;
import dto.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

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
    WebElement fuel;
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
    @FindBy(xpath = "//button[@type = 'submit']")
    WebElement btnSubmit;

    public void typeLetTheCarWorkForm(NewCar car) {
        serialNumber.sendKeys(car.getSerialNumber());
        manufacture.sendKeys(car.getManufacture());
        model.sendKeys(car.getModel());
        year.sendKeys(car.getYear());
        fuel.sendKeys(car.getFuel());
        seats.sendKeys(String.valueOf(car.getSeats()));
        carClass.sendKeys(car.getCarClass());
        pricePerDay.sendKeys(String.valueOf(car.getPricePerDay()));
        about.sendKeys(car.getAbout());
        city.sendKeys(car.getCity());
    }

    public boolean isBtnSubmitDisabled() {
        String disabledAttr = btnSubmit.getAttribute("disabled");
        return disabledAttr != null;
    }
}
