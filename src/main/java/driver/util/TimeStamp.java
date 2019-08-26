package driver.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeStamp {

    public String getCurrentTimeStamp(){
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        String reportDateTime = df.format(today);
        reportDateTime = reportDateTime.
                replace("/", "_").
                replace(":", "_").
                replace(" ", "_");
        return reportDateTime;
    }

}
