package myProject.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    static Properties getLoadedPropertiesObject() throws IOException {
        // 1 . read the file
        FileInputStream fis = new FileInputStream("Config/Config.properties");

        // 2. Create the properties object
        Properties prop = new Properties();

        // 3. load the file object with prop object
        prop.load(fis);

        return prop;
    }

    public static String getBaseUrl() throws IOException {

       return getLoadedPropertiesObject().getProperty("baseUrl");
    }
}
