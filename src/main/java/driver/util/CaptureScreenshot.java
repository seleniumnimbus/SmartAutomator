package driver.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class CaptureScreenshot {

    private WebDriver wd;

    public CaptureScreenshot(WebDriver wd){
        this.wd = wd;
    }

    public String takeFullScreenshot(){
        Screenshot fullScreenshot = new AShot().
                shootingStrategy(ShootingStrategies.viewportPasting(1000)).
                takeScreenshot(wd);
        String currentTimeStamp = new TimeStamp().getCurrentTimeStamp();
        String dest = System.getProperty("user.dir") + "/reports/screenshots/Screenshot_"+ currentTimeStamp +".png";
        try {
            ImageIO.write(fullScreenshot.getImage(),"PNG",
                    new File(dest));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest;
    }

    public String takeScreenshot(){
        TakesScreenshot ts = (TakesScreenshot) wd;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String currentTimeStamp = new TimeStamp().getCurrentTimeStamp();
        String dest = System.getProperty("user.dir") + "/reports/screenshots/Screenshot_"+ currentTimeStamp +".png";
        File destination = new File(dest);
        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest;
    }

}
