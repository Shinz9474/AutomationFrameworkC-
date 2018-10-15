package com.cssRobot.Execution;

import org.openqa.selenium.By;

import com.cssRobot.ResultLogger.ResultLogger;
import com.cssRobot.ResultLogger.ResultLogger.ISSTEPACTION;
import com.cssRobot.ResultLogger.ResultLogger.RESULT;
import com.cssRobot.Start.EntryPoint;
import com.cssRobot.Storage.Storage;



public class ObjectHandler {

	public static void GetWebElement()
    {
        try
        {
        	splitTarget();
        	if (!(Storage.OSType.toUpperCase().equals("IOS") && Storage.Browser_Name.equalsIgnoreCase("Safari") && Storage.AutomationToolType.equalsIgnoreCase("In-house")) ||(!(Storage.OSType.toUpperCase().equals("Android") && Storage.Browser_Name.equalsIgnoreCase("chrome") && Storage.AutomationToolType.equalsIgnoreCase("In-house")))) 
			{
        	//if(Storage.OSType.toUpperCase().equals("WINDOWS") || Storage.OSType.toUpperCase().equals("MAC")){
        		Browser.untilAngularFinishHttpCalls();
            	
                
               // String s=EntryPoint.driver.getPageSource();
                //  System.out.println("");

                  Storage.webElement=EntryPoint.driver.findElement(Storage.by);
                  	
        	}
        	else
        	{
        		  Storage.webElement=EntryPoint.AppDriver.findElement(Storage.by);
        	}
        	
            ResultLogger.log("Element Found in the page", ISSTEPACTION.False, RESULT.PASS);
            
            System.out.println("Element Found in the page");

        }
        catch (Exception ex)
        {
           ResultLogger.log(ex.getMessage(),ISSTEPACTION.False,RESULT.EXCEPTION);
           //System.out.println("");
        }
    }
	public static void GetWebElements()
    {
        try
        {
            splitTarget();

            Storage.webElements=EntryPoint.driver.findElements(Storage.by);
            
            ResultLogger.log("Element Found in the page", ISSTEPACTION.False, RESULT.PASS);

        }
        catch (Exception ex)
        {
           ResultLogger.log(ex.getMessage(),ISSTEPACTION.False,RESULT.EXCEPTION);
        }
    }
	public static void splitTarget()
	{	
		/* To Split and Store the Property type and Property Values from Target */
		 
          if (Storage.sTarget.toUpperCase().startsWith("ID"))
            {
                Storage.tPropValue = Storage.sTarget.split("=")[1];
 
                Storage.tPropType = "ID";
 
                Storage.by = By.id(Storage.tPropValue);
                
                
 
            }
            else if (Storage.sTarget.toUpperCase().startsWith("NAME"))
            {
                Storage.tPropValue = Storage.sTarget.split("=")[1];
 
                Storage.tPropType = "NAME";
 
                Storage.by = By.name(Storage.tPropValue);
 
            }
            else if (Storage.sTarget.toUpperCase().startsWith("CSS"))
            {
                Storage.tPropValue = Storage.sTarget.substring(4);
 
                Storage.tPropType = "CSS";
 
                Storage.by = By.cssSelector(Storage.tPropValue);
 
            }
            else if (Storage.sTarget.toUpperCase().startsWith("CLASS"))
            {
                Storage.tPropValue = Storage.sTarget.split("=")[1];
 
                Storage.tPropType = "CLASS";
 
                Storage.by = By.className(Storage.tPropValue);
 
            }
            else if (Storage.sTarget.toUpperCase().startsWith("LINK"))
            {
                Storage.tPropValue = Storage.sTarget.split("=")[1];
 
                Storage.tPropType = "LINK";
 
                Storage.by = By.linkText(Storage.tPropValue);
 
            }
            else if (Storage.sTarget.toUpperCase().startsWith("XPATH"))
            {
                Storage.tPropValue = Storage.sTarget.split("=")[1];
 
                Storage.tPropType = "XPATH";
 
                Storage.by = By.xpath(Storage.tPropValue);
 
            }
            else if (Storage.sTarget.toUpperCase().startsWith("//"))
            {
                Storage.tPropValue = Storage.sTarget;
 
                Storage.tPropType = "XPATH";
 
                Storage.by = By.xpath(Storage.tPropValue);
 
            }
            else if(Storage.sTarget.toUpperCase().startsWith(".//"))
            {
                Storage.tPropValue = Storage.sTarget;
 
                Storage.tPropType = "XPATH";
 
                Storage.by = By.xpath(Storage.tPropValue);
            }
            else if(Storage.sTarget.toUpperCase().startsWith("(//"))
            {
                Storage.tPropValue = Storage.sTarget;
 
                Storage.tPropType = "XPATH";
 
                Storage.by = By.xpath(Storage.tPropValue);
            }
            else
            {
                ResultLogger.log("Invalid Target Identified - "+Storage.sTarget, ISSTEPACTION.False,RESULT.FAIL);
            }
	}
}
