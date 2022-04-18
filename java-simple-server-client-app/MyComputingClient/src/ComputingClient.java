/* CLIENT */
import java.io.*;
import java.net.*;
public class ComputingClient {

    public static void main(String args[]) {

        Socket client = null;

        // Default port number we are going to use
        int portnumber = 1234;
        if (args.length >= 1){
            portnumber = Integer.parseInt(args[0]);
        }

        for (int i=0; i <10; i++) {
            try {
                String msg1 = "";
                String msg2 = "";

                // Create a client socket
                client = new Socket(InetAddress.getLocalHost(), portnumber);
                System.out.println("Client socket is created " + client);

                // Create an output stream of the client socket
                OutputStream clientOut = client.getOutputStream();
                PrintWriter pw = new PrintWriter(clientOut, true);

                // Create an input stream of the client socket
                InputStream clientIn = client.getInputStream();
                BufferedReader br = new BufferedReader(new
                        InputStreamReader(clientIn));

                // Create BufferedReader for a standard input
                BufferedReader stdIn = new BufferedReader(new
                        InputStreamReader(System.in));

                System.out.println("Enter number number. ");

                // Read data from standard input device and write it
                // to the output stream of the client socket.
                msg1 = stdIn.readLine().trim();
                pw.println(msg1);
                msg2 = stdIn.readLine().trim();
                pw.println(msg2);

                // Read data from the input stream of the client socket.
                System.out.println("Sum of These Numbers = " + br.readLine());

                pw.close();
                br.close();
                client.close();

                // Stop the operation
                if (msg1.equalsIgnoreCase("Bye")||msg2.equalsIgnoreCase("Bye") ) {
                    break;
                }

            } catch (IOException ie) {
                System.out.println("I/O error " + ie);
            }
        }
    }
}