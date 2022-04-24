
import java.io.*;
import java.net.Socket;

public class RequestHandler implements Runnable {

    protected Socket socket;
    protected BufferedReader in;
    protected PrintWriter out;
    protected String line;


    public RequestHandler(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {

        System.out.println("Connected by " + socket.getInetAddress());

        try {

            // If the client sends an empty line RequestHandler closes the socket.
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                out.println(line);
                if (line.isEmpty()) break;
            }

            socket.close();
            System.out.println("Socket closed");


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
