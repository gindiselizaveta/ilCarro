package utils;

public enum HeaderMenuItem {
    SEARCH("//a[text()=' Search ']"),
    LETTHECARWORK("//a[text()=' Let the car work ']"),
    TERMSOFUSE("//a[text()=' Terms of use ']"),
    SIGNUP("//a[text()=' Sign up ']"),
    LOGIN("//a[text()=' Log in ']");


    private final String locator;

    HeaderMenuItem(String locator) {
        this.locator = locator;
    }

    public String getLocator() {
        return locator;
    }
}
