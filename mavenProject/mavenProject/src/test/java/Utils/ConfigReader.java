package Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;

    public ConfigReader() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            // load a properties file from the class path, inside static method
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getBrowserType() {
        return properties.getProperty("browser.type");
    }

    public String getDriverPath() {
        return properties.getProperty("browser.driver.path");
    }

    public String getAppURL() {
        return properties.getProperty("app.url");
    }

    public String getUserName() {
        return properties.getProperty("username");
    }

    public String getUserInformationFirstName() {
        return properties.getProperty("firstName");
    }

    public String getUserInformationLastName() {
        return properties.getProperty("lastName");
    }

    public String getUserInformationZipPostalCode() {
        return properties.getProperty("zipPostalCode");
    }
    public String getPassWord() {
        return properties.getProperty("password");
    }

    public int getImplicitWait() {
        return Integer.parseInt(properties.getProperty("implicit.wait"));
    }

    public int getPageLoadTimeout() {
        return Integer.parseInt(properties.getProperty("page.load.timeout"));
    }
}

