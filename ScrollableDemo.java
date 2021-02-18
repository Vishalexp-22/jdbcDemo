package com.valuemomentum.training.jdbcdemo;

 

import java.sql.*;

 

public class ScrollableDemo 
{
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        Connection con;
        Statement stmt;
        ResultSet rs;
        try 
        {
            // Register JDBC Driver
            Class.forName("com.mysql.jdbc.Driver");
            
            //Open a Connection
            System.out.println("Connecting to Database............");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqljdbc","root","vish8797");
            
            //Execute a query
            stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);//scrollable resultset
            rs=stmt.executeQuery("Select * from skills");
            
            rs.afterLast();
            while(rs.previous()) 
            {
                System.out.println(rs.getInt(1)+" "+rs.getString(2));
                
            }
            System.out.println("****************");
            //move the cursor to 3rd record
            rs.absolute(3);
            System.out.println(rs.getInt(1)+" "+rs.getString(2));
            System.out.println("****************");
        
            //move the cursor to 2nd record using relative()
            rs.absolute(-1);
            System.out.println(rs.getInt(1)+" "+rs.getString(2));
            System.out.println("****************");
            
            int i=rs.getRow();
            System.out.println("current position="+i);
            
            rs.close();
            stmt.close();
            con.close();
        }
        
        catch (Exception e)
        {
            System.out.println(e);
        }

 

    }

 

}