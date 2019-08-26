package driver.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class Base {

    private static WebDriver wd;
    private static final String CHROMEDRIVERPATH = "driver/chromedriver.exe";

    /**
     * Launch Browser
     * @param browserType
     * @param stopNotification
     * @return
     */
    public static WebDriver launchBrowser(String browserType, boolean stopNotification){
        if(browserType.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver",CHROMEDRIVERPATH);
            if(stopNotification) {
                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("profile.default_content_setting_values.notifications", 2);
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", prefs);
                wd = new ChromeDriver(options);
            } else {
                wd = new ChromeDriver();
            }
        }
        wd.manage().window().maximize();
        return wd;
    }

}
