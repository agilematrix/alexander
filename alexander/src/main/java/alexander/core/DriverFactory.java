package alexander.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import alexander.listeners.ScreenshotListener;

import alexander.core.WebDriverThread;
@Listeners(ScreenshotListener.class)

public class DriverFactory {
	 private static List<WebDriverThread> webDriverThreadPool = Collections.synchronizedList(new ArrayList<WebDriverThread>());
	    private static ThreadLocal<WebDriverThread> driverThread;

	    @BeforeSuite
	    public static void instantiateDriverObject() {
	        driverThread = new ThreadLocal<WebDriverThread>() {
	            @Override
	            protected WebDriverThread initialValue() {
	                WebDriverThread webDriverThread = new WebDriverThread();
	                webDriverThreadPool.add(webDriverThread);
	                return webDriverThread;
	            }
	        };
	    }

	    public static WebDriver getDriver() throws Exception {
	        return driverThread.get().getDriver();
	    }

	   @AfterMethod
	    public static void clearCookies() throws Exception {
	        getDriver().manage().deleteAllCookies();
	    }

	    @AfterSuite
	    public static void closeDriverObjects() {
	        for (WebDriverThread webDriverThread : webDriverThreadPool) {
	            webDriverThread.quitDriver();
	        }
	    }
}
