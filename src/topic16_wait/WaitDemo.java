package topic16_wait;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WaitDemo {
	WebDriver driver;
	
	@Test(enabled=true)
	public void TC2_ImplicitWaitTest() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
		
		//driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS); // Fail: run in ~6.7s
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Pass: run in ~10.6s
		Assert.assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).isDisplayed());
	}
	
	@Test(enabled=true) 
	public void TC3_ExplicitWaitWith3SecondsTest() {
		WebDriverWait wait = new WebDriverWait(driver, 3); // Fail: run in ~5.5s
		
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//h4[contains(text(),'Hello World!')]")));
		Assert.assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).isDisplayed());
		
	}
	
	@Test(enabled=true) 
	public void TC3_ExplicitWaitWith5SecondsTest() {
		WebDriverWait wait = new WebDriverWait(driver, 5); // Fail: run in ~5.4s
		
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//h4[contains(text(),'Hello World!')]")));
		Assert.assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).isDisplayed());
		
	}
	
	@Test(enabled=true) 
	public void TC4_ExplicitWaitWith3SecondsTest() {
		WebDriverWait wait = new WebDriverWait(driver, 3); // Pass: run in ~5.5s
		
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(),'Hello World!')]")));
		Assert.assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).isDisplayed());
		
	}
	
	@Test(enabled=true) 
	public void TC4_ExplicitWaitWith5SecondsTest() {
		// WebDriverWait wait = new WebDriverWait(driver, 5); // Pass: run in ~5.5s
		WebDriverWait wait = new WebDriverWait(driver, 2); // Pass: run in ~5.5s
		
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(),'Hello World!')]")));
		Assert.assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).isDisplayed());
		
	}

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", ".\\libs\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
