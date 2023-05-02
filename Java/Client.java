import java.io.*;
import java.net.*;
import java.util.*;
public class Client {

    /**
     * This method name and parameters must remain as-is
     */
    public static int add(int lhs, int rhs) {
        // connect to server
        //create an isntance of the RemoteMethod
        // Remote Method add = new RemoteMethod("add", new Objects[](lhs, rhs))
        // ObjectOutputStream to serialie the add instance
        // OutputStream os = socket.get
        try {
            Socket clientSocket = new Socket("localhost", 10314);
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            RemoteMethod add = new RemoteMethod("add", new Object[]{lhs, rhs});
            oos.writeObject(add);
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            Integer result = (Integer) ois.readObject();
            return result.intValue();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return -1;
        } 
    }
    /**
     * This method name and parameters must remain as-is
     */
    public static int divide(int num, int denom) {
    try {
            Socket clientSocket = new Socket("localhost", 10314);
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            RemoteMethod divide = new RemoteMethod("divide", new Object[] { num, denom });
            oos.writeObject(divide);
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            Integer result = (Integer) ois.readObject();
            return result.intValue();
        } catch (IOException | ClassNotFoundException e) {
            // Handle the exception here
            e.printStackTrace();
            return -1; // or any other value that represents an error
        }
    }
    
    /**
     * This method name and parameters must remain as-is
     */
    public static String echo(String message) {
        try {
            Socket clientSocket = new Socket("localhost", 10314);
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            RemoteMethod echo = new RemoteMethod("echo", new Object[] { message });
            oos.writeObject(echo);
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            String result = (String) ois.readObject();
            return result;
        } catch (IOException | ClassNotFoundException e) {
            // Handle the exception here
            e.printStackTrace();
            return null; // or any other value that represents an error
        }
    }

    // Do not modify any code below this line
    // --------------------------------------
    String server = "localhost";
    public static final int PORT = 10314;

    public static void main(String... args) {
        // All of the code below this line must be uncommented
        // to be successfully graded.
        System.out.print("Testing... ");

        if (add(2, 4) == 6)
            System.out.print(".");
        else
            System.out.print("X");

        try {
            divide(1, 0);
            System.out.print("X");
        }
        catch (ArithmeticException x) {
            System.out.print(".");
        }

        if (echo("Hello") == "You said Hello!")
            System.out.print(".");
        else
            System.out.print("X");
        
        System.out.println(" Finished");
    }
}