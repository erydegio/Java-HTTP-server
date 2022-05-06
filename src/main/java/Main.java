public class Main {
    public static void main( String[] args ) throws Exception {

        HTTPServer server = new HTTPServer(8080);
        Thread t = new Thread(server);
        t.setDaemon(true);
        t.start();

        //shutdown the server after one minute
        Thread.sleep( 6000 );
        server.stopServer();
    }
}
