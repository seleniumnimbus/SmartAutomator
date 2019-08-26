package pages.policybazar.term_life;

import driver.util.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.policybazar.term_life.pages.Answer3Questions;
import pages.policybazar.term_life.pages.TermLifeCustomerDetailsPage;

public class TermLife {

    private WebDriver wd;
    private Helper helper;

    public TermLife(WebDriver wd){
        this.wd = wd;
        helper = new Helper(wd);
        PageFactory.initElements(wd, this);
    }

    public Answer3Questions getAnswer3Questions() {
        return new Answer3Questions(wd);
    }

    public TermLifeCustomerDetailsPage getTermLifeCustomerDetailsPage() {
        return new TermLifeCustomerDetailsPage(wd);
    }

}
