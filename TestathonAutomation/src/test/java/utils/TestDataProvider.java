package utils;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
                {"demouser", "testingisfun99", true},
                {"locked_user", "testingisfun99", false},
                {"image_not_loading_user", "testingisfun99", true},
                {"fav_user", "testingisfun99", true},
                {"invalid_user", "wrong_pass", false}      // negative case
        };
    }
}
