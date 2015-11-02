package com.singh.aakash.dbconnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by aakash on 02-11-2015.
 */
public class RegisterABusiness {

    public static void main(String[] args) {
        RegisterABusiness registerABusiness=new RegisterABusiness();
        registerABusiness.add("ChIJBbLl2j_BwjsRG5iLmvhVGi8");
    }

    private void add(String placeId){
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
            String idPlace=placeId.replace("-","");
            String sql="CREATE TABLE Product_"+idPlace+"("+"category varchar(255),\n" +
                    "name varchar(255),\n" +
                    "description varchar(255),\n" +
                    "price varchar(255)"+")";

            String sql1="CREATE TABLE Category_"+idPlace+"("+"category varchar(255) unique" + ")";



            System.out.println(sql);
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql1);

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
