package driver.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvData {

    /**
     * get data from environment property file
     * @param key
     * @return
     */
    public static String getEnvData(String key){
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream("./environment/env.properties");
            //Load properties file
            prop.load(input);
            //get the property value and return it to caller
            return prop.getProperty(key);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
