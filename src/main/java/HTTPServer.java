import java.io.IOException;

public class HTTPServer extends TCPServer {

    public HTTPServer(Integer port) {
        super(port);
    }

    @Override
    public void run() {

        while (running) try {

            // Accept the client connection, create a socket
            socket = server.accept();

            // HTTPRequestHandler Runnable class passed in a Thread constructor to process multiple requests.
            new Thread(new HTTPRequestHandler(socket)).start();

          // if the server is already closed is raised an IOException
        } catch (IOException e) {
            if (!running) {
                System.out.println("Bye..");
                return;
            }
        }
    }



}
