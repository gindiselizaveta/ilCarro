package ui_tests;

import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.FooterMenuItem;

public class FooterNavigationTests extends ApplicationManager {

    @Test
    public void iconFacebookNavigationTest() {
        Assert.assertTrue(new HomePage(getDriver()).clickFooterItem(FooterMenuItem.ICON_FACEBOOK, "Facebook"));
    }

    @Test
    public void iconTelegramNavigationTest() {
        Assert.assertTrue(new HomePage(getDriver()).clickFooterItem(FooterMenuItem.ICON_TELEGRAM, "Telegram"));
    }

    @Test
    public void iconVKNavigationTest() {
        Assert.assertTrue(new HomePage(getDriver()).clickFooterItem(FooterMenuItem.ICON_VK, "VK"));
    }

    @Test
    public void iconInstagramNavigationTest() {
        Assert.assertTrue(new HomePage(getDriver()).clickFooterItem(FooterMenuItem.ICON_INSTAGRAM, "Instagram"));
    }

    @Test
    public void iconSlackNavigationTest() {
        Assert.assertTrue(new HomePage(getDriver()).clickFooterItem(FooterMenuItem.ICON_SLACK, "Slack"));
    }
}
