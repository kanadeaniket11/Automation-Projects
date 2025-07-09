package Automationexercise.com;

public class TestRunner extends BaseTest {
	public static void main(String[] args) throws InterruptedException {
		TestRunner test = new TestRunner();
		test.setup();

		try {
			HomePage home = new HomePage(test.driver);
			ProductPage productPage = new ProductPage(test.driver);
			CartPage cartPage = new CartPage(test.driver);
			SignupPage signupPage = new SignupPage(test.driver);
			PaymentPage paymentPage = new PaymentPage(test.driver);

			home.open();
			home.scrollAndSelectCategory();

			productPage.addProductToCart("Sleeveless Dress");
			productPage.verifyAddToCartMessage();
			productPage.clickViewCart();

			cartPage.verifyProductInCart("Sleeveless Dress");
			cartPage.proceedToCheckout();

			signupPage.signup("Aniket", "kanadeaniket3211@gmail.com");
			signupPage.fillDetailsAndCreateAccount();

			cartPage.proceedToCheckout();
			cartPage.verifyProductInCart("Sleeveless Dress");

			paymentPage.enterPaymentDetailsAndPlaceOrder();
		} finally {
			test.tearDown();
		}
	}
}