package com.cssRobot.Framework;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.cssRobot.Execution.ObjectHandler;
import com.cssRobot.ResultLogger.ResultLogger;
import com.cssRobot.ResultLogger.ResultLogger.ISSTEPACTION;
import com.cssRobot.ResultLogger.ResultLogger.RESULT;
import com.cssRobot.Storage.Storage;



public class ListBoxActions {

	public static void select()
	{
		try
		{
			ObjectHandler.GetWebElement();
			
			Select select = new Select(Storage.webElement);
			
			String TargetOptionType = Storage.sValue.split("=")[0];
			
			String TargetOptionValue = Storage.sValue.split("=")[1];
			
			if(TargetOptionType.toUpperCase().equals("LABEL"))
			{
				select.selectByVisibleText(TargetOptionValue);
			}
			else if(TargetOptionType.toUpperCase().equals("VALUE"))
			{
				select.selectByValue(TargetOptionValue);
			}
			else if(TargetOptionType.toUpperCase().equals("INDEX"))
			{
				select.selectByIndex(Integer.parseInt(TargetOptionValue));
			}
			
			ResultLogger.log("Option selected from List/dropdown successfully. ", ISSTEPACTION.True, RESULT.PASS);
		}
		catch (Exception e) 
		{
			ResultLogger.log("Exception occured while selecting the option from dropdown/list. Exception Message: "+e.getMessage(), ISSTEPACTION.True, RESULT.EXCEPTION);
		}
		
	}
	public static void StoreListBoxSelectedValue(){
		ObjectHandler.GetWebElement();
		
		Select select=new Select(Storage.webElement);
		
		WebElement selectedOption= select.getFirstSelectedOption();
		
		String selectedText=selectedOption.getText();
		
		Storage.insertHashTable(Storage.sValue, selectedText);
	}
}
