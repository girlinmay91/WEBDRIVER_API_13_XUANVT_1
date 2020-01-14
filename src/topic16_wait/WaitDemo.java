package topic16_wait;

import java.util.Date;
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

	@Test(enabled = false)
	public void TC2_ImplicitWaitTest() {
		System.out.println("=== Start TC2_ImplicitWaitTest ===");
		System.out.println("Expected [Fail]: Thời gian loading là ~6s, thời gian chờ setting là 2s, check element hiển thị trên UI=> Kết quả: element không hiển thị");
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();

		System.out.println("Wait = " + new Date());
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		System.out.println("Wait = " + new Date());

		Assert.assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).isDisplayed());
		System.out.println("=== End TC2_ImplicitWaitTest ===");
	}

	@Test(enabled = true)
	public void TC3_ExplicitWaitWith3SecondsTest() {
		WebDriverWait wait = new WebDriverWait(driver, 3);

		System.out.println("=== Start TC3_ExplicitWaitWith3SecondsTest ===");
		System.out.println("Expected [Pass]");
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();

		System.out.println("Wait = " + new Date());
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//h4[contains(text(),'Hello World!')]")));
		System.out.println("Wait = " + new Date());

		Assert.assertTrue(!driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).isDisplayed());
		System.out.println("=== End TC3_ExplicitWaitWith3SecondsTest ===");
	}

	@Test(enabled = false)
	public void TC3_ExplicitWaitWith10SecondsTest() {
		WebDriverWait wait = new WebDriverWait(driver, 5);

		System.out.println("=== Start TC3_ExplicitWaitWith5SecondsTest ===");
		System.out.println("Expected [Fail]: Thời gian loading là ~6s, thời gian chờ setting là 10s, check element không hiển thị trên UI => Kết quả: element hiển thị");
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();

		System.out.println("Wait = " + new Date());
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//h4[contains(text(),'Hello World!')]")));
		System.out.println("Wait = " + new Date());
		
		Assert.assertTrue(!driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).isDisplayed());
		System.out.println("=== End TC3_ExplicitWaitWith5SecondsTest ===");

	}

	@Test(enabled = false)
	public void TC4_ExplicitWaitWith3SecondsTest() {
		WebDriverWait wait = new WebDriverWait(driver, 3);

		System.out.println("=== Start TC4_ExplicitWaitWith3SecondsTest ===");
		System.out.println("Expected [Fail]: Thời gian loading là ~6s, thời gian chờ setting là 3s, check element hiển thị trên UI => Kết quả: element không hiển thị");
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();

		System.out.println("Wait = " + new Date());
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(),'Hello World!')]")));
		System.out.println("Wait = " + new Date());
		
		Assert.assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).isDisplayed());
		System.out.println("=== End TC4_ExplicitWaitWith3SecondsTest ===");

	}

	@Test(enabled = false)
	public void TC4_ExplicitWaitWith10SecondsTest() {
		WebDriverWait wait = new WebDriverWait(driver, 10);

		System.out.println("=== Start TC4_ExplicitWaitWith5SecondsTest ===");
		System.out.println("Expected [Pass]: Thời gian loading là ~6s, thời gian chờ setting là 10s, check element hiển thị trên UI => Kết quả: element hiển thị");
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();

		System.out.println("Wait = " + new Date());
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(),'Hello World!')]")));
		System.out.println("Wait = " + new Date());
		
		Assert.assertTrue(driver.findElement(By.xpath("//h4[contains(text(),'Hello World!')]")).isDisplayed());
		System.out.println("=== End TC4_ExplicitWaitWith5SecondsTest ===");

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
