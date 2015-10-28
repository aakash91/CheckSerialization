
package com.singh.aakash.dbconnector;

//import com.mysql.jdbc.ResultSet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//STEP 1. Import required packages

public class JDBCConnect2 {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/mydata";
    private List<String> categories;
    private String category;
    private List<String> listOfProducts;
    private String placeId;
    //  Database credentials
    static final String USER = "root";
    static final String PASS = "krishna6";
    //private Product product;

    public JDBCConnect2(String placeId) {
        //this.product=product;
        Connection conn = null;
        Statement stmt = null;
        this.placeId=placeId;
        categories=new ArrayList<String>();
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
            String sql="select DISTINCT category from Product "+"where placeId= '"+placeId+"'";
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);
//
            while(rs.next()) {
                // System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5));
                System.out.println(rs.getString(1));
                categories.add(rs.getString(1));
            }


            conn.close();


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main


    public JDBCConnect2(String category,String placeId) {
        //this.product=product;
        this.category=category;
        Connection conn = null;
        Statement stmt = null;
        listOfProducts=new ArrayList<String>();
        //categories=new ArrayList<String>();
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
            String sql="select name, description,price from Product where category = '" + category +"' and " + "placeId= '" + placeId +"'";
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);

            while(rs.next()) {
                // System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5));
                //System.out.println(rs.getString(1));
                //categories.add(rs.getString(1));
                StringBuffer productDetails=new StringBuffer();
                productDetails.append(rs.getString("name"));
                productDetails.append(",");
                productDetails.append(rs.getString("description"));
                productDetails.append(",");
                productDetails.append(rs.getString("price"));
//                productDetails+(rs.getString("name"))+",";
//                productDetails+ (rs.getString("description"));
//                productDetails.add(rs.getString("price"));
                System.out.println(productDetails.toString());
                listOfProducts.add(productDetails.toString());
            }


            conn.close();


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main

    public List<String> getListOfProducts() {
        return listOfProducts;
    }

    public List<String> getCategories() {
        return categories;
    }
}//end JDBCExample