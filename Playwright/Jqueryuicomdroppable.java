package Playwright.Playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.FrameLocator;

public class Jqueryuicomdroppable {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		LaunchOptions lp = new LaunchOptions();
		lp.setHeadless(false);
		Browser browser = playwright.chromium().launch(lp);
		BrowserContext context = browser.newContext();
		Page page = context.newPage();
		page.setDefaultTimeout(600000);
		page.navigate("https://jqueryui.com/droppable/");
		FrameLocator locator_frame = page.frameLocator("//iframe[@class='demo-frame']");
		locator_frame.locator("//div[@id='draggable']").dragTo(locator_frame.locator("//div[@id='droppable']"));

	}

}
