import java.net.*;
import java.io.*;

public class ComputingServer {
   static int sum;
    public static String summation(int a,int b){
        sum = a+b;
        return String.valueOf(sum);
    }



    public static void main(String [] args) {

        ServerSocket server = null;
        Socket client;

        // Default port number we are going to use
        int portnumber = 1234;
        if (args.length >= 1){
            portnumber = Integer.parseInt(args[0]);
        }

        // Create Server side socket
        try {
            server = new ServerSocket(portnumber);
        } catch (IOException ie) {
            System.out.println("Cannot open socket." + ie);
            System.exit(1);
        }
        System.out.println("ServerSocket is created " + server);

        // Wait for the data from the client and reply
        while(true) {

            try {

                // Listens for a connection to be made to
                // this socket and accepts it. The method blocks until
                // a connection is made
                System.out.println("Waiting for connect request...");
                client = server.accept();

                System.out.println("Connect request is accepted...");
                String clientHost = client.getInetAddress().getHostAddress();
                int clientPort = client.getPort();
                System.out.println("Client host = " + clientHost + " Client port = " + clientPort);

                // Read data from the client
                InputStream clientIn = client.getInputStream();
                BufferedReader br = new BufferedReader(new
                        InputStreamReader(clientIn));
                String msgFromClient1 = br.readLine();
                String msgFromClient2 = br.readLine();

                System.out.println("Message received from client = " + msgFromClient1);
                System.out.println("Message received from client = " + msgFromClient2);

                // Send response to the client
                if (msgFromClient1 != null && !msgFromClient1.equalsIgnoreCase("bye")) {
                    OutputStream clientOut = client.getOutputStream();
                    PrintWriter pw = new PrintWriter(clientOut, true);
                    String ansMsg = summation(Integer.parseInt(msgFromClient1),Integer.parseInt(msgFromClient2));
                    pw.println(ansMsg);
                }

                // Close sockets
                if (msgFromClient1 != null && msgFromClient1.equalsIgnoreCase("bye")||msgFromClient2 != null && msgFromClient2.equalsIgnoreCase("bye")) {
                    server.close();
                    client.close();
                    break;
                }

            } catch (IOException ie) {
            }
        }
    }
}
