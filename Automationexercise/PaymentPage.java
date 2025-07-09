package Automationexercise.com;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class PaymentPage {
	private WebDriver driver;
	private WebDriverWait wait;

	public PaymentPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	public void enterPaymentDetailsAndPlaceOrder() {
		driver.findElement(By.xpath("//a[@href='/payment']")).click();

		driver.findElement(By.name("name_on_card")).sendKeys("Aniket Kanade");
		driver.findElement(By.name("card_number")).sendKeys("12345678910");
		driver.findElement(By.name("cvc")).sendKeys("123");
		driver.findElement(By.name("expiry_month")).sendKeys("06");
		driver.findElement(By.name("expiry_year")).sendKeys("2030");
		driver.findElement(By.id("submit")).click();

		WebElement confirmation = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[normalize-space()='Order Placed!']")));
		assert confirmation.getText().trim().equals("ORDER PLACED!");
	}
}
