package com.cssRobot.Storage;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cssRobot.ResultLogger.ResultLogger;
import com.cssRobot.ResultLogger.ResultLogger.ISSTEPACTION;
import com.cssRobot.ResultLogger.ResultLogger.RESULT;
import com.google.common.base.Stopwatch;





public class Storage {

	public static String scrLocation="";
	public static String buildId="";
	public static Stopwatch watch=Stopwatch.createStarted();
	
	public static String testDuration="";
	
	public static String agentName="";
	
	public static String isGridExecution="";
	/* Test Case Variables */ 
	
	public static  int EmailStringSizeRandom = 12;

	public static String Project_Environment = "QA";

	public static String Project_Locale = "US";

	public static String  machine_name = "";
	
	public static String TC_Name = "";
	
	public static String Market = "";
	
	public static String Environment = "";
	
	public static boolean isLocalExecution = false;
	
	public static String sAction = "";
	
	public static String sTarget = "";
	
	public static String sValue = "";
	
	public static String sActualValue = "";
	
	public static String tPropValue = "";
	
	public static String OSType = "";
	
	public static String AutomationToolType = "";
	
	public static String tPropType = "";
	
	public static int stepNumber = 0;
	
	public static com.cssRobot.ResultLogger.StepLogger stepLog = new com.cssRobot.ResultLogger.StepLogger();
	
	public static String Custom_ScreenCapture_Path ="D:\\checkhtml";
	
	public static String TestCaseResult = "PASSED";
	
	public static int Warnings_Count = 0;
	
	public static String Step_info = null;
	
	public static String ErrorMessage = "";
	
	public static boolean isTCFailed = false; 
	
	/* WebDriver Variables */
	
	//public static AndroidDriver driver = null;
	
	public static WebElement webElement = null;
	public static List<WebElement> webElements = null;
	public static RemoteWebDriver remoteWebDriver = null;
	public static WebDriverWait visibility_wait=null;
	

	
	public static RemoteWebElement remoteWebElement = null;
	
	public static By by = null;
	
	/* Execution Variables */
	
	public static String Browser_Name = "";
	
	public static ArrayList<String> Actions = new ArrayList<String>();
	
	public static ArrayList<String> Targets = new ArrayList<String>();
	
	public static ArrayList<String> Values =new ArrayList<String>();
	
	 
	  
		//input[@name='password']	${password}
	
	public static Hashtable< Object,Object> storageLocation=new Hashtable<Object,Object>();
	
	public static void insertHashTable(String hKey, String hValue)
    {

        String temp = "";
        
        if (hKey.startsWith("${"))
        {
            temp = hKey.substring(2, (hKey.length() - 1));
        }
        else
        {
            temp = hKey;
        }

        if (!Storage.storageLocation.containsKey(temp))
        {
            Storage.storageLocation.put(temp, hValue);
        }
        else
            ResultLogger.log("Already in Hash Table - Key "+temp,ISSTEPACTION.False,RESULT.WARNING);

    }
	
	 public static void removeHashTableData(String hKey)
     {
         try
         {
             String temp = "";
             
             if (hKey.startsWith("${"))
             {
                 temp = hKey.substring(2, (hKey.length() - 3));
             }
             else
             {
                 temp = hKey;
             }
             if (Storage.storageLocation.containsKey(temp))
             {
            	 Storage.storageLocation.remove(temp);
                 
             }
             ResultLogger.log("Removed Data from hash table", ISSTEPACTION.False, RESULT.PASS);
         }
         catch (Exception e)
         {
             ResultLogger.log("Exception occured while Removing Data from hash table : "+e.getMessage(),ISSTEPACTION.False,RESULT.EXCEPTION);
         }
     }

	 public static void clearHashTableKeys()
     {
         
         try
         {
             Storage.storageLocation.clear();
             
             ResultLogger.log("storage location cleared...",ISSTEPACTION.False,RESULT.EXCEPTION);
         }
         catch (Exception e)
         {
        	 ResultLogger.log("Exception occured while Removing Data from hash table : "+e.getMessage(),ISSTEPACTION.False,RESULT.EXCEPTION);
         }
     }

	 public static String getHashTable(String hKey)
     {
         String temp = "", tempData = "";
         
         try
         {

             if (hKey.startsWith("${"))
             {
                 temp = hKey.substring(2, (hKey.length() - 1));
             }
             else
             {
                 temp = hKey;
             }

             if (Storage.storageLocation.size() > 0 && Storage.storageLocation.containsKey(temp))
             {
                 tempData = Storage.storageLocation.get(temp).toString();
             }
             else
             {
              //   Console.WriteLine("Else Part Hash Table Key : " + temp + " - Value : " + tempData);
                 tempData = hKey;
             }

         }
         catch (Exception e)
         {
             ResultLogger.log("Hash Table Data error occurred :method getHashTable() for Data " + hKey+" - "+Storage.stepNumber,ISSTEPACTION.False,RESULT.EXCEPTION);
         }
         
         return tempData;
     }
	
	
}
