import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// Multithreaded HTTPServer

public class HTTPServer implements Runnable {

    private final Integer port;
    private Socket        socket = null;
    private ServerSocket  server = null;
    //String threadName = Thread.currentThread().getName();
    //private boolean       running = false;

    public HTTPServer(Integer port) {
        this.port = port;
    }

    public void startServer() throws IOException {
        // start listening for incoming connections on port 8080
        server = new ServerSocket(port);
        System.out.println("Server listening on port: " + port);
        //running = true;
    }

    public void stopServer() throws IOException {
        //running = false;
        System.out.println("Closing the Server...");
        //server.close();
        Thread.currentThread().interrupt();
    }

    @Override
    public void run() {

        // main
        try {
            startServer();

        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) try {

            socket = server.accept();
            new Thread(new HTTPRequestHandler(socket)).start();

        } catch (IOException e) {
           e.printStackTrace();
        }
    }

}
