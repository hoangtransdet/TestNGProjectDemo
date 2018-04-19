package myPackage;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestClass {
	String url = "https://www.phptravels.net/login";
	String driverPath = "C:\\geckodriver.exe";
	String email = "user@phptravels.com";
	String pass = "demouser";
	public WebDriver driver;

	@BeforeClass
	public void prepares() {
		System.out.println("launching firefox browser");
		System.setProperty("webdriver.firefox.marionette", driverPath);
		driver = new FirefoxDriver();
		driver.get(url);
	}

	@Test
	public void test() {
		// Web elements
		WebDriverWait driverWait = new WebDriverWait(driver, 10);		
		WebElement emailTxtBox = driver.findElement(By.xpath("//input[@name='username']"));
		WebElement passwordTxtBox = driver.findElement(By.xpath("//input[@name='password']"));
		WebElement logInBtn = driverWait
				  .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'Login')]")));
				
		// Wait for all elements visible in web page
		driverWait.until(ExpectedConditions.visibilityOf(emailTxtBox));
		driverWait.until(ExpectedConditions.visibilityOf(passwordTxtBox));
		driverWait.until(ExpectedConditions.visibilityOf(logInBtn));
		
		// Enter credential
		emailTxtBox.sendKeys(email);
		passwordTxtBox.sendKeys(pass);
		logInBtn.click();
		
		// Verify if credential is valid
		driverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Hi')]")));
		String expectedTitle = "My Account";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	
	@AfterClass
	public void close() throws IOException {
        driver.quit();
	}

}
