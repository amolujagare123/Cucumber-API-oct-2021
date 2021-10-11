package myProject.Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesDemo {

    public static void main(String[] args) throws IOException {

        // 1 . read the file
        FileInputStream fis = new FileInputStream("Config/Config.properties");

        // 2. Create the properties object
        Properties prop = new Properties();

        // 3. load the file object with prop object
        prop.load(fis);

        // 4. acceess the value of the key in the properties file

        System.out.println(prop.getProperty("baseUrl"));

    }
}
