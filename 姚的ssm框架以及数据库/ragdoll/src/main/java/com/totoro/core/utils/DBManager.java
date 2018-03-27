package com.totoro.core.utils;


import java.sql.*;
import java.text.*;
import java.util.*;


/**  
 * @Description: TODO(用一句话描述该文件做什么)
 * @author ljn  
 * @date 2014-4-11 下午1:02:37
 */
public class DBManager {
	public String ClassString=null;
	  public String ConnectionString=null;
	  public String UserName=null;
	  public String PassWord=null;

	  public Connection Conn;
	  public Statement Stmt;


	  public DBManager() {
	   /* ClassString=ArgsUtil.getConnDrive();
	    ConnectionString= ArgsUtil.getConnUrl();
	    UserName=ArgsUtil.getConnUser();
	    PassWord=ArgsUtil.getConnPwd();*/
	    
	    
	    ClassString = "com.mysql.jdbc.Driver";
	    ConnectionString = "jdbc:mysql://127.0.0.1:3306/work?useUnicode=true&amp;characterEncoding=utf-8";
	    UserName = "root";
	    PassWord = "root";
	    
	    
	  }

	  public boolean OpenConnection()
	  {
	   boolean mResult=true;
	   try
	   {
	     Class.forName(ClassString);
	     if ((UserName==null) && (PassWord==null))
	     {
	       Conn= DriverManager.getConnection(ConnectionString);
	     }
	     else
	     {
	       Conn= DriverManager.getConnection(ConnectionString,UserName,PassWord);
	     }

	     Stmt=Conn.createStatement();
	     mResult=true;
	   }
	   catch(Exception e)
	   {
	     System.out.println(e.toString());
	     mResult=false;
	   }
	   return (mResult);
	  }

	  //关闭数据库连接
	  public void CloseConnection()
	  {
	   try
	   {
	     Stmt.close();
	     Conn.close();
	   }
	   catch(Exception e)
	   {
	     System.out.println(e.toString());
	   }
	  }

	  public String GetDateTime()
	  {
	   Calendar cal  = Calendar.getInstance();
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String mDateTime=formatter.format(cal.getTime());
	   return (mDateTime);
	  }

	  public  java.sql.Date  GetDate()
	  {
	    java.sql.Date mDate;
	    Calendar cal  = Calendar.getInstance();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String mDateTime=formatter.format(cal.getTime());
	    return (java.sql.Date.valueOf(mDateTime));
	  }

	  public int GetMaxID(String vTableName,String vFieldName)
	  {
	   int mResult=0;
	   String mSql=new String();
	   DBManager DbaObj=new DBManager();
	   mSql = "select max("+vFieldName+")+1 as MaxID from " + vTableName;
	   if (DbaObj.OpenConnection())
	   {
	     try
	     {
	       ResultSet result=DbaObj.ExecuteQuery(mSql);
	       if (result.next())
	       {
	         mResult=result.getInt("MaxID");
	       }
	       if (mResult==0) mResult=1;
	       //System.out.println(String.valueOf(mResult));
	       result.close();
	     }
	     catch(Exception e)
	     {
	       System.out.println(e.toString());
	     }
	     DbaObj.CloseConnection();
	   }
	   return (mResult);
	 }

	  public ResultSet ExecuteQuery(String SqlString)
	  {
	    ResultSet result=null;
	    try
	    {
	      result=Stmt.executeQuery(SqlString);
	    }
	    catch(Exception e)
	    {
	      System.out.println(e.toString());
	    }
	    return (result);
	  }

	  public int ExecuteUpdate(String SqlString)
	  {
	    int result=0;
	    try
	    {
	      result=Stmt.executeUpdate(SqlString);
	    }
	    catch(Exception e)
	    {
	      System.out.println(e.toString());
	    }
	    return (result);
	  }
}

