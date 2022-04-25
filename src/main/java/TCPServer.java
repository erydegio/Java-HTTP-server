import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class TCPServer implements Runnable {

    protected final Integer port;
    protected Socket        socket = null;
    protected ServerSocket  server = null;
    protected boolean       running = false;


    public TCPServer(Integer port) {

        this.port = port;
        try {
            // start listening for incoming connections on port 8080
            startServer();
            System.out.println("Server listening on port " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startServer() throws IOException {
        server = new ServerSocket(port);
        running = true;
    }


    @Override
    public void run() {

        while (running) try {

            // Accept the client connection, create a socket
            socket = server.accept();

            // HTTPRequestHandler Runnable class passed in a Thread constructor to process multiple requests.
            new Thread(new TCPRequestHandler(socket)).start();

            // if the server is already closed is raised an IOException
        } catch (IOException e) {
            if (!running) {
                System.out.println("Bye..");
                return;
            }
        }
    }


    public void stopServer() throws IOException {
       running = false;
       System.out.println("Closing the Server...");
       server.close();
    }

}




