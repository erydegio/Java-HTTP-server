import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


//TCPServer extends Thread to handle the blocking accept() call.
public class TCPServer extends Thread {

    protected final Integer port;
    protected Socket socket = null;
    protected ServerSocket server = null;
   // boolean running = false;

    public static void main( String[] args ) throws Exception {

        // listen for incoming connections on port 8080
        TCPServer server = new TCPServer(8080);
        server.startServer();

        //shutdown the server after one minute
        Thread.sleep( 60000 );
        server.stopServer();
    }


    public TCPServer(Integer port) {
        this.port = port;
    }

    public void startServer() throws IOException {

        server = new ServerSocket(port);
        this.start();
    }


    @Override
    public void run() {
        //running = true;
        System.out.println("Server listening on port " + port);

        while (true) {
            try {
                socket = server.accept();

                // pass the socket into another thread so the server can be scalable
                TCPRequestHandler newTask = new TCPRequestHandler(socket);
                Thread newThread = new Thread(newTask);
                newThread.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopServer() {

        //this.interrupt();
        System.out.println("Server closed.....");
        System.exit( 0 );

    }
}




