
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
    private List<String> AdList;
    //  Database credentials
    static final String USER = "root";
    static final String PASS = "krishna6";
    //private Product product;
    private List<String> placeIdList;
    private List<String> AdsCustList;
    private List<String> afterLogin;

    public List<String> getAfterLogin() {
        return afterLogin;
    }

    public List<String> getAdsCustList() {
        return AdsCustList;
    }

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
            //String sql="select DISTINCT category from Product "+"where placeId= '"+placeId+"'"; //tobeback
            String idPlace=placeId.replace("-","");
            String sql="select DISTINCT category from Category_"+idPlace;
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

    public JDBCConnect2(String placeId, String ab, String abc) {
        //this.product=product;
        Connection conn = null;
        Statement stmt = null;
        this.placeId=placeId;
        AdList=new ArrayList<String>();
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
            String sql="select matter from Ads "+"where placeId= '"+placeId+"'";
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);
//
            while(rs.next()) {
                // System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5));
                System.out.println(rs.getString(1));
                AdList.add(rs.getString(1));

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
            //String sql="select name, description,price from Product where category = '" + category +"' and " + "placeId= '" + placeId +"'";
            String idPlace=placeId.replace("-","");
            String sql="select name, description,price from Product_"+idPlace +" where category = '" + category + "'" ;
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


    public JDBCConnect2(List<String> placeIdList) {
        //this.product=product;
        Connection conn = null;
        Statement stmt = null;
        this.placeIdList=placeIdList;
        AdsCustList=new ArrayList<String>();
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
            StringBuilder sb=new StringBuilder();
            sb.append("select matter,shop from Ads where placeId in ('");
            for(String s:placeIdList){
                System.out.println(s);
                sb.append(s+"','");
            }
            sb.append(")");

            String sql=sb.toString().replace(",')",")");

            //String sql="select matter,shop from Ads "+"where placeId= '"+placeId+"'";

            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);
//
            while(rs.next()) {
                // System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5));
                StringBuffer sbf=new StringBuffer();
                sbf.append(rs.getString("matter"));
                sbf.append(",");
                sbf.append(rs.getString("shop"));
                System.out.println(sbf.toString());
                AdsCustList.add(sbf.toString());

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

    public JDBCConnect2(String username, String password, String abc, String abcd) {
        //this.product=product;
        Connection conn = null;
        Statement stmt = null;
        afterLogin=new ArrayList<String>();

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
            String sql="select count(*) from Business where username='"+username +"' and pwd ='"+password +"'" ;
            System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);
//
//            while(rs.next()) {
//                // System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5));
//                System.out.println(rs.getString(1));
//                AdList.add(rs.getString(1));
//
//            }
            int count=0;
            while(rs.next()) {
                count = rs.getInt(1);
                System.out.println(count);
            }


            if(count==1){
                String sql1="select username,placeid,pwd from Business where username='"+username +"' and pwd ='"+password +"'" ;
                ResultSet rs1=stmt.executeQuery(sql1);

                while(rs1.next()) {
                    afterLogin.add(rs1.getString(1));
                    afterLogin.add(rs1.getString(2));
                    afterLogin.add(rs1.getString(3));
                }

            }else{
                afterLogin.add("invalid credentials");
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

    public List<String> getAdList() {
        return AdList;
    }
}//end JDBCExample
