package Automationexercise.com;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class CartPage {
	private WebDriver driver;
	private WebDriverWait wait;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	public void verifyProductInCart(String productName) {
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart_description a"));
		boolean found = cartItems.stream().anyMatch(item -> item.getText().equalsIgnoreCase(productName));
		assert found : productName + " not found in the cart.";
	}

	public void proceedToCheckout() {
		driver.findElement(By.xpath("//a[@href='/view_cart']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn.btn-default.check_out")));
		driver.findElement(By.cssSelector(".btn.btn-default.check_out")).click();
	}
}
