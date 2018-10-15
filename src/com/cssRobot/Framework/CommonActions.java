package com.cssRobot.Framework;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cssRobot.Execution.Browser;
import com.cssRobot.Execution.ObjectHandler;
import com.cssRobot.ResultLogger.ResultLogger;
import com.cssRobot.ResultLogger.ResultLogger.ISSTEPACTION;
import com.cssRobot.ResultLogger.ResultLogger.RESULT;
import com.cssRobot.Start.EntryPoint;
import com.cssRobot.Storage.Storage;
import io.appium.java_client.TouchAction;

public class CommonActions {
	
	public static void click()
	{
		try
		{
			if (!(Storage.OSType.toUpperCase().equals("IOS") && Storage.Browser_Name.equalsIgnoreCase("Safari") && Storage.AutomationToolType.equalsIgnoreCase("In-house")) ||(!(Storage.OSType.toUpperCase().equals("Android") && Storage.Browser_Name.equalsIgnoreCase("chrome") && Storage.AutomationToolType.equalsIgnoreCase("In-house")))) 
			{
			ObjectHandler.GetWebElement();
			
			Storage.webElement.click();
			}

			ResultLogger.log("Clicked element", ISSTEPACTION.True, RESULT.PASS);
		}
		catch (Exception e) 
		{
			ResultLogger.log("User Message: Unable to click the element. System Exception message: "+e.getMessage(), ISSTEPACTION.True,RESULT.EXCEPTION);
		}
	}
	public static void CheckBoxClick()

	{

	try

	{

	//ObjectHandler.GetWebElement();

	EntryPoint.driver.findElement(By.cssSelector("span.name.ng-isolate-scope[did-translate='legal.GTOU_create']")).click();
	
	//((JavascriptExecutor)EntryPoint.driver).executeScript("arguments[0].checked = true;", Storage.webElement);


	//((JavascriptExecutor)EntryPoint.driver).executeScript("var evt = document.createEvent('MouseEvents');" + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" + "arguments[0].dispatchEvent(evt);", Storage.webElement);


	ResultLogger.log("Clicked element successfully.", ISSTEPACTION.True, RESULT.PASS);

	//Thread.sleep(2000);

	}

	catch (Exception e) 

	{

	ResultLogger.log("User Message: Unable to click the element. System Exception message: "+e.getMessage(), ISSTEPACTION.True,RESULT.EXCEPTION);

	}

	}

	public static void switchToIframe() {
		
		try {
			
			if (!(Storage.OSType.toUpperCase().equals("IOS") && Storage.Browser_Name.equalsIgnoreCase("Safari") && Storage.AutomationToolType.equalsIgnoreCase("In-house")) ||(!(Storage.OSType.toUpperCase().equals("Android") && Storage.Browser_Name.equalsIgnoreCase("chrome") && Storage.AutomationToolType.equalsIgnoreCase("In-house")))) 
			{
			ObjectHandler.GetWebElement();
			
			EntryPoint.driver.switchTo().frame(Storage.webElement);
			}
			
			ResultLogger.log("Switched to IFrame successfully", ISSTEPACTION.True, RESULT.PASS);
		}
		catch(Exception ex) {
			ResultLogger.log("Exception occured while switch to iframe. Sys Message:"+ex.getMessage(), ISSTEPACTION.True, RESULT.EXCEPTION);
		}
	}
	
	public static void pressEnterKey(){
		try{
			
			//EntryPoint.driver.switchTo().activeElement();
			Actions act=new Actions(EntryPoint.driver);
			act.sendKeys(Storage.webElement, Keys.ENTER).build().perform();
			
			
		}
		catch(Exception ex){
		
			System.out.println();
		}
	}
	public static void WaitInSecond()
	{
		try
		{
			int WaitInSecondValue=Integer.parseInt(Storage.sValue);
		
			Thread.sleep(WaitInSecondValue*1000);
			
			ResultLogger.log("WaitInSecond", ISSTEPACTION.True, RESULT.PASS);
		}
		catch (Exception e) 
		{
			ResultLogger.log("User Message: Unable to waitinSecond"+e.getMessage(), ISSTEPACTION.True,RESULT.EXCEPTION);
		}
	}
	public static void scroll_to_view()
	{
		//EntryPoint.driver.scrollTo(Storage.sTarget);
	}
	public static void no_exection()
	{
		try
		{
			ResultLogger.log("no exection ", ResultLogger.ISSTEPACTION.True, ResultLogger.RESULT.PASS);
		}
		catch (Exception e)
		{
			ResultLogger.log("Exception occured while entering the text/value. System Message: " + e.getMessage(), ResultLogger.ISSTEPACTION.True, ResultLogger.RESULT.EXCEPTION);
		}
	}
	public static void search_record() throws InterruptedException
	{
		/*run_adb("adb shell input text 'Show'");
		Thread.sleep(4000);
		run_adb("adb shell input text '%sme'");
		Thread.sleep(4000);
		run_adb("adb shell input text '%sthe'");
		Thread.sleep(4000);
		run_adb("adb shell input text '%smeaning'");
		Thread.sleep(4000);*/

		try
		{
			Storage.visibility_wait = new WebDriverWait(EntryPoint.driver, 5000L);

			run_adb("adb shell input text 'Show'");
			Storage.visibility_wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Searching?']")));
			run_adb("adb shell input text '%sme'");
			Storage.visibility_wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Searching?']")));
			run_adb("adb shell input text '%sthe'");
			Storage.visibility_wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Searching?']")));
			run_adb("adb shell input text '%smeaning'");
			Storage.visibility_wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Searching?']")));

			ResultLogger.log("searched record successfully", ResultLogger.ISSTEPACTION.True, ResultLogger.RESULT.PASS);
		}
		catch (Exception e)
		{
			ResultLogger.log("search Record not found: " + e.getMessage(), ResultLogger.ISSTEPACTION.True, ResultLogger.RESULT.EXCEPTION);
		}
	}
	public static void End()
	{
		try
		{
			EntryPoint.driver.quit();
			
			ResultLogger.log("aplication closed", ResultLogger.ISSTEPACTION.True, ResultLogger.RESULT.PASS);
		}
		catch (Exception e)
		{
			ResultLogger.log("User Message: Unable to click the element. System Exception message: " + e.getMessage(), ResultLogger.ISSTEPACTION.True, ResultLogger.RESULT.EXCEPTION);
		}
	}
	public static void click_by_javascript()
	{
		try 
		{
			ObjectHandler.GetWebElement();
			EntryPoint.js = (JavascriptExecutor) EntryPoint.driver;
			EntryPoint.p = Storage.webElement.getLocation();
			EntryPoint.size = Storage.webElement.getSize();
			EntryPoint.x = EntryPoint.p.getX() + EntryPoint.size.getWidth() / 2.0;
			EntryPoint.y = EntryPoint.p.getY() + EntryPoint.size.getHeight() / 2.0;
			HashMap<String , Double> point = new HashMap<String , Double>();
			point.put("x" , EntryPoint.x);
			point.put("y" , EntryPoint.y);
			EntryPoint.js.executeScript("mobile: tap", point);
			Thread.sleep(2000);
		}
		catch (InterruptedException e)
		{
			
			e.printStackTrace();
		}
	}
	public static void tap_NASPTER_setting()
	{
		ObjectHandler.GetWebElements();
		int count1=Storage.webElements.size();
		if(count1!=0)
		{
			tap();
		}
	}
	public static void tap()
	{
		//int starty = (int) (EntryPoint.size.height * 0.80);
		//int startx = EntryPoint.size.width / 2;
		//TouchAction a2 = new TouchAction(EntryPoint.driver);
		//a2.tap (startx, starty).perform();	
	}
	public static void tab_element() throws InterruptedException
	{
		ObjectHandler.GetWebElement();
		//TouchAction a2 = new TouchAction(EntryPoint.driver);
		//a2.tap(Storage.webElement).perform();
		Thread.sleep(4000);
	}
	public static void search_text_entry()
	{
		try
		{
			
			ObjectHandler.GetWebElements();
			int count1 = Storage.webElements.size();
			if (count1 == 0) {
				click();
			}
			ResultLogger.log("search element successfully", ResultLogger.ISSTEPACTION.True, ResultLogger.RESULT.PASS);
		}
		catch (Exception e)
		{
			ResultLogger.log("User Message: Unable to click the element. System Exception message: " + e.getMessage(), ResultLogger.ISSTEPACTION.True, ResultLogger.RESULT.EXCEPTION);
		}
	}
	
	public static  void TypeWithKey(String keyvalue)
	{
		Actions action=null;
		if (!(Storage.OSType.toUpperCase().equals("IOS") && Storage.Browser_Name.equalsIgnoreCase("Safari") && Storage.AutomationToolType.equalsIgnoreCase("In-house")) ||(!(Storage.OSType.toUpperCase().equals("Android") && Storage.Browser_Name.equalsIgnoreCase("chrome") && Storage.AutomationToolType.equalsIgnoreCase("In-house")))) 
		{
			action = new Actions(EntryPoint.driver);
		}
		
		keyvalue = keyvalue.trim();

	if(keyvalue.equals("ENTER"))
	{
	action.sendKeys(Keys.ENTER).build().perform();
	}
	else if(keyvalue.equals("TAB"))
	{
	action.sendKeys(Keys.TAB).build().perform();
	}

	else
	{

	int stringlength = keyvalue.length();

	int iteration=0;

	while(iteration < stringlength)
	{

	char value = keyvalue.charAt(iteration);

	int newvalue = value;

	switch(newvalue) { 
	case '0': action.sendKeys(Keys.NUMPAD0).build().perform(); break;  
	    case '1': action.sendKeys(Keys.NUMPAD1).build().perform();break;   
	    case '2':action.sendKeys(Keys.NUMPAD2).build().perform();break;
	       
	    case '3':action.sendKeys(Keys.NUMPAD3).build().perform();break;
	       
	    case '4':action.sendKeys(Keys.NUMPAD4).build().perform();break;
	    case '5':action.sendKeys(Keys.NUMPAD5).build().perform();break;
	    case '6':action.sendKeys(Keys.NUMPAD6).build().perform();break;
	    case '7': action.sendKeys(Keys.NUMPAD7).build().perform();break;
	    case '8':action.sendKeys(Keys.NUMPAD8).build().perform();break;
	    case '9': action.sendKeys(Keys.NUMPAD9).build().perform();break;
	    default:    
	    
	 
	}
	iteration++;
	}

	}

	}
	public static void hideKeyboard()
	{
		try 
		{
			//EntryPoint.driver.hideKeyboard();
			
			ResultLogger.log("Hide keyboard successfully", ResultLogger.ISSTEPACTION.True, ResultLogger.RESULT.PASS);
		}
		catch(Exception e)
		{
			ResultLogger.log("User Message: Unable to hide the keyboard. System Exception message: " + e.getMessage(), ResultLogger.ISSTEPACTION.True, ResultLogger.RESULT.EXCEPTION);
		}
	}
	
	public static void run_adb(String cmd)
	{
		//Runtime runtime = Runtime.getRuntime();
		try 
		{
			EntryPoint.process = Runtime.getRuntime().exec(cmd);
			EntryPoint.process.waitFor();
		}

		catch (Exception e)
		{
			e.printStackTrace();

		}
	}
	public static void run_adb()
	{
		//Runtime runtime = Runtime.getRuntime();
		try 
		{
			EntryPoint.process = Runtime.getRuntime().exec(Storage.sTarget.toLowerCase());
			EntryPoint.process.waitFor();
		}

		catch (Exception e)
		{
			e.printStackTrace();

		}
	}
	public static void LOCATE_NASPTER()
	{
		ObjectHandler.GetWebElements();
		int locatorElementSize =Storage.webElements.size();
		if(locatorElementSize==0)
		{
			swipe();
			swipe();
		}
	}
	public static void swipe()
	{
		try{
			
			String fromTarget=Storage.sTarget.split("\\|")[0];
			String toTarget=Storage.sTarget.split("\\|")[1];
			
			Storage.sTarget=fromTarget;
			
			ObjectHandler.GetWebElement();
			int fromX= Storage.webElement.getLocation().getX();
			int fromY= Storage.webElement.getLocation().getY();
			
			Storage.sTarget=toTarget;
			
			ObjectHandler.GetWebElement();
			int toX= Storage.webElement.getLocation().getX();
			int toY= Storage.webElement.getLocation().getY();
			
			TouchAction action = new TouchAction(EntryPoint.AppDriver).longPress(fromX,fromY).moveTo(toX,toY).release();
			action.perform();
			
		}
		catch(Exception ex){
			
		}
	}
	public static Dimension get_screen_size()
	{
		return(EntryPoint.driver.manage().window().getSize());
	}
	public static int count_element(By xpath)
	{
		ObjectHandler.GetWebElements();
		int locatorElementSize =Storage.webElements.size();
		return locatorElementSize;
	}
	public static void clickAndWait()
	{
		try
		{
			if (!(Storage.OSType.toUpperCase().equals("IOS") && Storage.Browser_Name.equalsIgnoreCase("Safari") && Storage.AutomationToolType.equalsIgnoreCase("In-house")) ||(!(Storage.OSType.toUpperCase().equals("Android") && Storage.Browser_Name.equalsIgnoreCase("chrome") && Storage.AutomationToolType.equalsIgnoreCase("In-house")))) 
			{
				ObjectHandler.GetWebElement();

			((JavascriptExecutor)EntryPoint.driver).executeScript("var evt = document.createEvent('MouseEvents');" + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);" + "arguments[0].dispatchEvent(evt);", Storage.webElement);

			}
			ResultLogger.log("Clicked element successfully.", ISSTEPACTION.True, RESULT.PASS);
			//Thread.sleep(2000);
		}
		catch (Exception e) 
		{
			ResultLogger.log("User Message: Unable to click the element. System Exception message: "+e.getMessage(), ISSTEPACTION.True,RESULT.EXCEPTION);
		}
	}

	
	public static void RefreshPage()	
	{
		
		Browser.untilAngularFinishHttpCalls();
		Browser.waitForPageLoad();
		
		EntryPoint.driver.navigate().refresh();
		
		Browser.waitForPageLoad();
		Browser.untilAngularFinishHttpCalls();
	
	}
	public static void storeText()
	{
		try
		{
			ObjectHandler.GetWebElement();

			String tempTargetValue = "";

			if (Storage.webElement.getTagName().toUpperCase().equals("INPUT"))
			{
				tempTargetValue = Storage.webElement.getAttribute("value").trim();

			}
			else
			{
				tempTargetValue = Storage.webElement.getText().trim();
			}

			Storage.sValue = Storage.sValue.trim();

			Storage.insertHashTable(Storage.sValue, tempTargetValue);

			ResultLogger.log("Stored the text/value into storage location",ISSTEPACTION.True, RESULT.PASS);

		}
		catch(Exception ex)
		{
			ResultLogger.log("Exception occured. System Exception Message: "+ex.getMessage(), ISSTEPACTION.True,RESULT.EXCEPTION);
		}
	}

	public static void verifyText()
	{
		try
		{
			if (!(Storage.OSType.toUpperCase().equals("IOS") && Storage.Browser_Name.equalsIgnoreCase("Safari") && Storage.AutomationToolType.equalsIgnoreCase("In-house")) ||(!(Storage.OSType.toUpperCase().equals("Android") && Storage.Browser_Name.equalsIgnoreCase("chrome") && Storage.AutomationToolType.equalsIgnoreCase("In-house")))) 
			{
			ObjectHandler.GetWebElement();
			}
			
			Storage.sActualValue = Storage.webElement.getText();

			String tempKeySplitter = Storage.getHashTable(Storage.sValue);

			ResultLogger.log("Expected Value : " + tempKeySplitter, ISSTEPACTION.False, RESULT.PASS);
			
			//System.out.println(EntryPoint.driver.getPageSource());

			// ResultLogger.log("Actual Value : " + Storage.sActualValue, ISSTEPACTION.False, RESULT.PASS);

			if ((Storage.sActualValue.toLowerCase().contains(tempKeySplitter.toLowerCase()) || tempKeySplitter.toLowerCase().contains(Storage.sActualValue.toLowerCase())))
			{
				 ResultLogger.log("Actual and Expected texts are equal.",ISSTEPACTION.True,RESULT.PASS);
			}
			else
			{
				ResultLogger.log("Actual and Expected texts are not equal.",ISSTEPACTION.True,RESULT.WARNING);
			}

		}
		catch (Exception e) 
		{
			ResultLogger.log("Exception occured. System message: "+e.getMessage(), ISSTEPACTION.True, RESULT.EXCEPTION);
		}

	}

	public static void verifyElementPresent()
	{
		try
		{
			ObjectHandler.splitTarget();

			if (IsElementPresent())
			{
				ResultLogger.log("Element is Present.", ISSTEPACTION.True,RESULT.PASS);
			}
			else
			{
				ResultLogger.log("Element is not Present.", ISSTEPACTION.True,RESULT.WARNING);
			}
		}
		catch (Exception ex)
		{
			ResultLogger.log("Exception occured at verifyElementPresent ", ISSTEPACTION.True,RESULT.EXCEPTION);
		}

	}

	public static boolean IsElementPresent()
	{
		boolean isPresent = false;

		try
		{
			
        	if (!(Storage.OSType.toUpperCase().equals("IOS") && Storage.Browser_Name.equalsIgnoreCase("Safari") && Storage.AutomationToolType.equalsIgnoreCase("In-house")) ||(!(Storage.OSType.toUpperCase().equals("Android") && Storage.Browser_Name.equalsIgnoreCase("chrome") && Storage.AutomationToolType.equalsIgnoreCase("In-house")))) 
			{
        		EntryPoint.driver.findElement(Storage.by);
			}
			
        	else
			{
        		EntryPoint.AppDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				//EntryPoint.AppDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				EntryPoint.AppDriver.findElement(Storage.by);		
			}

			isPresent = true;
		}
		catch(Exception ex)
		{
			isPresent =false;
		}

		return isPresent;
	}

	public static void verifyElementNotPresent()
	{
		try
		{

			ObjectHandler.splitTarget();

			if (!IsElementPresent())
			{
				ResultLogger.log("Element is not Present.", ISSTEPACTION.True,RESULT.PASS);
			}
			else
			{
				// ResultLogger.log("Element is Present.", ISSTEPACTION.True,RESULT.WARNING);
			}
		}
		catch (Exception ex)
		{
			ResultLogger.log("Exception occured at verifyElementPresent ", ISSTEPACTION.True,RESULT.EXCEPTION);
		}

	}

	public static void storeUniqueEmail()
	{
		try
		{

			String randomEmail = RandomString();

			//Storage.sActualValue = "TestEsuite" + "+" + Storage.Project_Locale + "-" + Storage.Project_Environment + "-" + randomEmail + "@gmail.com";
			Storage.sActualValue = "Test"+ randomEmail + "@gmail.com";

			Storage.insertHashTable(Storage.sValue, Storage.sActualValue);

		}
		catch (Exception ex)
		{
			ResultLogger.log("Unable to Generate Random Email Id",ISSTEPACTION.True,RESULT.EXCEPTION);
		}

	}

	public static void storeUniqueName()
	{
		try
		{

			String randomName = RandomString();

			Storage.sActualValue = Storage.Project_Locale + Storage.Project_Environment + randomName;

			Storage.insertHashTable(Storage.sValue, Storage.sActualValue);

		}
		catch (Exception ex)
		{
			// ResultLogger.log("Unable to Generate Random Name",ISSTEPACTION.True,RESULT.EXCEPTION);
		}

	}

	private static String RandomString() 
	{
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();

		StringBuilder sb = new StringBuilder();

		Random random = new Random();

		for (int i = 0; i < 20; i++) 
		{
			char c = chars[random.nextInt(chars.length)];

			sb.append(c);
		}

		return sb.toString();
	}

	public static void verifyImagePresent()
	{

		try
		{
			ObjectHandler.GetWebElement();

			WebElement imgVerificationObject = Storage.webElement;

			Object result = null;


			result = ((JavascriptExecutor)EntryPoint.driver).executeScript("return arguments[0].complete && " + "typeof arguments[0].naturalWidth != \"undefined\" && " + "arguments[0].naturalWidth > 0", imgVerificationObject);


			Boolean loaded = false;

			if (result  instanceof Boolean) 
			{

				loaded = (Boolean) result;

				if (loaded)
				{
					// ResultLogger.log("Image is fully displayed and present ",ISSTEPACTION.True,RESULT.PASS);
				}

				else
				{
					// ResultLogger.log("Image is not fully loaded or present ",ISSTEPACTION.True,RESULT.WARNING);
				}
			}


		}
		catch (Exception ex)
		{
			ResultLogger.log("Exception occured while verifying the image. Source: "+ex.getStackTrace(),ISSTEPACTION.True,RESULT.WARNING);
		}
	}

	public static void CreateDirectory(String path)
	{
		File file= new File(path);

		if(!file.exists())
		{
			if(file.mkdirs())
			{
				ResultLogger.log("Folder created", ISSTEPACTION.False, RESULT.PASS);
			}
			else
			{
				ResultLogger.log("Failed to create multiple directories!", ISSTEPACTION.False, RESULT.PASS);
			}
		}
	}

	public static void TakeScreenshot(String saveLocation)
	{
		try
		{
			Calendar calobj = Calendar.getInstance();

			DateFormat df = new SimpleDateFormat("dd-MM-yy");

			Path pathString = Paths.get(Storage.Custom_ScreenCapture_Path+"\\screenshots", df.format(calobj.getTime()), Storage.Project_Environment.toUpperCase().trim(), Storage.Project_Locale.toUpperCase().trim(), Storage.TC_Name);

			CreateDirectory(pathString.toString());

			File screenshot = ((TakesScreenshot)EntryPoint.driver).getScreenshotAs(OutputType.FILE);

			Path filePath = Paths.get(pathString.toString(), saveLocation + "_" + (calobj.getTimeInMillis())+ ".png" );

			FileUtils.copyFile(screenshot, new File(filePath.toString()));
			
			String scrLocation=filePath.toString();
			scrLocation=scrLocation.replace(Storage.Custom_ScreenCapture_Path, "");
			scrLocation=scrLocation.replace("\\", "/");
			//Storage.stepLog.stepscrLocation.add(scrLocation);
			Storage.stepLog.stepscrLocation.add(Storage.stepNumber, scrLocation);
		}
		catch(Exception ex)
		{
			ResultLogger.log("Exception", ISSTEPACTION.False, RESULT.EXCEPTION);
		}

	}

	public static void storeamount()
	{

	}

	public static void clickIfExist()
	{
		try
		{
			ObjectHandler.splitTarget();

			if(IsElementPresent())
			{
				if(Storage.OSType.toUpperCase().equals("ANDROID")){
					EntryPoint.AppDriver.findElement(Storage.by).click();
				}
				else{
					EntryPoint.driver.findElement(Storage.by).click();	
				}
					
				

				ResultLogger.log("Element presented and clicked the element", ISSTEPACTION.True, RESULT.PASS);
			}
			else
			{
				ResultLogger.log("Element not presented ", ISSTEPACTION.True, RESULT.PASS);
			}

		}
		catch (Exception e) 
		{
			ResultLogger.log("Exception occured. Sys Message: "+e.getMessage(), ISSTEPACTION.False, RESULT.EXCEPTION);
		}

	}
	public static void invisibility_Wait()
	{
		try
		{
			ObjectHandler.splitTarget();
			int seconds = Integer.parseInt(Storage.sValue);
			long millis = seconds * 1000;
			Storage.visibility_wait = new WebDriverWait(EntryPoint.driver, millis);
			Storage.visibility_wait.until(ExpectedConditions.invisibilityOfElementLocated(Storage.by));
			ResultLogger.log("inVisibility of elemnet found successfully.", ISSTEPACTION.True, RESULT.PASS);
		}
		catch (Exception e) 
		{
			ResultLogger.log("User Message: Unable to click the element. System Exception message: "+e.getMessage(), ISSTEPACTION.True,RESULT.EXCEPTION);
		}
	}
	public static void visibility_Wait()
	{
		try
		{
				
			ObjectHandler.splitTarget();
			int seconds = Integer.parseInt(Storage.sValue);
			long millis = seconds * 1000;
			Storage.visibility_wait = new WebDriverWait(EntryPoint.driver, millis);
			Storage.visibility_wait.until(ExpectedConditions.visibilityOfElementLocated(Storage.by));
			ResultLogger.log("visibility of elemnet found successfully.", ISSTEPACTION.True, RESULT.PASS);
			
		}
		catch (Exception e) 
		{
			ResultLogger.log("User Message: Unable to click the element. System Exception message: "+e.getMessage(), ISSTEPACTION.True,RESULT.EXCEPTION);
		}
	}
	public static void waitInSeconds() throws InterruptedException
	{
		try
		{
			int seconds = Integer.parseInt(Storage.sValue);

			long millis = seconds * 1000;

			Thread.sleep(millis);

			ResultLogger.log("Waiting for "+seconds, ISSTEPACTION.True, RESULT.PASS);
		}
		catch (Exception e) 
		{
			ResultLogger.log(e.getMessage(), ISSTEPACTION.True, RESULT.EXCEPTION);
		}
	}

}
