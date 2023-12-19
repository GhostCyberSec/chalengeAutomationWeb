package Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;


public class AuxiliarMethods {

    public static void takeScreenshot1(WebDriver driver, String fileName) {
        // Convert WebDriver instance to TakesScreenshot
        TakesScreenshot screenshot = (TakesScreenshot) driver;

        // Capture the screenshot as a file
        File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);

        // Specify the destination file path
        File destinationFile = new File(fileName);

        try {
            // Copy the screenshot file to the destination
            FileUtils.copyFile(screenshotFile, destinationFile);
            System.out.println("Screenshot saved to: " + destinationFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error while taking screenshot: " + e.getMessage());
        }
    }

    public static void takeScreenshot2(WebDriver driver, String destinationFolder, String fileName) {
        // Check if the driver supports taking screenshots
        if (driver instanceof TakesScreenshot) {
            TakesScreenshot screenshotDriver = (TakesScreenshot) driver;

            // Capture the screenshot
            File screenshotFile = screenshotDriver.getScreenshotAs(OutputType.FILE);

            // Define the destination folder and file path
            String filePath = destinationFolder + File.separator + fileName;

            try {
                // Move the screenshot to the specified location
                org.apache.commons.io.FileUtils.copyFile(screenshotFile, new File(filePath));
                System.out.println("Screenshot saved to: " + filePath);
            } catch (IOException e) {
                System.err.println("Error saving screenshot: " + e.getMessage());
            }
        } else {
            System.err.println("WebDriver does not support taking screenshots");
        }


    }
}
