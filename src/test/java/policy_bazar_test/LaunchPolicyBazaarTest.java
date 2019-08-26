package policy_bazar_test;

import base_test.BaseTest;
import driver.data.EnvData;
import driver.util.Helper;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.policybazar.home.HomePage;

public class LaunchPolicyBazaarTest extends BaseTest {

    private Helper helper;
    private HomePage homePage;

    @Test(description = "Launch Policy Bazaar")
    public void launchPolicyBazaar(){
        //Navigate to application
        helper = new Helper(wd);
        helper.getURL(EnvData.getEnvData("PolicyBazaarURL"));

        //Validate Home Page after launching the application
        homePage = new HomePage(wd);
        Assert.assertTrue(homePage.verifyHomePage(),
                "Unable to launch Policy Bazaar");
        reports.logStep("Pass","Policy Bazaar is launched");
    }


}
