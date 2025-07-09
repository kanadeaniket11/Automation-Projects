package Automationexercise.com;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class SignupPage {
	private WebDriver driver;

	public SignupPage(WebDriver driver) {
		this.driver = driver;
	}

	public void signup(String name, String email) {
		driver.findElement(By.xpath("//u[contains(.,'Register / Login')]")).click();
		driver.findElement(By.name("name")).sendKeys(name);
		driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(email);
		driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();
	}

	public void fillDetailsAndCreateAccount() {
		driver.findElement(By.id("id_gender1")).click();
		driver.findElement(By.id("password")).sendKeys("Aniket11@");

		new Select(driver.findElement(By.id("days"))).selectByIndex(5);
		new Select(driver.findElement(By.id("months"))).selectByIndex(5);
		new Select(driver.findElement(By.id("years"))).selectByIndex(5);

		driver.findElement(By.name("newsletter")).click();
		driver.findElement(By.name("optin")).click();

		driver.findElement(By.id("first_name")).sendKeys("Aniket");
		driver.findElement(By.id("last_name")).sendKeys("Kanade");
		driver.findElement(By.id("company")).sendKeys("Dinero Software");
		driver.findElement(By.id("address1")).sendKeys("Thane");
		driver.findElement(By.id("address2")).sendKeys("Thane");
		driver.findElement(By.id("state")).sendKeys("Maharashtra");
		driver.findElement(By.id("city")).sendKeys("Thane");
		driver.findElement(By.id("zipcode")).sendKeys("400606");
		driver.findElement(By.id("mobile_number")).sendKeys("8451881581");

		driver.findElement(By.xpath("//button[@data-qa='create-account']")).click();
		assert driver.findElement(By.xpath("//b[contains(.,'Account Created!')]")).isDisplayed();
		driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click();
	}
}