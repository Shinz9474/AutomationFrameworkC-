package com.cssRobot.DB;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;

import com.cssRobot.ResultLogger.ResultLogger;
import com.cssRobot.ResultLogger.ResultLogger.ISSTEPACTION;
import com.cssRobot.ResultLogger.ResultLogger.RESULT;
import com.cssRobot.Storage.DBStorage;
import com.cssRobot.Storage.Storage;



public class DBOperations {

	public static void openDBConnection()
	{
		try
		{
//			Class.forName("com.mysql.jdbc.Driver"); 
//			
//			String url = "jdbc:mysql://10.4.64.106:3306/automation?autoReconnect=true&useSSL=false";
//
//			DBStorage.dBConnectionProperties=new Properties();
//
//			DBStorage.dBConnectionProperties.setProperty("user","username");
//
//			DBStorage.dBConnectionProperties.setProperty("password","password");
//
//			DBStorage.dBConnectionProperties.setProperty("ssl","true");
//
//			DBStorage.connection = DriverManager.getConnection(url, DBStorage.dBConnectionProperties);
//			
			Class.forName("com.mysql.jdbc.Driver"); 
			
			String url = "jdbc:mysql://mkrobotautomation.mysql.database.azure.com:3306/automation?autoReconnect=true&useSSL=false";
			url ="jdbc:mysql://mkrobotautomation.mysql.database.azure.com:3306/automation?useSSL=true&requireSSL=false";

//			Properties p=new Properties();
//			p.setProperty("user", "mkrobot@mkrobotautomation");
//			p.setProperty("password", "marykay123$");


			DBStorage.connection=DriverManager.getConnection(url, "mkrobot@mkrobotautomation", "marykay123$");

			ResultLogger.log("Connection established successfully.", ISSTEPACTION.False,RESULT.PASS);
		}
		catch(Exception ex)
		{
			ResultLogger.log("Exception occured while establishing the connection", ISSTEPACTION.False,RESULT.EXCEPTION);
		}
	}

	public static void closeDBConnection()
	{
		try
		{
			if(!DBStorage.connection.isClosed())
			{
				DBStorage.connection.close();

				ResultLogger.log("Connection closed successfully.", ISSTEPACTION.False,RESULT.PASS);
			}
		}
		catch(Exception ex)
		{
			ResultLogger.log("Exception occured while closing the connection", ISSTEPACTION.False,RESULT.EXCEPTION);
		}
	}

	public static ResultSet ExecuteQuery(String query)
	{
		ResultSet queryResults = null;

		try
		{
			queryResults =  DBStorage.connection.createStatement().executeQuery(query);
		}
		catch(Exception ex)
		{
			ResultLogger.log("Exception occured while reading the records from DB", ISSTEPACTION.False,RESULT.EXCEPTION);
		}

		return queryResults;
	}

	public static int ExecuteNonQuery(String query)
	{
		int rows=0;

		try
		{

			DBOperations.openDBConnection();

			rows = DBStorage.connection.createStatement().executeUpdate(query);

			ResultLogger.log("No. Of records updated: "+rows, ISSTEPACTION.False, RESULT.PASS);
			
			DBStorage.connection.close();
		}
		catch(Exception ex)
		{
			ResultLogger.log(ex.getMessage(), ISSTEPACTION.False, RESULT.EXCEPTION);
		}

		return rows;


	}

	public static void readTestCaseDesignSteps()
	{
		try
		{
			openDBConnection();

			readSteps();

			closeDBConnection();

		}
		catch(Exception ex)
		{
			ResultLogger.log("Exception occured while reading the test case design steps.", ISSTEPACTION.False,RESULT.EXCEPTION);
		}
	}


	public static void readSteps()
	{
		try
		{
			//Clearing the Actions, Targets, and Values lists
			com.cssRobot.Storage.Storage.Actions.clear();

			Storage.Targets.clear();

			Storage.Values.clear();

			//Reading the Steps by executing the DB query			
			String query=" SELECT STEPS.ID,STEPS.stepid,STEPS.obj_ref_id,obj.obj_ref_name,obj.target_property,STEPS.testcaseid, STEPS.htmlelementtype,ACTION.actiontypename,STEPS.status,STEPS.valuetext FROM test_case_design_steps STEPS inner join actiontype ACTION on ACTION.ID=STEPS.actiontype_id  inner join obj_repository obj on STEPS.obj_ref_id =  obj.id where  STEPS.testcaseid = (SELECT ID FROM testcases WHERE testcasename ='"+Storage.TC_Name+"')ORDER BY STEPS.stepid ASC";

			ResultSet queryResult =  ExecuteQuery(query);

			ResultLogger.log("Exceuted Query: "+query, ISSTEPACTION.False,RESULT.PASS);

			while(queryResult.next())
			{
				Storage.Actions.add(queryResult.getString(8));

				Storage.Targets.add(queryResult.getString(5));

				Storage.Values.add(queryResult.getString(10));
			}
		}
		catch(Exception ex)
		{
			ResultLogger.log("Exception occured while reading the test steps", ISSTEPACTION.False,RESULT.EXCEPTION);
		}

		if(Storage.Actions.size()==0)
		{
			ResultLogger.log("No Test cases found. Please design test case steps to execute", ISSTEPACTION.False,RESULT.FAIL);
		}
	}
}
