package com.singh.aakash.dbconnector;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public class ServerForCustomers1 extends Thread
{
    private ServerSocket serverSocket;

    Product product;
    String choice;
    String placeId;
    List<String> recievedList;
    List<String> categories;
    List<String> AdList;

    public ServerForCustomers1(int port) throws IOException
    {
        // this.product=product;
        serverSocket = new ServerSocket(port);
        categories=new ArrayList<String>();
        recievedList=new ArrayList<String>();
        AdList=new ArrayList<String>();
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
                ObjectInputStream in = new ObjectInputStream(server.getInputStream());
                //product=(Product)in.readObject();

// System.out.println(product.toString());
                recievedList= (ArrayList<String>)in.readObject();
                placeId=recievedList.get(0);
                choice=recievedList.get(1);
                System.out.println(choice);
                System.out.println(placeId);
                //in.close();
                ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());

                if (choice.equals("category"))
                {
                    JDBCConnect2 jdbcConnect2=new JDBCConnect2(placeId);
                    categories=jdbcConnect2.getCategories();
                    for(String s:categories){
                        System.out.println(s);
                    }
                    out.writeObject(categories);
                    out.flush();

                }
                else if (choice.equals("Ads"))
                {
                    JDBCConnect2 jdbcConnect2=new JDBCConnect2(placeId,"a","b");
                    AdList=jdbcConnect2.getAdList();
                    for(String s:AdList){
                        System.out.println(s);
                    }
                    out.writeObject(AdList);
                    out.flush();

                }

                else
                {
                    JDBCConnect2 jdbcConnect2=new JDBCConnect2(choice,placeId);

                    //categories=jdbcConnect1.getCategories();
                    for(String s:jdbcConnect2.getListOfProducts()){
                        System.out.println(s);
                    }
                    out.writeObject(jdbcConnect2.getListOfProducts());
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
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String [] args)
    {
        int port = 3002;
        try
        {
            //            Product product=new Product("mobile","samsung","yo","100");
            Thread t = new ServerForCustomers1(port);
            t.start();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}

