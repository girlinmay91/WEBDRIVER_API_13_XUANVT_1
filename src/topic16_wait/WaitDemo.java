package topic16_wait;

import java.time.Duration;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class WaitDemo {
	WebDriver driver;

	@Test(enabled = false)
	public void TC2_ImplicitWaitTest() {
		System.out.println("=== Start TC2_ImplicitWaitTest ===");
		System.out.println("Expected [Fail]");
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();

		System.out.println("Wait = " + new Date());
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		System.out.println("Wait = " + new Date());

		Assert.assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).isDisplayed());
		System.out.println("=== End TC2_ImplicitWaitTest ===");
	}

	@Test(enabled = false)
	public void TC3_ExplicitWaitWithMax3SecondsTest() {
		WebDriverWait wait = new WebDriverWait(driver, 3);

		System.out.println("=== Start TC3_ExplicitWaitWithMax3SecondsTest ===");
		System.out.println("Expected [Pass]");
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();

		System.out.println("Wait = " + new Date());
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']//img")));
		System.out.println("Wait = " + new Date());
	
		Assert.assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).isDisplayed());
		System.out.println("=== End TC3_ExplicitWaitWithMax3SecondsTest ===");
	}

	@Test(enabled = false)
	public void TC3_ExplicitWaitWithMax5SecondsTest() {
		WebDriverWait wait = new WebDriverWait(driver, 5);

		System.out.println("=== Start TC3_ExplicitWaitWithMax5SecondsTest ===");
		System.out.println("Expected [Fail]");
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();

		System.out.println("Wait = " + new Date());
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']//img")));
		System.out.println("Wait = " + new Date());
	
		Assert.assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).isDisplayed());
		System.out.println("=== End TC3_ExplicitWaitWithMax5SecondsTest ===");
	}

	@Test(enabled = false)
	public void TC4_ExplicitWaitWithMax3SecondsTest() {
		WebDriverWait wait = new WebDriverWait(driver, 3);

		System.out.println("=== Start TC4_ExplicitWaitWithMax3SecondsTest ===");
		System.out.println("Expected [Pass]");
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();

		System.out.println("Wait = " + new Date());
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[ (text(),'Hello World!')]")));
		System.out.println("Wait = " + new Date());
		
		Assert.assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).isDisplayed());
		System.out.println("=== End TC4_ExplicitWaitWithMax3SecondsTest ===");
	}

	@Test(enabled = false)
	public void TC4_ExplicitWaitWithMax5SecondsTest() {
		WebDriverWait wait = new WebDriverWait(driver, 5);

		System.out.println("=== Start TC4_ExplicitWaitWithMax5SecondsTest ===");
		System.out.println("Expected [Pass]");
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();

		System.out.println("Wait = " + new Date());
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(),'Hello World!')]")));
		System.out.println("Wait = " + new Date());
		
		Assert.assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).isDisplayed());
		System.out.println("=== End TC4_ExplicitWaitWithMax5SecondsTest ===");
	}
	
	@Test(enabled = false)
	public void TC5_ExplicitWaitDatePickerTest() {
		WebDriverWait wait = new WebDriverWait(driver, 5);

		System.out.println("=== Start TC5_ExplicitWaitDatePickerTest ===");
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		System.out.println("Wait = " + new Date());
		wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//div[@id='ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_RadCalendar1Panel']"))));
		System.out.println("Wait = " + new Date());
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel']")).getText(), "No Selected Dates to display.");
		
		
		driver.findElement(By.xpath("//a[contains(text(),'19')]")).click();
		
		System.out.println("Wait = " + new Date());
		wait.until(ExpectedConditions.invisibilityOfElementLocated((By.xpath("//div[@class='raDiv']"))));
		System.out.println("Wait = " + new Date());
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel']")).getText(), "Sunday, January 19, 2020");
		System.out.println("=== End TC5_ExplicitWaitDatePickerTest ===");
	}
	
	@Test(enabled = true)
	public void TC6_FluentWaitTest() {
		WebDriverWait waitEx = new WebDriverWait(driver, 30);
	
		System.out.println("=== Start TC6_FluentWaitTest ===");
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		WebElement countDown = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		waitEx.until(ExpectedConditions.visibilityOf(countDown));
		
		new FluentWait<WebElement>(countDown)
			.withTimeout(Duration.ofSeconds(15))
			.pollingEvery(Duration.ofSeconds(1))
			.ignoring(NoSuchElementException.class)
			.until(new Function<WebElement, Boolean>() {
				@Override
				public Boolean apply(WebElement el) {
					boolean flg = el.getText().endsWith("02");
					System.out.println("Time = " + el.getText());
					return flg;
				}
			});
		
		System.out.println("=== End TC6_FluentWaitTest ===");
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
