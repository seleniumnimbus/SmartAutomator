package base_test;

import driver.data.AutomationInputData;
import driver.data.EnvData;
import driver.reports.GenerateExtentReports;
import driver.util.Base;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.net.MalformedURLException;

public class BaseTest {

    public static WebDriver wd;
    public static GenerateExtentReports reports;
    public static AutomationInputData data;


    @BeforeSuite
    @Parameters({"env","applicationName"})
    public void setupSuite(String env,String applicationName){
        GenerateExtentReports.setUp(env,applicationName);
    }

    @BeforeTest
    @Parameters({"TestCaseName","TestCaseDes"})
    public void setupTest(String TestCaseName,String TestCaseDes){
        wd = Base.launchBrowser(EnvData.getEnvData("BrowserType"), true);
        reports = new GenerateExtentReports(wd);
        data = new AutomationInputData(TestCaseName);
        reports.startTestReport(TestCaseName + " : " + TestCaseDes);
    }

    @AfterMethod
    public void tearDownTest(ITestResult result){
        if(result.getStatus() == ITestResult.SUCCESS){
            reports.generateResult("Pass",result);
        } else if(result.getStatus() == ITestResult.FAILURE){
            reports.generateResult("Fail",result);
        } else if(result.getStatus() == ITestResult.SKIP){
            reports.generateResult("Skip",result);
        }
    }

    @AfterTest
    public void closeTest(){
        wd.quit();
    }

    @AfterSuite
    public void endReport() {
        reports.endExtent();
    }


}
