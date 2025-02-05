package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }


    @FindBy( xpath=("//span[normalize-space()='My Account']"))
    WebElement locMyAccount;

    @FindBy(xpath=("//a[normalize-space()='Register']"))
    WebElement locRegister;


    @FindBy(xpath=("//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']"))
    WebElement btnLogin;

    public void clickMyAccount(){
        locMyAccount.click();
    }
    public void clickRegister(){
        locRegister.click();
    }
    public void clickLogin(){
        btnLogin.click();
    }

}
