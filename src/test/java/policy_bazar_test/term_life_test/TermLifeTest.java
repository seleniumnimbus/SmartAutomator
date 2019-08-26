package policy_bazar_test.term_life_test;

import base_test.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.policybazar.home.HomePage;
import pages.policybazar.term_life.TermLife;

public class TermLifeTest extends BaseTest {

    private TermLife termLife;
    private HomePage homePage;

    @Test(description = "Select Term Life")
    public void selectTermLife(){
        //Select Term Life
        homePage = new HomePage(wd);
        termLife = new TermLife(wd);

        homePage.clickTermLife();

        Assert.assertTrue(termLife
                        .getTermLifeCustomerDetailsPage()
                        .verifyTermLifeCustomerDetailsPage(),
                "Unable to navigate to Term Life Insurance page");
        reports.logStep("Pass","Navigate to Term Life Insurance Customer Details page");
    }


    @Test(description = "Enter customer details and navigate to Answer 3 Question popup",
            dependsOnMethods = "selectTermLife")
    public void enterCustomerDetails() {

        //termLife = new TermLife(wd);

        reports.logInfo("Customer Name : " + data.getData("CustomerName"));
        reports.logInfo("Customer DOB : " + data.getData("DOB"));
        reports.logInfo("Customer Mobile No : " + data.getData("MobileNo"));

        termLife
                .getTermLifeCustomerDetailsPage()
                .enterCustomerName(data.getData("CustomerName"));

        termLife
                .getTermLifeCustomerDetailsPage()
                .enterCustomerDOB(data.getData("DOB"));

        termLife
                .getTermLifeCustomerDetailsPage()
                .enterMobileNo(data.getData("MobileNo"));

        termLife
                .getTermLifeCustomerDetailsPage()
                .clickViewFreeQuotes();

        Assert.assertTrue(termLife
                        .getAnswer3Questions()
                        .verifyAnswer3Questionpopup(),
                "Application is unable to navigate to Answer 3 Question popup");
        reports.logStep("Pass","Application is navigated to Answer 3 Question popup");
    }


    @Test(description = "Answer 3 Questions",
            dependsOnMethods = "enterCustomerDetails")
    public void answer3Questions(){
        //termLife = new TermLife(wd);

        reports.logInfo("Do you Smoke or Chew tobacco? : " + data.getData("SmokeIND"));
        reports.logInfo("Please select your annual income : " + data.getData("AnnualIncome"));
        reports.logInfo("Please choose your occupation type : " + data.getData("Occupation"));

        termLife
                .getAnswer3Questions()
                .selectSmokeIND(data.getData("SmokeIND"));

        termLife
                .getAnswer3Questions()
                .selectAnnualIncome(data.getData("AnnualIncome"));

        termLife
                .getAnswer3Questions()
                .selectOccupationType(data.getData("Occupation"));

        Assert.assertFalse(termLife
                        .getAnswer3Questions()
                        .verifyAnswer3Questionpopup(),
                "Unable to answer 3 questions");
        System.out.println("Answer 3 Questions as per inputs");
        reports.logStep("Pass","Answer 3 Questions as per inputs");
    }



}
