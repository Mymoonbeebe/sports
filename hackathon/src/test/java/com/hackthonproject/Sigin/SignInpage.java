package com.hackthonproject.Sigin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SignInpage {
	static WebDriver driver;
	public String URL = "https://in.bookmyshow.com/";
	 

	@BeforeTest
	public WebDriver SetupDriver() {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\2081105\\eclipse-workspace\\hackathon\\src\\resource\\test\\drivers\\chromedriver.exe");

		driver = new ChromeDriver(options);

		driver.manage().window().maximize();
		return driver;
	}

	@Test(priority = 1)
	public void openURL() {

		driver.get(URL);

		driver.manage().window().maximize();
		String PageTitle = driver.getTitle();
		Assert.assertEquals(PageTitle, "Movie Tickets, Plays, Sports, Events & Cinemas nearby - BookMyShow");

	}

	@Test(priority = 2)
	public void selectCity() {
		WebElement city = driver.findElement(By.xpath("//input[@placeholder='Search for your city']"));
		city.sendKeys("coimbatore");
		city.sendKeys(Keys.ENTER);

	}

	@Test(priority = 3)
	public void signinBtn() throws InterruptedException {
		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(60));
		WebElement SD = wt.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[@id='super-container']/div[2]/header/div[1]/div/div/div/div[2]/div[2]/div[1]")));

		SD.click();
Thread.sleep(3000);
		
	}

	@Test(priority = 4)
	public void continuewithGoogle() {
		
	
		driver.findElement(By.xpath("//*[@id='modal-root']/div/div/div/div/div[2]/div/div[1]/div/div[2]/div/div/span/span/img")).click();
		String window=driver.getWindowHandle();
		Set<String> MainWindow=driver.getWindowHandles();
		Iterator<String> itr=MainWindow.iterator();
		while(itr.hasNext())
		{
			String currentWindow=itr.next();
			if(!window.equals(currentWindow))
			{
				driver.switchTo().window(currentWindow);
			}
		}
	}
		
	@Test(priority=5)
	public void sendDetails()
	{
	 WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(50));
		WebElement email=wait1.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("identifier"))));
		email.sendKeys("12345cee@gmail.com");
		driver.findElement(By.xpath("//*[@id='identifierNext']/div/button/span")).click();

		try {
			Thread.sleep(1000);
			driver.findElement(By.xpath("//*[@id='password']/div[1]/div/div[1]/input")).sendKeys("1234");

		} catch (Exception e) {
			takeScreenshot();
		}
	}

	public void takeScreenshot() {
		TakesScreenshot capture = (TakesScreenshot) driver;
		File srcFile = capture.getScreenshotAs(OutputType.FILE);
		try {
			FileHandler.copy(srcFile, new File("./Screenshot/"+timestamp()+"errMsg.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String timestamp() {
		// TODO Auto-generated method stub
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}

	@AfterTest

	public void closeBrowser() {
		driver.quit();

	}
}
