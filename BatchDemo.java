package com.valuemomentum.training.jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BatchDemo {

	public static void main(String[] args) {
		Connection con;
        Statement stmt;
        ResultSet rs;
        try {

 

            Class.forName("com.mysql.cj.jdbc.Driver");

 

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqljdbc", "root", "vish8797");

 

            stmt = con.createStatement(); 
            
            //add branch
            stmt.addBatch("insert into candidate_skills values(100,5)");
            stmt.addBatch("update skills set name='Ajax' where id=3");
            stmt.addBatch("delete from candidates where id=80");
            
            //disable auto-commit mode
            con.setAutoCommit(false);
            try
            {
            	stmt.executeBatch();
            	con.commit();
            	System.out.println("batch is successfuly executed");
            	
            }
            catch(Exception e) {
            	try {
            		con.rollback();
            		System.out.println("batch is failed");
            		System.out.println("Exception is"+e);
            	}
            	catch(Exception e1)
            	{
            		System.out.println("Exception is"+e1);
            		
            	}
           		}
            //end of outer catch
            //cleanup
            stmt.close();
            con.close();
        
	}
        catch(Exception e2) {
        	System.out.println(e2);
        }
	}
}
