package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage{
    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id="input-firstname")
    WebElement txtFirstName;

    @FindBy(id="input-lastname")
    WebElement txtLastName;

    @FindBy(id="input-email")
    WebElement txtEmail;

    @FindBy(id="input-telephone")
    WebElement txtTelephone;

    @FindBy(id="input-password")
    WebElement txtPassword;

    @FindBy(id="input-confirm")
    WebElement txtConfirmPassword;

    @FindBy(xpath="//input[@name='agree']")
    WebElement chkAgreeCheckbox;

    @FindBy(xpath="//input[@value='Continue']")
    WebElement btnContinue;

    @FindBy(xpath ="//h1[text()='Your Account Has Been Created!']")
    WebElement msgConfirm;

    public void setFirstName(String fName){
        txtFirstName.sendKeys(fName);
    }
    public void setLastName(String lName) {
        txtLastName.sendKeys(lName);
    }
    public void setEmail(String email){
        txtEmail.sendKeys(email);
    }
    public void setTelephone(String telephone){
        txtTelephone.sendKeys(telephone);
    }
    public void setPassword(String password){
        txtPassword.sendKeys(password);
    }
    public void setConfirmPassword(String confirmPassword){
        txtConfirmPassword.sendKeys(confirmPassword);
    }
    public void checkAgreeCheckbox(){
        chkAgreeCheckbox.click();
    }
    public void clickContinueButton(){
        btnContinue.click();
    }
    public String checkMsgConfirm(){
       try {
           String msg=msgConfirm.getText();
           return msg;
       } catch (Exception e) {
           return String.valueOf(e);
       }

    }
}

