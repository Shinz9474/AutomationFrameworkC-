package com.cssRobot.Start;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Hashtable;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cssRobot.DB.DBOperations;
import com.cssRobot.Execution.Browser;
import com.cssRobot.Framework.CommonActions;
import com.cssRobot.Framework.ListBoxActions;
import com.cssRobot.Framework.TextBoxActions;
import com.cssRobot.ResultLogger.ResultLogger;
import com.cssRobot.ResultLogger.ResultLogger.ISSTEPACTION;
import com.cssRobot.ResultLogger.ResultLogger.RESULT;
import com.cssRobot.Storage.Storage;


import io.appium.java_client.AppiumDriver;

public class EntryPoint 
{
	
	
	EntryPoint()
	{

		try
		{
			Storage.watch.start();
			InetAddress addr;

			addr = InetAddress.getLocalHost();

			Storage.machine_name = addr.getHostName();

		}
		catch (UnknownHostException ex)
		{
			// ResultLogger.log("Exception occured While getting the Host name",ISSTEPACTION.False,RESULT.EXCEPTION);
		}

	}

	public static WebDriver driver;
	
	public static AppiumDriver<WebElement> AppDriver;
	//public static IOSDriver driver; 
	//public static  ChromeDriver driver;
	public static Dimension size;
	public static WebDriverWait wait;
	public static WebElement element;
	public static Process process;
	public static DesiredCapabilities capabilities=null;
	public static JavascriptExecutor js ;
	public static Point p;
	public static double x ;
	public static double y ;
	public static HashMap<String , Double> point ;
	public static Hashtable<String,String> hm=null;
	
	public static void main(String[] args) throws IOException  {
		try
		{
			
			Storage.storageLocation.clear();

			//Initialize test cases
			InitializeTestExecution(args);

			//Initialize the webDriver
			Browser.launchBrowser();

			//Read Test case steps
			DBOperations.readTestCaseDesignSteps();

			//ResultLogger.log("Execution started...", ISSTEPACTION.False,RESULT.PASS);

			for(Storage.stepNumber=0;Storage.stepNumber <= Storage.Actions.size()-1;Storage.stepNumber++)
			{
				Storage.sAction = Storage.Actions.get(Storage.stepNumber);

				Storage.sTarget = Storage.Targets.get(Storage.stepNumber);

				Storage.sValue = Storage.Values.get(Storage.stepNumber);

				Storage.Step_info = "Step number: "+(Storage.stepNumber)+"||Action: "+Storage.sAction+"|| Target: "+Storage.sTarget;

				Storage.stepLog.stepNumber.add(((Integer)Storage.stepNumber).toString());

				Storage.stepLog.StepAction.add(Storage.sAction);

				Storage.stepLog.StepTarget.add(Storage.sTarget);

				Storage.stepLog.StepValue.add(Storage.sValue);
				
				Storage.stepLog.stepscrLocation.add("NA");
			
				System.out.println("-----------------------------------------------------------------");
			
				System.out.println("Step Number:"+(Storage.stepNumber+1)+"|Action:"+Storage.sAction+"|Target:"+Storage.sTarget+"|Value:"+Storage.sValue );

				ResultLogger.log("Step Number:"+(Storage.stepNumber)+"|Action:"+Storage.sAction+"|Target:"+Storage.sTarget+"|Value:"+Storage.sValue, ISSTEPACTION.False, RESULT.PASS);

				executeAction();
				
				System.out.println("-----------------------------------------------------------------");

			}

			Storage.watch.stop();
			
			Storage.testDuration=Storage.watch.toString();
			
			System.out.println("Time Taken:"+Storage.watch);
			
			ResultLogger.getnerateReport();
			
			EntryPoint.driver.close();



		}
		catch(Exception ex)
		{
			//Assert.fail(ex.getMessage());
			
			Storage.watch.stop();
			ResultLogger.getnerateReport();
			CommonActions.End();
		}
	
	}
	
	public static void InitializeTestExecution(String[]args){

		try
		{
			Storage.isLocalExecution = true;
			
			hm=new Hashtable<String,String>();   
			  
			if(Storage.isLocalExecution)
			{
				//ResultLogger.log("Execution starts from Local Code base...", ISSTEPACTION.False,RESULT.PASS);

				Storage.OSType = "Android".toUpperCase();
				
				Storage.Browser_Name = "Chrome".toUpperCase(); //Browser Names: Chrome/IE/ Firefox

				Storage.TC_Name = "ShopDisney_Signup_Android"; //Provide the Test case name to run PriceFilter_VerifyProducts//SelectStoreAndPlaceOrder_Test//Android_AddProductToBag "shopdisneyHeaderLinksVerification"
				
				Storage.AutomationToolType="BROWSERSTACK";
			}

			else
			{
				Storage.TC_Name=args[0];
				
				Storage.Browser_Name=args[1];
				
				Storage.OSType=args[2];
				
				Storage.buildId=args[3];

				try {
					
					Storage.agentName=args[4];
					
					Storage.isGridExecution=args[5];
					
					System.out.println("Agent Name:"+Storage.agentName);
					System.out.println("Grid:"+Storage.isGridExecution);
					
				}
				catch(Exception ex) {
					
				}
				System.out.println("Test Case Name:"+Storage.TC_Name);
				System.out.println("Browser:"+Storage.Browser_Name);
				
				
				DBOperations.openDBConnection();

				String query = "update buildconfiguration set status='In Progress' where id="+Integer.parseInt(Storage.buildId);

				DBOperations.ExecuteNonQuery(query);

				
			}
		}
		catch(Exception ex)
		{
			//ResultLogger.log("Exception occured. System Message: "+ex.getMessage(),ISSTEPACTION.False, RESULT.EXCEPTION);
		}

	}

	public static void executeAction() throws InterruptedException
	{
		

		Browser.waitForPageLoad();

		if(Storage.sAction.toUpperCase().equals("OPEN"))
		{
			Browser.open();
		}
		else if(Storage.sAction.toUpperCase().equalsIgnoreCase("switchToIframe")) {
			CommonActions.switchToIframe();
		}
		else if(Storage.sAction.toUpperCase().equals("DeleteAllVisibleCookies".toUpperCase()))
		{
			Browser.DeleteVisibleCookies();
		}
		
		else if(Storage.sAction.toUpperCase().equals("WaitInSecond".toUpperCase()))
		{
			CommonActions.WaitInSecond();
		}
		else if(Storage.sAction.toUpperCase().equals("click".toUpperCase()))
		{
			CommonActions.click();
		}
		else if(Storage.sAction.toUpperCase().equals("verifytitle".toUpperCase()))
		{
			Browser.verifytitle();
		
		}
		else if(Storage.sAction.toUpperCase().equals("clickandwait".toUpperCase()))
		{
			CommonActions.clickAndWait();
		}
		else if(Storage.sAction.toUpperCase().equals("storetext".toUpperCase()))
		{
			CommonActions.storeText();
		}
		else if(Storage.sAction.toUpperCase().equals("RefreshPage".toUpperCase()))
		{
			CommonActions.RefreshPage();
		}
		else if(Storage.sAction.toUpperCase().equals("swipe".toUpperCase()))
		{
			CommonActions.swipe();
		}
		else if(Storage.sAction.toUpperCase().equals("storeamount".toUpperCase()))
		{
			CommonActions.storeamount();
		}
		else if(Storage.sAction.toUpperCase().equals("verifyimage".toUpperCase()))
		{
			CommonActions.verifyImagePresent();
		}
		else if(Storage.sAction.toUpperCase().equals("storeuniqueemail".toUpperCase()))
		{
			CommonActions.storeUniqueEmail();
		}
		else if(Storage.sAction.toUpperCase().equals("StoreListBoxSelectedValue".toUpperCase()))
		{
			ListBoxActions.StoreListBoxSelectedValue();
		}
		else if(Storage.sAction.toUpperCase().equals("storeuniquename".toUpperCase()))
		{
			CommonActions.storeUniqueName();
		}
		else if(Storage.sAction.toUpperCase().equals("TypewithKey".toUpperCase()))
		{
			CommonActions.TypeWithKey(Storage.sValue);
		}
		else if(Storage.sAction.toUpperCase().equals("type".toUpperCase()))
		{
			TextBoxActions.type();
		}
		else if(Storage.sAction.toUpperCase().equals("select".toUpperCase()))
		{
			ListBoxActions.select();
		}
		else if(Storage.sAction.toUpperCase().equals("clickIfExist".toUpperCase()))
		{
			CommonActions.clickIfExist();

		}
		else if(Storage.sAction.toUpperCase().equals("hideKeyboard".toUpperCase()))
		{
			CommonActions.hideKeyboard();
		}
		else if(Storage.sAction.toUpperCase().equals("verifyText".toUpperCase()))
		{
			CommonActions.verifyText();

		}
		else if(Storage.sAction.toUpperCase().equals("waitInSeconds".toUpperCase()))
		{
			CommonActions.waitInSeconds();

		}
		else if(Storage.sAction.toUpperCase().equals("locateNapster".toUpperCase()))
		{
			CommonActions.LOCATE_NASPTER();

		}
		else if(Storage.sAction.toUpperCase().equals("pressEnterKey".toUpperCase()))
		{
			if(Storage.OSType.equalsIgnoreCase("Windows")){
			CommonActions.pressEnterKey();
			}
			else{
			CommonActions.run_adb();
			}

		}
						
		else if(Storage.sAction.toUpperCase().equals("SearchTextEntry".toUpperCase()))
		{
			CommonActions.search_text_entry();

		}
		else if (Storage.sAction.toUpperCase().equals("Textclear".toUpperCase()))
		{
			TextBoxActions.type_clear();
		}
		else if (Storage.sAction.toUpperCase().equals("setPriceRange".toUpperCase()))
		{
			//ProductSpecificActions.setPriceRange();
		}
		else if (Storage.sAction.toUpperCase().equals("verifyPriceRangeProducts".toUpperCase()))
		{
			//ProductSpecificActions.verifyPriceRangeProducts();
		}
		else if(Storage.sAction.toUpperCase().equals("search_record".toUpperCase()))
		{
			CommonActions.search_record();

		}
		else if(Storage.sAction.toUpperCase().equals("scrollToView".toUpperCase()))
		{
			CommonActions.scroll_to_view();
		}
		else if(Storage.sAction.toUpperCase().equals("tabElement".toUpperCase()))
		{
			CommonActions.tab_element();
		}
		else if(Storage.sAction.toUpperCase().equals("tabNapstersetting".toUpperCase()))
		{
			CommonActions.tap_NASPTER_setting();
		}
		else if ((Storage.sAction.toUpperCase().equals("stop".toUpperCase())) || (Storage.sAction.toUpperCase().equals("next".toUpperCase())) || (Storage.sAction.toUpperCase().equals("previous".toUpperCase()))) 
		{
			CommonActions.run_adb();
		}
		else if ((Storage.sAction.toUpperCase().equals("repeat".toUpperCase())) || (Storage.sAction.toUpperCase().equals("shuffle".toUpperCase()))) 
		{
			CommonActions.no_exection();
		}

		/*else if(Storage.sAction.toUpperCase().equals("stop".toUpperCase())||Storage.sAction.toUpperCase().equals("repeat".toUpperCase())||Storage.sAction.toUpperCase().equals("previous".toUpperCase())||Storage.sAction.toUpperCase().equals("next".toUpperCase())||Storage.sAction.toUpperCase().equals("shuffle".toUpperCase()))
		{
			CommonActions.click_by_javascript();
		}*/
		else if(Storage.sAction.toUpperCase().equals("visibilityofwait".toUpperCase()))
		{
			CommonActions.visibility_Wait();			
		}
		else if(Storage.sAction.toUpperCase().equals("invisibilityofwait".toUpperCase()))
		{
			CommonActions.invisibility_Wait();
		}
		else if(Storage.sAction.toUpperCase().equals("quit".toUpperCase()))
		{
			CommonActions.End();
		}
	}
	
	public static void start() throws InterruptedException
	{
        
        Storage.storageLocation.clear();
        
        hm=new Hashtable<String,String>();  
        
        //Initialize test cases
        //InitializeTestExecution(args);

        //Initialize the webDriver
        Browser.launchBrowser();

        //Read Test case steps
        DBOperations.readTestCaseDesignSteps();

        //ResultLogger.log("Execution started...", ISSTEPACTION.False,RESULT.PASS);

        for(Storage.stepNumber=0;Storage.stepNumber <= Storage.Actions.size()-1;Storage.stepNumber++)
        {
               Storage.sAction = Storage.Actions.get(Storage.stepNumber);

               Storage.sTarget = Storage.Targets.get(Storage.stepNumber);

               Storage.sValue = Storage.Values.get(Storage.stepNumber);

               Storage.Step_info = "Step number: "+(Storage.stepNumber)+"||Action: "+Storage.sAction+"|| Target: "+Storage.sTarget;

               Storage.stepLog.stepNumber.add(((Integer)Storage.stepNumber).toString());

               Storage.stepLog.StepAction.add(Storage.sAction);

               Storage.stepLog.StepTarget.add(Storage.sTarget);

               Storage.stepLog.StepValue.add(Storage.sValue);
               
               Storage.stepLog.stepscrLocation.add("NA");
        
               System.out.println("-----------------------------------------------------------------");
        
               System.out.println("Step Number:"+(Storage.stepNumber+1)+"|Action:"+Storage.sAction+"|Target:"+Storage.sTarget+"|Value:"+Storage.sValue );

               ResultLogger.log("Step Number:"+(Storage.stepNumber)+"|Action:"+Storage.sAction+"|Target:"+Storage.sTarget+"|Value:"+Storage.sValue, ISSTEPACTION.False, RESULT.PASS);

               executeAction();
               
               System.out.println("-----------------------------------------------------------------");

        }

        Storage.watch.stop();
        
        Storage.testDuration=Storage.watch.toString();
        
        System.out.println("Time Taken:"+Storage.watch);
        
        
        
        ResultLogger.getnerateReport();
        
        

        CommonActions.End();
        
 }


}
