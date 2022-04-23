import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class TCPServer extends Thread {

    protected final Integer port;
    protected Socket socket = null;
    protected ServerSocket server = null;
    boolean running = false;


    public TCPServer(Integer port) {
        this.port = port;
    }

    public void startServer() throws IOException {

        server = new ServerSocket(port);
        this.start();
    }

    @Override
    public void run() {
        running = true;

        while (running) {
            try {
                System.out.println("Server listening on port " + port);
                socket = server.accept();

                // pass the socket into another thread so the server can be scalable
                RequestHandler newTask = new RequestHandler(socket); //interface
                Thread newThread = new Thread(newTask); // thread class
                newThread.start();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void stopServer()
    {
        running = false;
        this.interrupt();
    }
}




