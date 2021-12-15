import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import server_18070006043.Encrypt;

public class Server_18070006043 {
    public static void main(String[] args) {

        ServerSocket server = null;
        Socket client;

        // Default port number we are going to use
        int port_number = 1234;
        if (args.length >= 1) {
            port_number = Integer.parseInt(args[0]);
        }

        // Create server side socket
        try {
            server = new ServerSocket(port_number);
        } catch (IOException ie) {
            System.out.println("Cannot open socket." + ie);
            System.exit(1);
        }
        System.out.println("Server socket is created " + server);

        // Wait for the data from the client and reply
        while (true) {
            try {
                // Listens for a connection to be made to
                // this socket and accepts it. The method blocks until
                // a connection is made.
                System.out.println("waiting for connect request...");
                client = server.accept();

                System.out.println("Connect request is accepted...");
                String clientHost = client.getInetAddress().getHostAddress();
                int clientPort = client.getPort();
                System.out.println("Client host = " + clientHost + " Client port = " + clientPort);

                // Read data from the client
                InputStream clientIn = client.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(clientIn));
                String msgFromClient = br.readLine();
                System.out.println("Message received from client = " + msgFromClient);

                // Send response to the client
                if (msgFromClient != null && !msgFromClient.equalsIgnoreCase("bye")) {
                    
                    String dcrypted = Encrypt.decrypt(msgFromClient, Encrypt.findKey(msgFromClient));
                    

                

                    OutputStream clientOut = client.getOutputStream();
                    PrintWriter pw = new PrintWriter(clientOut, true);
                    String ansMsg = "Your answer is, " + dcrypted;
                    pw.println(dcrypted);
                }

                // Close sockets
                if (msgFromClient != null && msgFromClient.equalsIgnoreCase("bye")) {
                    server.close();
                    client.close();
                    break;
                }
            } catch (IOException ie) {

            }

        }
    }
}
