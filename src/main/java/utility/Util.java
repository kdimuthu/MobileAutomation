package utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Util {
    private static Properties pro;

    /**
     * Read data from property files.
     */

    Util() {
        try {
            File src = new File("./src/main/resources/config.properties");
            FileInputStream fis = new FileInputStream(src);
            pro = new Properties();
            pro.load(fis);
        } catch (Exception e) {
            System.out.println("Exception is :" + e);
        }
    }

    public static String getPropertyFileData(String key) {
        return pro.getProperty(key);
    }
}
