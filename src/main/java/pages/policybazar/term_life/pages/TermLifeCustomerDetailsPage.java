package pages.policybazar.term_life.pages;

import driver.util.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TermLifeCustomerDetailsPage {

    private WebDriver wd;
    private Helper helper;

    public TermLifeCustomerDetailsPage(WebDriver wd){
        this.wd = wd;
        helper = new Helper(wd);
        PageFactory.initElements(wd, this);
    }

    @FindBy(xpath = "//input[@placeholder='Enter Your Full Name']")
    private WebElement customerName;

    @FindBy(name = "dob")
    private WebElement customerDOB;

    @FindBy(id= "mobileNo")
    private WebElement customerMobileNo;

    @FindBy(id = "submitButton")
    private WebElement viewFreeQuotes;


    public boolean verifyTermLifeCustomerDetailsPage(){
        return helper.visibilityOfElement(10,customerName) &&
                wd.getTitle().contains("Term Life Insurance");
    }

    public void enterCustomerName(String name){
        customerName.sendKeys(name);
        helper.Wait(1);
    }

    public void enterCustomerDOB(String dateofbirth){
        customerDOB.sendKeys(dateofbirth);
        helper.Wait(1);
    }

    public void enterMobileNo(String mobileNo){
        customerMobileNo.sendKeys(mobileNo);
        helper.Wait(1);
    }

    public void clickViewFreeQuotes(){
        viewFreeQuotes.click();
        helper.Wait(1);
    }

}
