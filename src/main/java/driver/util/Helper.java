package driver.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {

    private WebDriver wd;
    private WebDriverWait wait;
    private Actions actions;

    public Helper(WebDriver wd){
        this.wd = wd;
    }

    public boolean visibilityOfElement(int timeInsecond, WebElement we){
        wait = new WebDriverWait(wd,timeInsecond);
        try {
            wait.until(ExpectedConditions.visibilityOf(we));
            wait = null;
            return true;
        } catch(Exception e){
            System.out.println("Object is not visible");
            wait = null;
            return false;
        }

    }

    public boolean isClickableElement(int timeInsecond,WebElement we){
        wait = new WebDriverWait(wd,timeInsecond);
        try{
            wait.until(ExpectedConditions.elementToBeClickable(we));
            wait = null;
            return true;
        } catch(Exception e){
            System.out.println("Object is not clickable");
            wait = null;
            return false;
        }
    }

    public boolean presenceOfElement_ByXpath(int timeInsecond,String xPathExp){
        wait = new WebDriverWait(wd,timeInsecond);
        try{
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPathExp)));
            wait = null;
            return true;
        } catch(Exception e){
            System.out.println("Object is not present in the application");
            wait = null;
            return false;
        }
    }

    public void Wait(int timeInSecond){
        try {
            Thread.sleep(timeInSecond*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void getURL(String url){
        wd.get(url);
        Wait(2);
    }

    public Helper moveMouseToElement(WebElement targetElement){
        actions = new Actions(wd);
        if(isClickableElement(5,targetElement)){
            actions.moveToElement(targetElement).build().perform();
            Wait(1);
        }
        actions = null;
        return this;
    }

}
