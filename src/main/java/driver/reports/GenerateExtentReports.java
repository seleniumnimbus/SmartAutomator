package driver.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import driver.data.EnvData;
import driver.util.CaptureScreenshot;
import driver.util.TimeStamp;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.IOException;

public class GenerateExtentReports {

    private static ExtentHtmlReporter htmlReporter;
    private static ExtentReports extent;
    private static ExtentTest test;
    private WebDriver wd;

    public GenerateExtentReports(WebDriver wd){
        this.wd = wd;
    }

    public static void setUp(String env,String applicationName){

        String currentTimeStamp = new TimeStamp().getCurrentTimeStamp();
        String reportsPath = System.getProperty("user.dir") + "/reports/html/AutomationReports_" + currentTimeStamp + ".html";
        htmlReporter = new ExtentHtmlReporter(reportsPath);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        extent.setSystemInfo("Host Name", EnvData.getEnvData("UserName"));
        extent.setSystemInfo("Environment", env);
        extent.setSystemInfo("Application Name", applicationName);

        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Automation Reports");
        htmlReporter.config().setReportName("Reports : " + applicationName);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.DARK);

    }

    public void generateResult(String resultStatus, ITestResult result){

        if(resultStatus.equalsIgnoreCase("Pass")){
            //test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
        } else if(resultStatus.equalsIgnoreCase("Fail")){
            String screenShotPath = new CaptureScreenshot(wd).takeFullScreenshot();
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() +
                    " Test case FAILED due to below issues:", ExtentColor.RED));
            test.fail(result.getThrowable());
            try {
                test.fail("Snapshot below: " + test.addScreenCaptureFromPath(screenShotPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }

    }

    public void startTestReport(String TCID){
        test = extent.createTest(TCID);
    }

    public void logStep(String resultStatus,String logMessage){
        if(resultStatus.equalsIgnoreCase("Pass")){
            test.log(Status.PASS, logMessage);
        } else if(resultStatus.equalsIgnoreCase("Fail")){
            String screenShotPath = new CaptureScreenshot(wd).takeFullScreenshot();
            test.log(Status.FAIL, logMessage);
            try {
                test.fail("Snapshot below: " + test.addScreenCaptureFromPath(screenShotPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void logInfo(String logMessage){
        test.log(Status.INFO, logMessage);
    }

    public void endExtent(){
        extent.flush();
    }

}
