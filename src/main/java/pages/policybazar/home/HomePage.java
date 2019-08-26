package pages.policybazar.home;

import driver.util.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private WebDriver wd;
    private Helper helper;

    public HomePage(WebDriver wd){
        this.wd = wd;
        helper = new Helper(wd);
        PageFactory.initElements(wd, this);
    }

    @FindBy(xpath = "(//a[@title='poliybazaar logo'])[1]")
    private WebElement policyBazaarLogo;

    @FindBy(xpath = "//*[contains(text(),'Term Life') and not(@*)]/parent::a")
    private WebElement termLife;

    public boolean verifyHomePage(){
        return helper.visibilityOfElement(20,policyBazaarLogo);
    }

    public void clickTermLife(){
        termLife.click();
        helper.Wait(3);
    }

}
