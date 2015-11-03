package com.singh.aakash.dbconnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by aakash on 02-11-2015.
 */
public class RegisterANormalUser {


    public void add(String username,String emailId,String name,String mobile,String pwd){
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost:3306/mydata";
        final String USER = "root";
        final String PASS = "krishna6";
        Connection conn = null;
        Statement stmt = null;


        try{
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            stmt=conn.createStatement();
//            String sql = "INSERT INTO Products " + "VALUES "+"('" + product.getCategory()+"', '"+ product.getName()+"', '"+product.getDescription()+"', '"+product.getPrice()+"')";
//            System.out.println(sql);
//            stmt.executeUpdate(sql);
            ///String idPlace=placeId.replace("-","");
            String emailMod=emailId.replaceAll("\\W", "");
            String sql="CREATE TABLE profile_"+emailMod+"("+"username varchar(255),\n" +
                    "emailid varchar(255),\n" +
                    "name varchar(255),\n" +
                    "mobile varchar(255),\n" +
                    "pwd varchar(255)"+")";

            String sql1="CREATE TABLE Address_"+emailMod+"("+"address varchar(255)" + ")";

            String sql2="CREATE TABLE Order_"+emailMod+"("+"order_detail varchar(255), order_id varchar(255)" + ")";

            String sql3="Insert into profile_"+emailMod+" Values ('"+username+"','"+emailId+"','"+name+"','"+mobile+"','"+pwd+   "')";

            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql2);
            stmt.executeUpdate(sql3);


        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }


        try {
            conn.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }


    }


}
