package Playwright.Playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class W3schoolscom {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Playwright playwright = Playwright.create();
		LaunchOptions lp = new LaunchOptions();
		lp.setHeadless(false);
		Browser browser = playwright.chromium().launch(lp);
		BrowserContext context = browser.newContext();
		Page page = context.newPage();
		page.setDefaultTimeout(600000);
		page.navigate("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_iframe");
		// page.locator("(//a[@title='CSS Tutorial'])[3]").click();
		FrameLocator frame1 = page.frameLocator("//iframe[@name='iframeResult']");
		FrameLocator frame2 = frame1.frameLocator("iframe[title='W3Schools Free Online Web Tutorials']");
		frame2.locator("//a[@title='CSS Tutorial']").last().click();
		page.locator("//a[@id='getwebsitebtn']").click();
		page.close();

	}

}
