import java.io.IOException;

public class HTTPServer extends TCPServer {

    public HTTPServer(Integer port) {
        super(port);
    }

    @Override
    public void run() {

        while (running) try {

            socket = server.accept();

            new Thread(new HTTPRequestHandler(socket)).start();

        } catch (IOException e) {
            if (!running) {
                System.out.println("Bye..");
                return;
            }
        }
    }



}
