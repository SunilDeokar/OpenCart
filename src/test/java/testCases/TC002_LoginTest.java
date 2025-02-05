package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

    @Test(groups = "Sanity")
 public void SuccessLogin(){
     HomePage hp1=new HomePage(driver);
     hp1.clickMyAccount();
     hp1.clickLogin();

     LoginPage loginPage=new LoginPage(driver);
     loginPage.enterEmailid(prop.getProperty("EmailId"));
     loginPage.enterPassword(prop.getProperty("password"));
     loginPage.clickLogin();

        MyAccountPage myAccount=new MyAccountPage(driver);
     boolean myAccountText=myAccount.verifyMsg();

     Assert.assertEquals(myAccountText,true);
 }
}
