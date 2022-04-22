import java.io.IOException;

public class Main {
    public static void main( String[] args ) throws Exception {
        HTTPServer server = new HTTPServer(8080);
        server.startServer();

    }
}
