
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class HTTPServer extends Thread {

    private final Integer port;
    private Socket socket = null;
    private ServerSocket server = null;

    public static void main( String[] args ) throws Exception {

        // listen for incoming connections on port 8080
        HTTPServer server = new HTTPServer(8080);
        server.startServer();

        //shutdown the server after one minute
        // TO DO: shutdown the server with CTRL+C
        Thread.sleep( 60000 );
        server.stopServer();

    }


    public HTTPServer(Integer port) {
        this.port = port;
    }

    public void startServer() throws IOException {
        server = new ServerSocket(port);
        this.start();
    }


    @Override
    public void run() {

        System.out.println("Server listening on port " + port);

        while (true) try {
            // Accept the client connection, create a socket
            socket = server.accept();

            // HTTPRequestHandler Runnable class passed in a Thread constructor to process multiple requests.
            HTTPRequestHandler newTask = new HTTPRequestHandler(socket);
            Thread newThread = new Thread(newTask);
            newThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopServer() {

        System.out.println("Closing the Server...");
        System.exit( 0 );

    }




}
