package com.singh.aakash.dbconnector;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public class ServerForCustomers extends Thread
 {
 private ServerSocket serverSocket;

 Product product;
     String choice;
     List<String> categories;

 public ServerForCustomers(int port) throws IOException
 {
 // this.product=product;
 serverSocket = new ServerSocket(port);
     categories=new ArrayList<String>();
 }

 public void run()
 {
 while(true)
 {
 try
 {
 System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
 Socket server = serverSocket.accept();
 System.out.println("Just connected to " + server.getRemoteSocketAddress());
 DataInputStream in = new DataInputStream(server.getInputStream());
 //product=(Product)in.readObject();

// System.out.println(product.toString());
     choice= in.readUTF();
     System.out.println(choice);
     //in.close();
     ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());

     if (choice.equals("category"))
     {
         JDBCConnect1 jdbcConnect1=new JDBCConnect1();
         categories=jdbcConnect1.getCategories();
     for(String s:categories){
         System.out.println(s);
     }
         out.writeObject(categories);
         out.flush();

     }

 server.close();

     in.close();
     out.close();

 //JDBCConnect jdbcConnect=new JDBCConnect(product);


 }catch(SocketTimeoutException s)
 {
 System.out.println("Socket timed out!");
 break;
 }catch(IOException e)
 {
 e.printStackTrace();
 break;
 }
 }
 }

 public static void main(String [] args)
 {
 int port = 3002;
 try
 {
 //            Product product=new Product("mobile","samsung","yo","100");
 Thread t = new ServerForCustomers(port);
 t.start();
 }catch(IOException e)
 {
 e.printStackTrace();
 }
 }
 }

