package Automationexercise.com;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

public class ProductPage {
	private WebDriver driver;
	private WebDriverWait wait;

	public ProductPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	public void addProductToCart(String productName) {
		List<WebElement> products = driver.findElements(By.cssSelector(".single-products"));
		WebElement product = products.stream()
				.filter(p -> p.findElement(By.cssSelector("p")).getText().equalsIgnoreCase(productName)).findFirst()
				.orElse(null);

		if (product != null) {
			product.findElement(By.cssSelector(".add-to-cart")).click();
		} else {
			throw new RuntimeException("Product '" + productName + "' not found.");
		}
	}

	public void verifyAddToCartMessage() {
		WebElement message = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p[@class='text-center'])[1]")));
		assert message.getText().trim().equals("Your product has been added to cart.");
	}

	public void clickViewCart() {
		driver.findElement(By.xpath("//u[contains(.,'View Cart')]")).click();
	}
}