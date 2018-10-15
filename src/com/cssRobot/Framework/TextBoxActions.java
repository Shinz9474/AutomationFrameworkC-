package com.cssRobot.Framework;

import com.cssRobot.Execution.ObjectHandler;
import com.cssRobot.ResultLogger.ResultLogger;
import com.cssRobot.ResultLogger.ResultLogger.ISSTEPACTION;
import com.cssRobot.ResultLogger.ResultLogger.RESULT;
import com.cssRobot.Storage.Storage;

public class TextBoxActions {

	public static void type()
	{
		try
		{
			ObjectHandler.GetWebElement();
			
			if(Storage.sValue.startsWith("$"))
			{
				System.out.println(Storage.sValue.length());
				Storage.sValue=Storage.sValue.substring(2, Storage.sValue.length()-1);
				//Storage.sValue=EntryPoint.hm.get(Storage.sValue);
			}
			
			if(!Storage.OSType.toUpperCase().equals("IOS"))
				Storage.webElement.clear();

			Storage.webElement.sendKeys(Storage.getHashTable(Storage.sValue));

			ResultLogger.log("Entered Text into the specified filed. ", ISSTEPACTION.True, RESULT.PASS);
		}
		catch (Exception e) 
		{
			ResultLogger.log("Exception occured while entering the text/value. System Message: "+e.getMessage(), ISSTEPACTION.True, RESULT.EXCEPTION);
		}
	}
	public static void type_clear()
	{
		try
		{
			ObjectHandler.GetWebElement();
			Storage.webElement.click();
			Storage.webElement.clear();
			ResultLogger.log("click and clear Text into the specified filed. ", ResultLogger.ISSTEPACTION.True, ResultLogger.RESULT.PASS);
		}
		catch (Exception e)
		{
			ResultLogger.log("Exception occured while entering the text/value. System Message: " + e.getMessage(), ResultLogger.ISSTEPACTION.True, ResultLogger.RESULT.EXCEPTION);
		}
	}
}
