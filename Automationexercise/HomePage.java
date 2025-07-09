package Automationexercise.com;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor js;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		this.js = (JavascriptExecutor) driver;
	}

	public void open() {
		driver.get("https://automationexercise.com/");
		wait.until(webDriver -> js.executeScript("return document.readyState").equals("complete"));
	}

	public void scrollAndSelectCategory() {
		js.executeScript("window.scrollTo(0,500)");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@class='badge pull-right'])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/category_products/1']"))).click();
	}
}
