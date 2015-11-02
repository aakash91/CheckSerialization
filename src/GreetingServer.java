import com.singh.aakash.dbconnector.JDBCConnect;
import com.singh.aakash.dbconnector.Product;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public class GreetingServer extends Thread
{
    private ServerSocket serverSocket;

    Product product;
    List<String> adString;
    JDBCConnect jdbcConnect;
    List<String> recieved;
    public GreetingServer(int port) throws IOException
    {
       // this.product=product;
        serverSocket = new ServerSocket(port);
        adString=new ArrayList<String>();
        recieved=new ArrayList<String>();
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
                Object o=in.readObject();


                if(o instanceof Product) {
                    System.out.print("yo");
                    product = (Product)o;

                    System.out.println(product.toString());



                    jdbcConnect = new JDBCConnect(product);
                }
                if(o instanceof List<?>){
//                    adString=(ArrayList<String>) o;
//                    String placeId=adString.get(0);
//                    String ads=adString.get(1);
//                    String shop=adString.get(2);
//                    jdbcConnect=new JDBCConnect(placeId,ads,shop);
                    recieved=(ArrayList<String>) o;
                    if(recieved.get(recieved.size()-1).equals("ads")){
                        String placeId=recieved.get(0);
                    String ads=recieved.get(1);
                    String shop=recieved.get(2);
                    jdbcConnect=new JDBCConnect(placeId,ads,shop);
                    }
                    else if(recieved.get(recieved.size()-1).equals("cat")){
                        String placeId=recieved.get(0);
                        String category=recieved.get(1);
                    jdbcConnect =new JDBCConnect(placeId,category);
                    }

                }

                server.close();
                in.close();

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
        int port = 3001;
        try
        {
//            Product product=new Product("mobile","samsung","yo","100");
            Thread t = new GreetingServer(port);
            t.start();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}

