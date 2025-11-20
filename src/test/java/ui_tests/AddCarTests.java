package ui_tests;

import data_providers.CarDP;
import dto.NewCar;
import dto.User;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.LetTheCarWorkPage;
import pages.LoginPage;
import utils.HeaderMenuItem;
import utils.TestNGListener;

import java.util.Random;

import static pages.BasePage.clickButtonHeader;

@Listeners(TestNGListener.class)
public class AddCarTests extends ApplicationManager {

    LetTheCarWorkPage letTheCarWorkPage;
    LoginPage loginPage;

    @BeforeMethod
    public void login() throws IllegalAccessException {
        User user = User.builder()
                .username("lizkafox@mail.ru")
                .password("wertY!23").build();
        new HomePage(getDriver()).clickButtonHeader(HeaderMenuItem.LOGIN);
        loginPage = new LoginPage(getDriver());
        loginPage.typeLoginForm(user);
        //loginPage.btnOkClick();
        letTheCarWorkPage = clickButtonHeader(HeaderMenuItem.LETTHECARWORK);
    }

    @Test
    public void addNewCarPositiveTest() {
        NewCar newCar = NewCar.builder()
                .serialNumber("8" + new Random().nextInt(10000))
                .manufacture("Toyota")
                .model("Yaris Cross")
                .year("2022")
                .fuel("Hybrid")
                .seats(4)
                .carClass("J")
                .pricePerDay(350.0)
                .about("Some String about the car")
                .city("Haifa")
                .image("1.png").build();
        letTheCarWorkPage.typeLetTheCarWorkForm(newCar);
        Assert.assertTrue(letTheCarWorkPage.btnOkPopUpPresent());
    }

    @Test(dataProvider = "dataProviderCarFile", dataProviderClass = CarDP.class)
    public void addNewCarPositiveDP(NewCar car) {
        letTheCarWorkPage.typeLetTheCarWorkForm(car);
        Assert.assertTrue(letTheCarWorkPage.isBtnSubmitDisabled());
        //Assert.assertTrue(letTheCarWorkPage.btnOkPopUpPresent());
    }

    @Test
    public void addNewCarNegativeTest_WOManufacture() {
        NewCar newCar = NewCar.builder()
                .serialNumber("8" + new Random().nextInt(10000))
                .manufacture("")
                .model("Yaris Cross")
                .year("2022")
                .fuel("Hybrid")
                .seats(4)
                .carClass("J")
                .pricePerDay(350.0)
                .about("Some String about the car")
                .city("Haifa")
                .image("1.png").build();
        letTheCarWorkPage.typeLetTheCarWorkForm(newCar);
        Assert.assertTrue(letTheCarWorkPage.isTextInErrorPresent("Make is required"));
    }

    @Test
    public void addNewCarNegativeTest_WOModel() {
        NewCar newCar = NewCar.builder()
                .serialNumber("8" + new Random().nextInt(10000))
                .manufacture("Toyota")
                .model("")
                .year("2022")
                .fuel("Hybrid")
                .seats(4)
                .carClass("J")
                .pricePerDay(350.0)
                .about("Some String about the car")
                .city("Haifa")
                .image("1.png").build();
        letTheCarWorkPage.typeLetTheCarWorkForm(newCar);
        Assert.assertTrue(letTheCarWorkPage.isTextInErrorPresent("Model is required"));
    }

    @Test
    public void addNewCarNegativeTest_WOYear() {
        NewCar newCar = NewCar.builder()
                .serialNumber("8" + new Random().nextInt(10000))
                .manufacture("Toyota")
                .model("Yaris Cross")
                .year("")
                .fuel("Hybrid")
                .seats(4)
                .carClass("J")
                .pricePerDay(350.0)
                .about("Some String about the car")
                .city("Haifa")
                .image("1.png").build();
        letTheCarWorkPage.typeLetTheCarWorkForm(newCar);
        Assert.assertTrue(letTheCarWorkPage.isTextInErrorPresent("Year required"));
    }

    @Test
    public void addNewCarNegativeTest_WOFuel() {
        NewCar newCar = NewCar.builder()
                .serialNumber("8" + new Random().nextInt(10000))
                .manufacture("Toyota")
                .model("Yaris Cross")
                .year("2022")
                .fuel("")
                .seats(4)
                .carClass("J")
                .pricePerDay(350.0)
                .about("Some String about the car")
                .city("Haifa")
                .image("1.png").build();
        letTheCarWorkPage.typeLetTheCarWorkFormWOSelector(newCar);
        Assert.assertTrue(letTheCarWorkPage.isTextInErrorPresent("Fuel is required"));
    }

    @Test
    public void addNewCarNegativeTest_LesThanTwoSeats() {
        NewCar newCar = NewCar.builder()
                .serialNumber("8" + new Random().nextInt(10000))
                .manufacture("Toyota")
                .model("Yaris Cross")
                .year("2022")
                .fuel("Hybrid")
                .seats(1)
                .carClass("J")
                .pricePerDay(350.0)
                .about("Some String about the car")
                .city("Haifa")
                .image("1.png").build();
        letTheCarWorkPage.typeLetTheCarWorkForm(newCar);
        Assert.assertTrue(letTheCarWorkPage.isTextInErrorPresent("Car must have min 2 seat"));
    }

    @Test
    public void addNewCarNegativeTest_WOClass() {
        NewCar newCar = NewCar.builder()
                .serialNumber("8" + new Random().nextInt(10000))
                .manufacture("Toyota")
                .model("Yaris Cross")
                .year("2022")
                .fuel("Hybrid")
                .seats(4)
                .carClass("")
                .pricePerDay(350.0)
                .about("Some String about the car")
                .city("Haifa")
                .image("1.png").build();
        letTheCarWorkPage.typeLetTheCarWorkForm(newCar);
        Assert.assertTrue(letTheCarWorkPage.isTextInErrorPresent("Car class is required"));
    }

    @Test
    public void addNewCarNegativeTest_WOSerialNumber() {
        NewCar newCar = NewCar.builder()
                .serialNumber("")
                .manufacture("Toyota")
                .model("Yaris Cross")
                .year("2022")
                .fuel("Hybrid")
                .seats(4)
                .carClass("J")
                .pricePerDay(350.0)
                .about("Some String about the car")
                .city("Haifa")
                .image("1.png").build();
        letTheCarWorkPage.typeLetTheCarWorkForm(newCar);
        Assert.assertTrue(letTheCarWorkPage.isTextInErrorPresent("Car registration number is required"));
    }

    @Test
    public void addNewCarNegativeTest_WOPrice() {
        NewCar newCar = NewCar.builder()
                .serialNumber("8" + new Random().nextInt(10000))
                .manufacture("Toyota")
                .model("Yaris Cross")
                .year("2022")
                .fuel("Hybrid")
                .seats(4)
                .carClass("J")
                .pricePerDay(null)
                .about("Some String about the car")
                .city("Haifa")
                .image("1.png").build();
        letTheCarWorkPage.typeLetTheCarWorkForm(newCar);
        Assert.assertTrue(letTheCarWorkPage.isTextInErrorPresent("Price is required"));
    }

    @Test
    public void addNewCarNegativeTest_TooBigPrice() {
        NewCar newCar = NewCar.builder()
                .serialNumber("8" + new Random().nextInt(10000))
                .manufacture("Toyota")
                .model("Yaris Cross")
                .year("2022")
                .fuel("Hybrid")
                .seats(4)
                .carClass("J")
                .pricePerDay(1000.01)
                .about("Some String about the car")
                .city("Haifa")
                .image("1.png").build();
        letTheCarWorkPage.typeLetTheCarWorkForm(newCar);
        Assert.assertTrue(letTheCarWorkPage.isTextInErrorPresent("To much big price"));
    }
}
