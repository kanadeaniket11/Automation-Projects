package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class MedscapeAutomation2 {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		try {
			driver.manage().window().maximize();
			driver.get("https://www.medscape.com/");

			// Wait for full page load
			wait.until(webDriver -> js.executeScript("return document.readyState").equals("complete"));
			System.out.println("Page fully loaded.");

			// Wait for home section
			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(.,'Today on Medscape')]")));
			Thread.sleep(3000);

			// Scroll down gradually
			for (int i = 1; i <= 10; i++) {
				js.executeScript("window.scrollBy(0,500)");
				Thread.sleep(1000);
			}

			// Scroll back to top
			js.executeScript("window.scrollTo(0,0)");
			Thread.sleep(2000);

			// Click "Tools & Reference" tab
			List<WebElement> headers = driver.findElements(By.cssSelector(".header-tab"));
			WebElement optionElement1 = headers.stream().filter(s -> s.getText().equalsIgnoreCase("Tools & Reference"))
					.findFirst().orElseThrow();
			optionElement1.click();

			wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(.,'Tools & Reference')]")));
			Thread.sleep(2000);

			// Scroll again
			for (int i = 1; i <= 3; i++) {
				js.executeScript("window.scrollBy(0,500)");
				Thread.sleep(1000);
			}

			// Click "CME/CE" tab
			List<WebElement> updatedHeaders = driver.findElements(By.cssSelector(".header-tab"));
			WebElement optionElement2 = updatedHeaders.stream().filter(s -> s.getText().equalsIgnoreCase("CME/CE"))
					.findFirst().orElseThrow();
			optionElement2.click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.specialty div.title")));
			Thread.sleep(1000);

			// Registration flow
			driver.findElement(By.xpath("(//a[contains(text(),'Register')])[2]")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("regEmail")));

			driver.findElement(By.name("regEmail")).sendKeys("kanadeaniket11@gmail.com");
			driver.findElement(By.name("regPassword")).sendKeys("Aniket121@");

			// Click Continue
			driver.findElement(By.xpath("//button[contains(@class,'mdscp-button--submit')]")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName")));

			driver.findElement(By.name("firstName")).sendKeys("Aniket");
			driver.findElement(By.name("lastName")).sendKeys("Kanade");

			// Select country
			WebElement countrySpan = driver
					.findElement(By.xpath("//span[normalize-space()='Begin typing and select an option below...']"));
			js.executeScript("arguments[0].scrollIntoView(true);", countrySpan);
			countrySpan.click();

			WebElement countryInput = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.cssSelector("input[aria-controls='listbox-country']")));
			countryInput.sendKeys("ind");

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("listbox-country")));
			List<WebElement> options = driver.findElements(By.cssSelector(".multiselect__element"));
			for (WebElement option : options) {
				if (option.getText().equalsIgnoreCase("India")) {
					option.click();
					break;
				}
			}

			// Enter Zip Code
			WebElement zipInput = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//input[@placeholder='Enter Zip / Postal Code']")));
			zipInput.sendKeys("400606");
			Thread.sleep(5000);
			driver.findElement(By.xpath("//input[@placeholder='Enter Zip / Postal Code']")).sendKeys("400606");
			Thread.sleep(2000);

			// Click on the custom dropdown
			WebElement degreeDropdown = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//div[@class='input-group']//div[@class='multiselect__tags']")));
			degreeDropdown.click();

			// Wait until the dropdown options are visible
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@class='input-group']//div[@class='multiselect__content-wrapper']")));

			// Select "Physician" from the list
			List<WebElement> degreeOptions = driver.findElements(By.cssSelector(".multiselect__element"));
			for (WebElement option : degreeOptions) {
				if (option.getText().trim().equalsIgnoreCase("Physician")) {
					option.click();
					break;
				}
			}
			// *************************************************************************************************************************************************
			// 1. Wait briefly before interacting
			Thread.sleep(3000); // Ensure the section is fully rendered

			// 2. Click the placeholder to activate the dropdown
			WebElement specialtyPlaceholder = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='multiselect__placeholder']")));
			specialtyPlaceholder.click(); // ✅ Only click once

			// 3. Type into the visible input field
			WebElement specialtyInput = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='specialty']")));
			specialtyInput.sendKeys("Cardi");

			// 4. Wait for dropdown options to load
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='listbox-specialty']")));
			Thread.sleep(1000); // Let items load

			// 5. Loop through options and click "Cardiology"
			List<WebElement> optionss = driver.findElements(By.cssSelector(".multiselect__element"));
			boolean found = false;
			for (WebElement option : optionss) {
				if (option.getText().trim().equalsIgnoreCase("Cardiology")) {
					option.click();
					found = true;
					System.out.println("✅ 'Cardiology' selected.");
					break;
				}
			}
			if (!found) {
				System.out.println("❌ 'Cardiology' not found in dropdown options.");
			}
			// Click on checkbox
			WebElement checkbox = driver.findElement(By.xpath("//span[@class='checkbox-custom circular']"));
			js.executeScript("arguments[0].click();", checkbox);

		} catch (TimeoutException e) {
			System.out.println("Timeout: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error occurred: " + e.getMessage());
		} finally {
			driver.quit();
		}

	}
}