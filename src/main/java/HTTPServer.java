
import java.io.IOException;



public class HTTPServer extends TCPServer {

    public HTTPServer(Integer port)
    {
        super(port);
    }

    // REQUESTS HANDLING
    @Override
    public void run() {
        running = true;

        while (running) {

            try {

                System.out.println("Server listening on port " + port);
                socket = server.accept();

                HTTPRequestHandler newTask = new HTTPRequestHandler(socket); //interface
                Thread newThread = new Thread(newTask); // thread class
                newThread.start();


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
