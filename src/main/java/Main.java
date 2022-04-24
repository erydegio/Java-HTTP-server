
public class Main {
    public static void main( String[] args ) throws Exception {
        HTTPServer server = new HTTPServer(8080);
        server.startServer();

        //shut down the server after one minute
        // TO DO: shut down the server with CTRL+C
        Thread.sleep( 60000 );
        server.stopServer();
        System.out.println("Server closed.....");

    }
}
