import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.Socket;

// Handle the communication client-server respecting the HTTP protocol
// It handles only GET and returns 501  "Not implemented" for every other type of request

public class HTTPRequestHandler implements Runnable {

    protected Socket socket;
    protected BufferedReader in;
    protected PrintWriter out;
    protected String line;
    String threadName = Thread.currentThread().getName();


    public HTTPRequestHandler(Socket socket) throws IOException {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Connected by " + socket);
            System.out.println(threadName + " handling the request");

            //String line;
            StringBuilder data = new StringBuilder();

            //read data till is sent a blank line
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                data.append(line).append("\n");
                if (line.isEmpty()) break;
            }

            // Parse the data received and create a request object
            HTTPRequest req = new HTTPRequest(data.toString());

            // Create a Response object based on the request
            HTTPResponse res = new HTTPResponse();

            // Set response type
            try {

                // Check if the method of the request is implemented in the response object, if not implemented an Exception is raised during the search
                Method m = res.getClass().getMethod("handle" + "_" + req.getStatusCode());
                Object resData = m.invoke(res);
                res.setResponse((String) resData);

            } catch (Exception e) {
                res.setResponse(res.handleUnknownMethods());
            }

            out.println(res.getResponse());
            System.out.println("response sent");
            System.out.println(res.getResponse());

            socket.close();
            System.out.println("Socket closed");


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
