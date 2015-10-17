import com.singh.aakash.dbconnector.Product;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class GreetingServer extends Thread
{
    private ServerSocket serverSocket;

    Product product;

    public GreetingServer(int port) throws IOException
    {
       // this.product=product;
        serverSocket = new ServerSocket(port);

    }

    public void run()
    {
        while(true)
        {
            try
            {
                System.out.println("Waiting for client on port " +
                        serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();
                System.out.println("Just connected to "
                        + server.getRemoteSocketAddress());

                //Thread.sleep(1000);
                ObjectInputStream in =
                        new ObjectInputStream(server.getInputStream());
                product=(Product)in.readObject();

                System.out.println(product.toString());



                //System.out.println(in.readUTF());
                //ObjectOutputStream out =
                //        new ObjectOutputStream(server.getOutputStream());
                //out.writeObject(product);
                //out.flush();
                server.close();
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
        int port = 3000;
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

