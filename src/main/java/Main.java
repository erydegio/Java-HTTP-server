
public class Main {
    public static void main( String[] args ) throws Exception {
        HTTPServer server = new HTTPServer(8080);
        server.startServer();

        //shut down the server after one minute
        Thread.sleep( 600000 );
        server.stopServer();
        System.out.println("Server closed...");

    }
}
