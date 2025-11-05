package ui_tests;

import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
        Assert.assertTrue(homePage.isCarAvailable());
    }

    @Test
    public void searchNegTestEmptyCity() {
        String city = "";
        LocalDate dateFrom = LocalDate.of(2025, 12, 1);
        LocalDate dateTo = LocalDate.of(2025, 12, 31);
        homePage.typeSearchForm(city, dateFrom, dateTo);
        Assert.assertTrue(homePage.isCarAvailable());
    }

    @Test
    public void searchNegTestEmptyDates() {
        String city = "Haifa";
        LocalDate dateFrom = LocalDate.of(2025, 11, 1);
        LocalDate dateTo = LocalDate.of(2025, 12, 31);
        homePage.typeSearchForm(city, dateFrom, dateTo);
        Assert.assertTrue(homePage.wrongDate());
    }
}
