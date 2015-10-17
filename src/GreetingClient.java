import java.io.*;
import java.net.Socket;

public class GreetingClient
{
    public static void main(String [] args)
    {
        String serverName = args[0];
        int port = Integer.parseInt(args[1]);
        Product product;
        try
        {
            System.out.println("Connecting to " + serverName +
                    " on port " + port);
            Socket client = new Socket(serverName, port);
            System.out.println("Just connected to "
                    + client.getRemoteSocketAddress());
            //OutputStream outToServer = client.getOutputStream();
            //ObjectOutputStream out = new ObjectOutputStream(outToServer);
            //out.writeUTF("Hello from "
            //        + client.getLocalSocketAddress());
            //out.writeUTF("what's up");
            InputStream inFromServer = client.getInputStream();
            ObjectInputStream in =
                    new ObjectInputStream(inFromServer);
            product=(Product)in.readObject();
            in.close();
            System.out.println(product.toString());
            //System.out.println("Server says " + in.readObject());
            client.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}