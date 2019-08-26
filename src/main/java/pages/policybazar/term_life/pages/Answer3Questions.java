package pages.policybazar.term_life.pages;

import driver.util.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Answer3Questions {

    private WebDriver wd;
    private Helper helper;

    public Answer3Questions(WebDriver wd){
        this.wd = wd;
        helper = new Helper(wd);
        PageFactory.initElements(wd, this);
    }

    @FindBy(xpath = "//*[text()='Just answer 3 simple questions to get more accurate quotes']")
    private WebElement answer3Questions;

    @FindBy(xpath = "//*[text()='Please select your annual income']" +
            "/parent::*//*[contains(text(),'Select Annual Income')]" )
    private WebElement annualImcomeDropdown;

    @FindBy (xpath = "//*[text()='Do you Smoke or Chew tobacco?']" +
            "/parent::*//input[@value='Yes']")
    private WebElement yesButton;

    @FindBy (xpath = "//*[text()='Do you Smoke or Chew tobacco?']" +
            "/parent::*//input[@value='No']")
    private WebElement noButton;

    @FindBy ( xpath = "//*[text()='Please choose your occupation type']/parent::*//input[@value='Salaried']" )
    private WebElement salariedButton;

    @FindBy ( xpath = "//*[text()='Please choose your occupation type']/parent::*//input[@value='Self Employed']" )
    private WebElement selfEmployedButton;

    public void selectOccupationType(String occupation){
        if(occupation.equalsIgnoreCase("Salaried")){
            salariedButton.click();
        } else if (occupation.equalsIgnoreCase("Self-Employed")){
            selfEmployedButton.click();
        }
        helper.Wait(2);
    }

    public void selectAnnualIncome(String income){
        annualImcomeDropdown.click();
        helper.Wait(2);
        String xpath = "//*[contains(text(),'Select Annual Income')]" +
                "/following::*[contains(text(),'" + income + "')]";
        wd.findElement(By.xpath(xpath)).click();
        helper.Wait(2);
    }

    public void selectSmokeIND(String smokeIND){
        if(smokeIND.equalsIgnoreCase("Yes")){
            yesButton.click();
        } else if(smokeIND.equalsIgnoreCase("No")){
            noButton.click();
        }
        helper.Wait(1);
    }

    public boolean verifyAnswer3Questionpopup(){
        return helper.isClickableElement(10,answer3Questions);
    }


}
