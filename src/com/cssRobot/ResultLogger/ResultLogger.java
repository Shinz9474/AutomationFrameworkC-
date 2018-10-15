package com.cssRobot.ResultLogger;


import org.junit.Assert;

import com.cssRobot.DB.DBOperations;
import com.cssRobot.Framework.CommonActions;
import com.cssRobot.Storage.Storage;



public class ResultLogger {
	public static enum RESULT
	{
		WARNING,FAIL,EXCEPTION, PASS
	}
	
	public static enum ISSTEPACTION
	{
		True, False;
	}
	
	
	public static void log(String argMessage, ISSTEPACTION isStepAction, RESULT result)
	{
		//System.out.println(argMessage);
		
		if(ISSTEPACTION.True == isStepAction)
		{
			Storage.stepLog.StepResult.add(result.toString());
			
			Storage.stepLog.StepComments.add(Storage.Step_info + "\r\n"+ argMessage);
			
			System.out.println(argMessage);
		}
		
	
			
			if(result == RESULT.FAIL || result == RESULT.EXCEPTION)
			{
				Storage.TestCaseResult = "FAILED";
				
				Storage.ErrorMessage += Storage.Step_info + "\r\n"+ argMessage;
				
				Storage.isTCFailed = true;
				
				System.out.println(Storage.ErrorMessage);
				
				//Take Screenshot
				//CommonActions.TakeScreenshot(Storage.TC_Name+"_"+Storage.Browser_Name+"_Warnings"+(Storage.stepNumber+1)+"_Action-"+Storage.sAction);
				
				//EntryPoint.driver.close();
				
				//getnerateReport();
				
				if(!Storage.isLocalExecution) {
				
				DBOperations.openDBConnection();

				String query = "update buildconfiguration set status='Completed' where id="+Integer.parseInt(Storage.buildId);

				DBOperations.ExecuteNonQuery(query);
				}
				Assert.fail();
			}
			else if(result == RESULT.WARNING)
			{
				Storage.TestCaseResult = "FAILED";
				
				Storage.Warnings_Count++;
				
				Storage.stepLog.StepComments.add(Storage.Step_info + "\r\n"+ argMessage);
				
				Storage.ErrorMessage += Storage.Step_info + "\r\n"+ argMessage;
				
				System.out.println(Storage.ErrorMessage);
				
				//Take Screenshot
				
				CommonActions.TakeScreenshot(Storage.TC_Name+"_"+Storage.Browser_Name+"_Warnings"+(Storage.stepNumber+1)+"_Action-"+Storage.sAction);
				
			}
		

	}

	public static void getnerateReport()
	{
		if(!Storage.isLocalExecution) {
		DBOperations.openDBConnection();

		String query1 = "update buildconfiguration set status='Completed' where id="+Integer.parseInt(Storage.buildId);

		DBOperations.ExecuteNonQuery(query1);
		}
		if(Storage.Warnings_Count>=1)
		{
			StringBuilder builder= new StringBuilder(Storage.ErrorMessage);
			
			builder.insert(0, "Test Case failed with "+Storage.Warnings_Count+" Warnings");
			
						
			Storage.ErrorMessage = builder.toString();
			
			
			
			
		}
		Storage.ErrorMessage=Storage.ErrorMessage.replace("\'","\"");
//		Calendar calobj = Calendar.getInstance();
//		 
//		DateFormat df = new SimpleDateFormat("dd-MM-yy HH:MM:SS");
		
		DBOperations.openDBConnection();
		
		String query= " INSERT INTO testcaseresults(testcaseid, testresult, duration, runnedby, owner, createddate,"
				+ " modifieddate, modifiedby, comments,active)	"
				+ "VALUES ( (select ID from testcases where testcasename='"+Storage.TC_Name+"'),"
				+ "'"+Storage.TestCaseResult+"' ,"+
			            "'"+Storage.watch.toString()+"','ADMIN', 'ADMIN', curdate(),curdate(), 'ADMIN',  '"+Storage.ErrorMessage+"','Yes')";
		
		//System.out.println("Executed Query: "+query);
		
		int rows = DBOperations.ExecuteNonQuery(query);
		
		DBOperations.closeDBConnection();
		
		if(rows==0)
		{
			ResultLogger.log("No records updated. Unable to generate report.", ISSTEPACTION.False, RESULT.FAIL);
		}
		else
		{
			for(int i=0;i<=Storage.Actions.size()-1;i++)
			{
				DBOperations.openDBConnection();
				try{
			// Inserting the Step result
					query ="INSERT INTO teststepresults(	 testresultslogsid, stepid, actiontype, target, "+
							"valuetext, stepresult, errorcomments, screenshotlocation)	"
									+ "VALUES ( (select MAX(ID) as ID from testcaseresults), "+Storage.stepLog.stepNumber.get(i)+",'"+
							Storage.stepLog.StepAction.get(i).replace("\'","\"")+"','"+Storage.stepLog.StepTarget.get(i).replace("\'","\"")+
							"','"+Storage.stepLog.StepValue.get(i).replace("\'","\"")+"','"+Storage.stepLog.StepResult.get(i).replace("\'","\"")+"','"+
							Storage.stepLog.StepComments.get(i).replace("\'","\"")+"','"+Storage.stepLog.stepscrLocation.get(i)+"')";
					
					//System.out.println("Executed Query: "+query);
					
					rows = DBOperations.ExecuteNonQuery(query);
					
					DBOperations.closeDBConnection();
				}
				catch(Exception ex)
				{

				}
				
				
			}
		}
		
	}

}
