package com.singh.aakash.dbconnector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//STEP 1. Import required packages


    public class JDBCConnect {
        // JDBC driver name and database URL
        static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        static final String DB_URL = "jdbc:mysql://localhost:3306/mydata";

        //  Database credentials
        static final String USER = "root";
        static final String PASS = "krishna6";
        private Product product;

        public JDBCConnect(Product product) {
            this.product=product;
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
                String sql = "INSERT INTO Product " + "VALUES "+"('" + product.getPlaceId()+"', '"+ product.getCategory()+"', '"+ product.getName()+"', '"+product.getDescription()+"', '"+product.getPrice()+"')";
                System.out.println(sql);
                stmt.executeUpdate(sql);
//            ResultSet rs=stmt.executeQuery("select * from Persons");
//
//            while(rs.next())
//                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3) + " "+ rs.getString(4)+" "+ rs.getString(5) );



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
        }


        public JDBCConnect(String placeId,String ad,String shop) {
            this.product=product;
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
                String sql = "INSERT INTO ads " + "VALUES "+"('" + placeId+"', '"+ ad+"', '"+shop+"')";
                System.out.println(sql);
                stmt.executeUpdate(sql);
//            ResultSet rs=stmt.executeQuery("select * from Persons");
//
//            while(rs.next())
//                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3) + " "+ rs.getString(4)+" "+ rs.getString(5) );



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
        }
}//end JDBCExample