package com.cssRobot.Execution;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cssRobot.ResultLogger.ResultLogger;
import com.cssRobot.ResultLogger.ResultLogger.ISSTEPACTION;
import com.cssRobot.ResultLogger.ResultLogger.RESULT;
import com.cssRobot.Start.EntryPoint;
import com.cssRobot.Storage.Storage;

import io.appium.java_client.AppiumDriver;

public class Browser {

	public static void launchBrowser() {
		try {

			if (Storage.OSType.equalsIgnoreCase("WINDOWS") && Storage.Browser_Name.equalsIgnoreCase("Firefox")
					&& Storage.AutomationToolType.equalsIgnoreCase("In-house")) {
				// URL cl= ClassLoader.getSystemResource("geckodriver.exe");
				// //Properties p=System.getProperties();
				//
				// File file=new File(System.getProperty("java.io.tmpdir")+"\\geckodriver.exe");
				//
				// if(!file.exists()) {
				// FileUtils.copyFile(new File(cl.getFile()),file);
				//
				// }

				System.setProperty("webdriver.gecko.driver", "C:\\Automation\\WebDrivers\\geckodriver.exe");
				// EntryPoint.driver=new FirefoxDriver();

				if (Storage.isGridExecution.equalsIgnoreCase("true")) {
					DesiredCapabilities cap = DesiredCapabilities.firefox();

					EntryPoint.driver = new RemoteWebDriver(new URL("http://" + Storage.agentName + ":5567/wd/hub"),
							cap);

				} else {

					EntryPoint.driver = new FirefoxDriver();
				}
			} else if (Storage.OSType.equalsIgnoreCase("WINDOWS") && Storage.Browser_Name.equalsIgnoreCase("IE")
					&& Storage.AutomationToolType.equalsIgnoreCase("In-house")) {
				// URL cl= ClassLoader.getSystemResource("IEDriverServer.exe");
				// //Properties p=System.getProperties();
				//
				// File file=new
				// File(System.getProperty("java.io.tmpdir")+"\\IEDriverServer.exe");
				//
				// if(!file.exists()) {
				// FileUtils.copyFile(new File(cl.getFile()),file);
				//
				// }

				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				InternetExplorerOptions capabilities1 = new InternetExplorerOptions(capabilities);
				// capabilities.a
				System.setProperty("webdriver.ie.driver", "c:\\Automation\\WebDrivers\\IEDriverServer.exe");

				if (Storage.isGridExecution.equalsIgnoreCase("true")) {
					DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
					// cap = DesiredCapabilities.internetExplorer();
					// cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
					// true);

					EntryPoint.driver = new RemoteWebDriver(new URL("http://" + Storage.agentName + ":5568/wd/hub"),
							cap);

				} else {

					EntryPoint.driver = new InternetExplorerDriver(capabilities1);
				}
			}

			else if (Storage.OSType.equalsIgnoreCase("Android") && Storage.Browser_Name.equalsIgnoreCase("Chrome")
					&& Storage.AutomationToolType.equalsIgnoreCase("BROWSERSTACK")) {

				String USERNAME = "mktesttestingsfg1";

				String AUTOMATE_KEY = "15E7wBpGrqGy3bBqF48L";

				String URL = "http://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

				DesiredCapabilities caps = new DesiredCapabilities();

				caps.setCapability("browserName", "android");
				caps.setCapability("device", "Samsung Galaxy Note4");
				caps.setCapability("realMobile", "true");
				caps.setCapability("os_version", "6.0");

				EntryPoint.driver = new RemoteWebDriver(new URL(URL), caps);

			}

			else if (Storage.OSType.equalsIgnoreCase("WINDOWS") && Storage.Browser_Name.equalsIgnoreCase("Chrome")
					&& Storage.AutomationToolType.equalsIgnoreCase("In-house")) {

				// URL cl= ClassLoader.getSystemResource("chromedriver.exe");
				// //Properties p=System.getProperties();
				//
				// File file=new
				// File(System.getProperty("java.io.tmpdir")+"\\chromedriver.exe");
				//
				// if(!file.exists()) {
				// FileUtils.copyFile(new File(cl.getFile()),file);
				//
				// }

				System.setProperty("webdriver.chrome.driver", "C:\\Automation\\WebDrivers\\chromedriver.exe");
				//
				// //DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				//// ChromeOptions options = new ChromeOptions();
				//// options.addArguments("--disable-extensions");
				//// options.addArguments("test-type");
				//// options.addArguments("chrome.exe","C:\\Program Files
				// (x86)\\Google\\Chrome\\Application");
				// //capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				// //WebDriver driver = new ChromeDriver(options);
				ChromeOptions options = new ChromeOptions();

				// options.addArguments("user-data-dir=C:\\Users\\TFSTest\\AppData\\Local\\Google\\Chrome\\User
				// Data\\Profile 1");
				// options.setProxy(null);
				if (Storage.TC_Name.toUpperCase().contains("SALESFORCE")) {

					options.addArguments(
							"user-data-dir=C:\\Users\\TFSTest\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 1");
					options.setProxy(null);

					if (Storage.isGridExecution.equalsIgnoreCase("true")) {
						DesiredCapabilities cap = DesiredCapabilities.chrome();

						cap.setCapability("user-data-dir",
								"C:\\\\Users\\\\TFSTest\\\\AppData\\\\Local\\\\Google\\\\Chrome\\\\User Data\\\\Profile 1");
						// options1.setProxy(null);

						EntryPoint.driver = new RemoteWebDriver(new URL("http://" + Storage.agentName + ":5566/wd/hub"),
								cap);

						System.out.println("GRID Execution");
					} else {

						System.out.println("Not GRID Execution");
						EntryPoint.driver = new ChromeDriver(options);
					}

				}

				else {

					if (Storage.isGridExecution.equalsIgnoreCase("true")) {
						DesiredCapabilities cap = DesiredCapabilities.chrome();

						EntryPoint.driver = new RemoteWebDriver(new URL("http://" + Storage.agentName + ":5566/wd/hub"),
								cap);

					} else {

						EntryPoint.driver = new ChromeDriver(options);
					}

					// String USERNAME = "mktestoffshore1";
					// String AUTOMATE_KEY = "9159GWvxibLMijCeBxSV";
					// String URL = "http://" + USERNAME + ":" + AUTOMATE_KEY +
					// "@hub-cloud.browserstack.com/wd/hub";
					//
					// DesiredCapabilities caps = new DesiredCapabilities();
					// caps.setCapability("browser", "Chrome");
					// caps.setCapability("browser_version", "66.0");
					// caps.setCapability("os", "Windows");
					// caps.setCapability("os_version", "7");
					// caps.setCapability("resolution", "1366x768");
					//
					// EntryPoint.driver = new RemoteWebDriver(new URL(URL), caps);

				}
			}

			// EntryPoint.driver=new ChromeDriver(options);

			// EntryPoint.driver.get("http://www.google.com");

			// EntryPoint.driver.manage().addCookie(new Cookie("79eb100099b9a8bf",
			// "3:false:.salesforce.com"));
			// EntryPoint.driver.manage().addCookie(new
			// Cookie("AMCVS_8D6C67C25245AF020A490D4C%40AdobeOrg", "1"));
			// EntryPoint.driver.manage().addCookie(new
			// Cookie("AMCV_8D6C67C25245AF020A490D4C%40AdobeOrg",
			// "-1891778711%7CMCIDTS%7C17687%7CMCMID%7C69288529483741295244353263672581630841%7CMCAAMLH-1528703997%7C7%7CMCAAMB-1528703997%7CRKhpRz8krg2tLO6pguXWp5olkAcUniQYPHaMWWgdJ3xzPWQmdj0y%7CMCOPTOUT-1528106397s%7CNONE%7CMCAID%7CNONE%7CMCSYNCSOP%7C411-17694%7CvVersion%7C2.4.0"));
			// EntryPoint.driver.manage().addCookie(new Cookie("BrowserId",
			// "BiUbfYANTemvG931IFp_jw"));
			// EntryPoint.driver.manage().addCookie(new Cookie("QCQQ", "qkh0gvX77z4"));
			// EntryPoint.driver.manage().addCookie(new Cookie("autocomplete", "1"));
			// EntryPoint.driver.manage().addCookie(new Cookie("com.salesforce.LocaleInfo",
			// "us"));
			// EntryPoint.driver.manage().addCookie(new Cookie("login",
			// "Y2hhbmRyYXNla2hhci5tZWxhbUBsaXZlLmNvbQ=="));
			// EntryPoint.driver.manage().addCookie(new Cookie("oid",
			// "00D7F000007E5Ro","ap5.salesforce.com","/",new
			// Date("2020-06-03T08:06:03.597Z")));
			// EntryPoint.driver.manage().addCookie(new Cookie("oinfo",
			// "c3RhdHVzPUZSRUUmdHlwZT0zJm9pZD0wMEQ3RjAwMDAwN0U1Um8="));
			// EntryPoint.driver.manage().addCookie(new Cookie("rememberUn", "true"));
			// EntryPoint.driver.manage().addCookie(new Cookie("session", "1528099564070"));
			// EntryPoint.driver.manage().addCookie(new Cookie("sfdc_lv2",
			// "KxG83HhMumseTEz+2VPO9+ldt0v+aR2xpvlg1m7QO9MlZHcD6OQ7rtU5dGAD6ieaY="));
			// EntryPoint.driver.manage().addCookie(new Cookie("webact",
			// "%7B%22l_vdays%22%3A-1%2C%22l_visit%22%3A0%2C%22session%22%3A1528099577787%2C%22l_search%22%3A%22%22%2C%22l_dtype%22%3A%22%22%2C%22l_page%22%3A%22SFDC%3Aus%3Alogin%3Adeveloper%22%2C%22counter%22%3A0%2C%22pv%22%3A1%2C%22f_visit%22%3A1528099577787%2C%22developer%22%3A1528099577787%2C%22seg%22%3A%22non-customer%3Aus%22%7D"));

			// EntryPoint.driver.manage().addCookie(new
			// Cookie("sid_Client","F0000041BOvF000007E5Ro"));
			// EntryPoint.driver.manage().addCookie(new
			// Cookie("force-stream","2462308874.38175.0000"));
			// EntryPoint.driver.manage().addCookie(new Cookie("inst","APP_7F"));
			// EntryPoint.driver.manage().addCookie(new
			// Cookie("sfdc-stream","2462308874.38175.0000"));
			// EntryPoint.driver.manage().addCookie(new
			// Cookie("force-proxy-stream","450025994.38175.0000"));
			// EntryPoint.driver.manage().addCookie(new Cookie("clientSrc","208.86.48.93"));

			// }

			else if (Storage.OSType.toUpperCase().equals("MAC") && Storage.Browser_Name.equalsIgnoreCase("Safari")
					&& Storage.AutomationToolType.equalsIgnoreCase("In-house")) {
				// DesiredCapabilities d=new DesiredCapabilities();

				SafariOptions safarioptions = new SafariOptions();
				safarioptions.setCapability("cleanSession", false);
				EntryPoint.driver = new SafariDriver(safarioptions);

			}

			else if (Storage.OSType.toUpperCase().equals("ANDROID") && Storage.Browser_Name.equalsIgnoreCase("Chrome")
					&& Storage.AutomationToolType.equalsIgnoreCase("In-house")) {

				// start_appium();
				// System.out.println("---- Starting appium server ----");
				// startServer();
				// run_adb("adb shell pm clear org.hola");

				// run_adb(" taskkill /F /IM node.exe");
				// run_adb("taskkill /f /im cmd.exe");

				// start_appium();

				// run_adb("adb shell pm clear com.hul.humarashop.mobile");
				// run_adb("appium");

				// Created object of DesiredCapabilities class.

				EntryPoint.capabilities = new DesiredCapabilities();

				// Set android deviceName desired capability. Set your device name.
				EntryPoint.capabilities.setCapability("deviceName", "3300729aa7662251");

				// Set BROWSER_NAME desired capability. It's Android in our case here.
				// EntryPoint.capabilities.setCapability(CapabilityType.BROWSER_NAME,
				// "Android");
				EntryPoint.capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
				// Set android VERSION desired capability. Set your mobile device's OS version.
				EntryPoint.capabilities.setCapability(CapabilityType.VERSION, "6.0.1");

				// Set android platformName desired capability. It's Android in our case here.
				EntryPoint.capabilities.setCapability("platformName", "Android");

				// EntryPoint.capabilities.setCapability("autoAcceptAlerts", true);
				// EntryPoint.capabilities.setCapability("autoDismissAlerts", true);

				// Set your application's appPackage if you are using any other app.
				// capabilities.setCapability("appPackage", "com.android.chrome");
				// EntryPoint.capabilities.setCapability("appPackage", "org.hola");

				// Set your application's appPackage if you are using any other app.
				// capabilities.setCapability("appActivity",
				// "org.chromium.chrome.browse.ChromeTabbedActivity");
				// EntryPoint.capabilities.setCapability("appPackage",
				// "com.hul.humarashop.mobile");
				// EntryPoint.capabilities.setCapability("appActivity",
				// "com.hul.humarashop.mobile.SplashActivity");//com.hul.humarashop.mobile.SplashActivity
				// org.chromium.chrome.browse.ChromeTabbedActivity
				//
				// AppiumDriver<WebElement> dr=new AppiumDriver<WebElement>(new
				// URL("http://127.0.0.1:4723/wd/hub"),EntryPoint.capabilities);
				// Set<String>contexts= dr.getContextHandles();
				// for(String s:contexts){
				// System.out.println(s);
				// }

				// start_driver(60000);

				EntryPoint.AppDriver = new AppiumDriver<WebElement>(new URL("http://172.17.224.199:4726/wd/hub"),
						EntryPoint.capabilities);
				// EntryPoint.driver=new RemoteWebDriver(new
				// URL("http://172.17.224.199:4726/wd/hub"),EntryPoint.capabilities);
				// EntryPoint.driver=new AppiumDriver(new
				// URL("http://127.0.0.1:4723/wd/hub"),EntryPoint.capabilities);

				EntryPoint.AppDriver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

				//
				// int fromX=EntryPoint.AppDriver.findElement(By.xpath("//*[@text='Help &
				// Support']")).getLocation().getX();
				// int fromY=EntryPoint.AppDriver.findElement(By.xpath("//*[@text='Help &
				// Support']")).getLocation().getY();
				//
				// int toX=EntryPoint.AppDriver.findElement(By.xpath("//*[@text='SNACKS &
				// PANTRY']")).getLocation().getX();
				// int toY=EntryPoint.AppDriver.findElement(By.xpath("//*[@text='SNACKS &
				// PANTRY']")).getLocation().getY();
				//
				//
				// TouchAction action = new
				// TouchAction(EntryPoint.AppDriver).longPress(fromX,fromY).moveTo(toX,toY).release();
				// action.perform();
				// EntryPoint.AppDriver.findElement(By.id("com.hul.humarashop.mobile:id/textId4")).click();
				//
				// EntryPoint.AppDriver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
				// EntryPoint.AppDriver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
				// EntryPoint.AppDriver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
				// EntryPoint.AppDriver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
				// EntryPoint.AppDriver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
				//
				// EntryPoint.AppDriver.findElement(By.xpath("//*[@content-desc='Open navigation
				// drawer']")).click();
				//
				// //TouchAction touchAction = new TouchAction(EntryPoint.AppDriver);
				// // touchAction.
				//
				//
				// //touchAction.moveTo(0, 500).perform();
				//
				//
				//
				// //com.hul.humarashop.mobile:id/expandableText
				//
				// //com.hul.humarashop.mobile:id/search_prod_name
				//
				// //com.hul.humarashop.mobile:id/prod_webprice
				//
				// //com.hul.humarashop.mobile:id/button_add_to_cart_search
				//
				// //com.hul.humarashop.mobile:id/productdetails_prd_title
				// //com.hul.humarashop.mobile:id/productdetails_price
				// //com.hul.humarashop.mobile:id/productdetials_addtocart
				//
				//// com.hul.humarashop.mobile:id/auto_city
				// //com.hul.humarashop.mobile:id/toolbar
				// //com.hul.humarashop.mobile:id/parentview
				// System.out.println(EntryPoint.AppDriver.getPageSource());
				// EntryPoint.AppDriver.findElement(By.xpath("//android.widget.TextView[@text='Change
				// Store']")).click();
				// EntryPoint.AppDriver.findElement(By.id("com.hul.humarashop.mobile:id/auto_city")).sendKeys("110059");
				//
				// EntryPoint.AppDriver.findElement(By.id("com.hul.humarashop.mobile:id/toolbar")).click();
				//
				// EntryPoint.AppDriver.findElement(By.id("com.hul.humarashop.mobile:id/parentview")).click();
				//
				// EntryPoint.AppDriver.findElement(By.xpath("//*[@content-desc='Open navigation
				// drawer']")).click();
				//
				// EntryPoint.AppDriver.findElement(By.id("com.hul.humarashop.mobile:id/expandableText")).click();
				//
				//
				// String
				// pName=EntryPoint.AppDriver.findElement(By.id("com.hul.humarashop.mobile:id/search_prod_name")).getText();
				//
				// String
				// price=EntryPoint.AppDriver.findElement(By.id("com.hul.humarashop.mobile:id/prod_webprice")).getText();
				//
				// EntryPoint.AppDriver.findElement(By.id("com.hul.humarashop.mobile:id/button_add_to_cart_search")).click();
				//
				//
				// String
				// qty=EntryPoint.AppDriver.findElement(By.id("com.hul.humarashop.mobile:id/count_value")).getText();
				// EntryPoint.AppDriver.findElement(By.id("com.hul.humarashop.mobile:id/count_value")).click();
				//
				// //com.hul.humarashop.mobile:id/product_name
				// String
				// pname1=EntryPoint.AppDriver.findElement(By.id("com.hul.humarashop.mobile:id/product_name")).getText();
				//
				// String
				// pprice=EntryPoint.AppDriver.findElement(By.id("com.hul.humarashop.mobile:id/price")).getText();
				//
				// EntryPoint.AppDriver.findElement(By.id("com.hul.humarashop.mobile:id/check_out_btn")).click();
				//
				// EntryPoint.AppDriver.manage().timeouts().implicitlyWait(20,
				// TimeUnit.SECONDS);

			}

			else if (Storage.OSType.toUpperCase().equals("IOS") && Storage.Browser_Name.equalsIgnoreCase("Safari")
					&& Storage.AutomationToolType.equalsIgnoreCase("In-house")) {

				// start_appium();
				// System.out.println("---- Starting appium server ----");
				// startServer();
				// run_adb("adb shell pm clear org.hola");
				// Created object of DesiredCapabilities class.

				try {

					start_appium();

					EntryPoint.capabilities = new DesiredCapabilities();

					// capabilities.setCapability(CapabilityType.BROWSER_NAME, "Safari");
					EntryPoint.capabilities.setCapability("automationName", "XCUITest");
					// capabilities.SetCapability(MobileCapabilityType.DeviceName, "MaryKayUser's
					// iPad");
					EntryPoint.capabilities.setCapability("version", "10.2.1");
					EntryPoint.capabilities.setCapability("platformName", "iOS");

					EntryPoint.capabilities.setCapability("udid", "728f93e73080b7d234547c978d56a7fae83d0720");
					EntryPoint.capabilities.setCapability("app", "/Users/mkuser/Desktop/Automation/NetGear.ipa");
					// EntryPoint.capabilities.SetCapability("bundleId", "com.apple.mobilesafari");
					EntryPoint.capabilities.setCapability("bundleId", "com.csscorp.NetGear");
					EntryPoint.capabilities.setCapability("wdaLocalPort", "8200");
					EntryPoint.capabilities.setCapability("newCommandTimeout", "120000");
					EntryPoint.capabilities.setCapability("clearSystemFiles", "true");
					EntryPoint.capabilities.setCapability("deviceName", "MaryKayUser's iPad");

					// start_driver(60000);

				} catch (Exception ex) {

				}

			}

			//// ResultLogger.log(Storage.Browser_Name +"Browser Launched Successfully",
			//// ISSTEPACTION.False, RESULT.PASS);

			if (!Storage.Browser_Name.toUpperCase().equals("APP".toUpperCase())
					&& Storage.AutomationToolType.equalsIgnoreCase("In-house")) {
				EntryPoint.driver.manage().window().maximize();
				EntryPoint.driver.manage().timeouts().implicitlyWait(30, TimeUnit.MINUTES);
				EntryPoint.driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.MINUTES);
				EntryPoint.driver.manage().timeouts().setScriptTimeout(10, TimeUnit.MINUTES);

				// EntryPoint.driver.manage().deleteAllCookies();
			}

		} catch (Exception ex) {
			System.out.println("Error message" + ex);
			//// ResultLogger.log(e.getMessage(),ISSTEPACTION.False,RESULT.EXCEPTION);
		}

	}

	// public static void start_driver(int time)
	// {
	// long t= System.currentTimeMillis();
	// long end = t+time;
	// while(System.currentTimeMillis() < end)
	// {
	// if(Storage.OSType.toUpperCase().equals("ANDROID"))
	// {
	// try
	// {
	// EntryPoint.AppDriver = new AndroidDriver(new
	// URL("http://127.0.0.1:4723/wd/hub"),EntryPoint.capabilities);
	//
	// return;
	// }
	// catch (UnreachableBrowserException e)
	// {
	//
	// }
	// catch (Exception e)
	// {
	// break;
	// }
	// }
	// else if(Storage.OSType.toUpperCase().equals("IOS"))
	// {
	// try
	// {
	// //EntryPoint.driver = new IOSDriver<WebElement>(new
	// URL("http://10.8.35.21:4723/wd/hub"), EntryPoint.capabilities);
	//
	// return;
	// }
	// catch (UnreachableBrowserException e)
	// {
	//
	// }
	// catch (Exception e)
	// {
	// break;
	// }
	// }
	//
	// // do something
	// // pause to avoid churning
	// }
	// }
	public static void verifytitle() {
		try {
			String title = EntryPoint.driver.getTitle();

			Storage.sActualValue = Storage.getHashTable(Storage.sValue);

			if (title.equals(Storage.sActualValue)) {
				ResultLogger.log("Titles are  Matched.", ISSTEPACTION.True, RESULT.PASS);
			} else {
				ResultLogger.log("Titles are mismatched.", ISSTEPACTION.True, RESULT.WARNING);
			}

		} catch (Exception ex) {
			//// ResultLogger.log("Exception occured while verifying the Title of the page.
			//// Exception: "+ex.getStackTrace(), ISSTEPACTION.True, RESULT.EXCEPTION);
		}
	}

	public static void open() {

		try {
			if (!(Storage.OSType.toUpperCase().equals("IOS") && Storage.Browser_Name.equalsIgnoreCase("Safari")
					&& Storage.AutomationToolType.equalsIgnoreCase("In-house"))
					|| (!(Storage.OSType.toUpperCase().equals("Android")
							&& Storage.Browser_Name.equalsIgnoreCase("chrome")
							&& Storage.AutomationToolType.equalsIgnoreCase("In-house")))) {
				EntryPoint.driver.navigate().to(Storage.sValue);

				ResultLogger.log("Navigated the " + Storage.sValue + " Successfully. ", ISSTEPACTION.True, RESULT.PASS);

				waitForPageLoad();
			}

		} catch (Exception e) {

			ResultLogger.log(e.getMessage(), ISSTEPACTION.True, RESULT.EXCEPTION);
		}

	}

	public static void waitForPageLoad() {
		try {
			if (!(Storage.OSType.toUpperCase().equals("IOS") && Storage.Browser_Name.equalsIgnoreCase("Safari")
					&& Storage.AutomationToolType.equalsIgnoreCase("In-house"))
					|| (!(Storage.OSType.toUpperCase().equals("Android")
							&& Storage.Browser_Name.equalsIgnoreCase("chrome")
							&& Storage.AutomationToolType.equalsIgnoreCase("In-house")))) {
				String pageLoadState = ((JavascriptExecutor) EntryPoint.driver).executeScript(
						"if (document != undefined && document.readyState) { return document.readyState;} else { return undefined;}")
						.toString();

				while (true) {
					if (pageLoadState.toUpperCase().equals("COMPLETE")
							|| pageLoadState.toUpperCase().equals("LOADED")) {
						// ResultLogger.log("Page Load State:
						// "+pageLoadState,ISSTEPACTION.True,RESULT.PASS);
						System.out.println("Page Load State: " + pageLoadState);

						break;
					}
					pageLoadState = ((JavascriptExecutor) EntryPoint.driver).executeScript(
							"if (document != undefined && document.readyState) { return document.readyState;} else { return undefined;}")
							.toString();

				}
			}
		} catch (Exception ex) {

		}

		if (!Storage.Browser_Name.toUpperCase().equals("IE")) {
			untilAngularFinishHttpCalls();
		}
		// untilAngularFinishHttpCalls();

	}

	public static void untilAngularFinishHttpCalls() {

		try {
			if (!(Storage.OSType.toUpperCase().equals("IOS") && Storage.Browser_Name.equalsIgnoreCase("Safari")
					&& Storage.AutomationToolType.equalsIgnoreCase("In-house"))
					|| (!(Storage.OSType.toUpperCase().equals("Android")
							&& Storage.Browser_Name.equalsIgnoreCase("chrome")
							&& Storage.AutomationToolType.equalsIgnoreCase("In-house")))) {
				final String javaScriptToLoadAngular = "var injector = window.angular.element('body').injector();"
						+ "var $http = injector.get('$http');" + "return ($http.pendingRequests.length === 0)";

				ExpectedCondition<Boolean> pendingHttpCallsCondition = new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver driver) {
						return ((JavascriptExecutor) driver).executeScript(javaScriptToLoadAngular).equals(true);
					}
				};
				WebDriverWait wait = new WebDriverWait(EntryPoint.driver, 2); // timeout = 20 secs
				wait.until(pendingHttpCallsCondition);

			}
		} catch (Exception ex) {
			// System.out.println(ex.getMessage());
		}
	}

	public static void start_appium() throws InterruptedException {
		if (Storage.OSType.toUpperCase().equals("Android".toUpperCase())) {
			try {
				run_adb("cmd /c start  C:\\Automation\\LocalExecution\\StartAppium.bat");
				Thread.sleep(30000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (Storage.OSType.toUpperCase().equals("IOS".toUpperCase())) {
			run_adb("cmd /c start  C:\\Automation\\LocalExecution\\PuttyStarter.bat");
			Thread.sleep(30000);
		}

	}

	public static void run_adb(String commands) {
		// Runtime runtime = Runtime.getRuntime();
		try {
			EntryPoint.process = Runtime.getRuntime().exec("cmd /c" + commands);
			EntryPoint.process.waitFor();
		}

		catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static void DeleteVisibleCookies() {
		try {
			// EntryPoint.driver.manage().deleteAllCookies();

			//// ResultLogger.log("Deleted cookies
			//// Successfully.",ISSTEPACTION.True,RESULT.PASS);
		} catch (Exception ex) {
			//// ResultLogger.log("Exception occured while deleting the
			//// cookies.",ISSTEPACTION.True,RESULT.EXCEPTION);
		}
	}

}
