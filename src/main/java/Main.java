public class Main {
    public static void main( String[] args ) throws Exception {

        HTTPServer server = new HTTPServer(8080);
        new Thread(server).start();

        //shutdown the server after one minute
        Thread.sleep( 60000 );
        server.stopServer();
    }
}
