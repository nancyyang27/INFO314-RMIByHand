import java.io.*;
import java.net.*;
import java.util.*;
// import Java.RemoteMethod;
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(10314);
            while (true) {
                Socket clientSocket = server.accept();
                ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                RemoteMethod obj = (RemoteMethod) ois.readObject();
                if (obj.getMethodName().equals("add")) {
                    int lhs = (Integer) obj.getArguments()[0];
                    int rhs = (Integer) obj.getArguments()[1];
                    Integer result = Integer.valueOf(add(lhs, rhs));
                    ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
                    oos.writeObject(result);
                    oos.flush();
                    ois.close();
                    clientSocket.close();
                } else if (obj.getMethodName().equals("echo")) {
                    String result = echo((String) obj.getArguments()[0]);
                    ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
                    oos.writeObject(result);
                    oos.flush();
                    ois.close();
                    clientSocket.close();
                } else if (obj.getMethodName().equals("divide")) {
                    int num = (Integer) obj.getArguments()[0];
                    int denom = (Integer) obj.getArguments()[1];
                    Integer result = Integer.valueOf(divide(num, denom));
                    ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
                    oos.writeObject(result);
                    oos.flush();
                    ois.close();
                    clientSocket.close();
                }
                
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
    // Do not modify any code below tihs line
    // --------------------------------------
    public static String echo(String message) { 
        return "You said " + message + "!";
    }
    public static int add(int lhs, int rhs) {
        return lhs + rhs;
    }
    public static int divide(int num, int denom) {
        if (denom == 0)
            throw new ArithmeticException();

        return num / denom;
    }
}