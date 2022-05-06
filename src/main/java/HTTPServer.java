import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Multithreaded HTTPServer

public class HTTPServer implements Runnable {

    private final Integer     port;
    private Socket            socket = null;
    private ServerSocket      server = null;
    protected ExecutorService threadPool = Executors.newFixedThreadPool(2);

    //String threadName = Thread.currentThread().getName();

    public HTTPServer(Integer port) {
        this.port = port;
    }

    public void startServer() throws IOException {
        // start listening for incoming connections on port 8080
        server = new ServerSocket(port);
        System.out.println("Server listening on port: " + port);
    }

    public void stopServer() {
        System.out.println("Closing the Server...");
        //server.close();
        threadPool.shutdown();
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
            threadPool.execute(new HTTPRequestHandler(socket));

        } catch (IOException e) {
           e.printStackTrace();
        }
    }

}
