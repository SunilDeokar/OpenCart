package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_DataDrivenLoginTest  extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class)
    public void dataDrivenLoginTest(String email, String pwd, String exp) {

            HomePage hp1 = new HomePage(driver);
            hp1.clickMyAccount();
            hp1.clickLogin();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.enterEmailid(email);
            loginPage.enterPassword(pwd);
            loginPage.clickLogin();

            MyAccountPage myAccount = new MyAccountPage(driver);
            boolean myAccountText = myAccount.verifyMsg();
        /*
        Data is valid -login success -test pass -logout
        Data is valid -login fail - test fail
        Data is invalid -login success - test fail -logout
        Data is invalid - login fail - test pass
         */
            if (exp.equalsIgnoreCase("valid")) {

                if (myAccountText == true) {
                    myAccount.ClickLogut();
                    Assert.assertTrue(true);
                } else {
                    Assert.assertTrue(false);
                }
            }
            if (exp.equalsIgnoreCase("Invalid")) {
                if (myAccountText == true) {
                    myAccount.ClickLogut();
                    Assert.assertTrue(false);
                } else {
                    Assert.assertTrue(true);
                }
            }

    }
}
