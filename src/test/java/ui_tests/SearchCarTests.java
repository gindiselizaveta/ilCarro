package ui_tests;

import manager.ApplicationManager;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.TestNGListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Listeners(TestNGListener.class)
public class SearchCarTests extends ApplicationManager {
    HomePage homePage;

    @BeforeMethod
    public void openHomePage() {
        homePage = new HomePage(getDriver());
    }

    @Test
    public void searchPosTest() {
        String city = "Haifa";
        LocalDate dateFrom = LocalDate.of(2025, 12, 1);
        LocalDate dateTo = LocalDate.of(2025, 12, 31);
        homePage.typeSearchForm(city, dateFrom, dateTo);
        Assert.assertTrue(homePage.urlContains("results", 5));
    }

    @Test(expectedExceptions = TimeoutException.class)
    public void searchNegTestWOCity() {
        String city = "";
        LocalDate dateFrom = LocalDate.of(2025, 12, 1);
        LocalDate dateTo = LocalDate.of(2025, 12, 31);
        homePage.typeSearchFormWOJS(city, dateFrom, dateTo);
    }

    @Test
    public void searchNegTestBeforeTodayDate() {
        String city = "Haifa";
        LocalDate dateFrom = LocalDate.of(2025, 11, 1);
        LocalDate dateTo = LocalDate.of(2025, 12, 31);
        homePage.typeSearchFormWOJS(city, dateFrom, dateTo);

        Assert.assertTrue(homePage.isTextInErrorPresent("You can't pick date before today"));
    }

    @Test
    public void searchNegTestWOCityValidateErrorMessage() {
        String city = "";
        LocalDate dateFrom = LocalDate.of(2025, 12, 1);
        LocalDate dateTo = LocalDate.of(2025, 12, 31);
        homePage.typeSearchFormWOJS(city, dateFrom, dateTo);

        Assert.assertTrue(homePage.isTextInErrorPresent("City is required"));
    }

    @Test
    public void searchNegTestAfterOneYear() {
        String city = "Haifa";
        LocalDate dateFrom = LocalDate.of(2025, 12, 1);
        LocalDate dateTo = LocalDate.of(2027, 12, 31);
        homePage.typeSearchFormWOJS(city, dateFrom, dateTo);

        Assert.assertTrue(homePage.isTextInErrorPresent("You can't pick date after one year"));
    }

    @Test
    public void searchNegTestToBeforeFrom() {
        String city = "Haifa";
        LocalDate dateFrom = LocalDate.of(2025, 12, 31);
        LocalDate dateTo = LocalDate.of(2025, 12, 1);
        homePage.typeSearchFormWOJS(city, dateFrom, dateTo);

        Assert.assertTrue(homePage.isTextInErrorPresent("Second date must be after first date"));
    }

    @Test
    public void searchPosTestCalendar() {
        String city = "Haifa";
        LocalDate dateFrom = LocalDate.of(2025, 12, 22);
        LocalDate dateTo = LocalDate.of(2026, 5, 11);
        homePage.typeSearchFormCalendar(city, dateFrom, dateTo);
        Assert.assertTrue(homePage.urlContains("results", 5));
    }
}
