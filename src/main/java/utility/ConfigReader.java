package utility;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    public static Logger logger = Logger.getLogger(ConfigReader.class);

    public static String getProperty(String propertyName) {
        Properties config = new Properties();
        try {
            InputStream iStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties");
            config.load(iStream);
        } catch (IOException e) {
            logger.error("unable to read property " + propertyName + " from file");
        }
        return (config.getProperty(propertyName));
    }
}
