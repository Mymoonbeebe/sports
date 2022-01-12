package com.hackthonproject.Sigin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSetup {
	private static WebDriver driver;
	public static WebDriver getFirefoxDriver()
	{
		String driverPath = System.getProperty("user.dir")
				+ "\\drivers\\geckodriver.exe";
				System.setProperty("webdriver.gecko.driver", driverPath);
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
	
	return driver;
	}
	public static WebDriver getedgeDriver()
	{
		String driverPath = System.getProperty("user.dir")
				+ "\\drivers\\msedgedriver.exe";
				System.setProperty("webdriver.edge.driver", driverPath);
				driver = new EdgeDriver();
				driver.manage().window().maximize();
				return driver;
	}
	public static WebDriver getChrome()
	{
		String driverPath = System.getProperty("user.dir")
				+ "\\drivers\\chromedriver.exe";
				System.setProperty("webdriver.chrome.driver", driverPath);
				driver = new ChromeDriver();
				driver.manage().window().maximize();
	
	return driver;
	}

	}



