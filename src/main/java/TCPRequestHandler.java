
import java.io.*;
import java.net.Socket;

// Superclass of HTTPRequestHandler: handle the communication between client and server
public class TCPRequestHandler implements Runnable {

    protected Socket socket;
    protected BufferedReader in;
    protected PrintWriter out;
    protected String line;


    public TCPRequestHandler(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        System.out.println("Connected by " + socket);
    }

    @Override
    public void run() {

        try {
            // Echoes the data (line) sent by the client
            // If the client sends an empty line RequestHandler closes the socket.
            out.println("Connected!!");
            while ((line = in.readLine()) != null) {
                System.out.println("Received: " + line + " from " + socket.getPort());
                out.println("Returned " + line + " from Server");
                if (line.isEmpty()) break;
            }

            socket.close();
            System.out.println("Socket closed");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
