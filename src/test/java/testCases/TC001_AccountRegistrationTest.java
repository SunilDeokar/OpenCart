package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegisterPage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

@Test
    public void SuccessAccountRegistration() throws InterruptedException {
//logger.info("***********TC001_AccountRegistrationTest Started ***********");
        HomePage hp=new HomePage(driver);
        hp.clickMyAccount();

        hp.clickRegister();

    RegisterPage regPage=new RegisterPage(driver);
    regPage.setFirstName(randomString().toUpperCase());
    regPage.setLastName(randomString().toUpperCase());
    regPage.setEmail(randomString()+"@gmail.com");
    regPage.setTelephone(randomNumber());

    String pwd=randomAlphaNumber();
    regPage.setPassword(pwd);
    regPage.setConfirmPassword(pwd);
    regPage.checkAgreeCheckbox();
    regPage.clickContinueButton();
    String confirmMsg=regPage.checkMsgConfirm();

    if(confirmMsg.equals("Your Account Has Been Created!")){
        Assert.assertTrue(true);

    }
    else {
        Assert.assertTrue(false);
        //logger.info("***********TC001_AccountRegistrationTest ended ***********");
        //logger.debug("+++++++++Debug logs+++++++++");
    }


    }
}
