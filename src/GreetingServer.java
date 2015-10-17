import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class GreetingServer extends Thread
{
    private ServerSocket serverSocket;

    Product product;

    public GreetingServer(int port,Product product) throws IOException
    {
        this.product=product;
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
               // ObjectInputStream in =
                //        new ObjectInputStream(server.getInputStream());
                //System.out.println(in.readUTF());
                ObjectOutputStream out =
                        new ObjectOutputStream(server.getOutputStream());
                out.writeObject(product);
                out.flush();
                server.close();
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
        int port = Integer.parseInt(args[0]);
        try
        {
            Product product=new Product("mobile","samsung","yo","100");
            Thread t = new GreetingServer(port,product);
            t.start();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}

