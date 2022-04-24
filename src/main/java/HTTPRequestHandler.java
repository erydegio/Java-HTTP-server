import java.io.IOException;
import java.lang.reflect.Method;
import java.net.Socket;

public class HTTPRequestHandler extends TCPRequestHandler {

    public HTTPRequestHandler(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    public void run() {

        try {
            System.out.println(Thread.currentThread() + " request handling");

            //String line;
            StringBuilder data = new StringBuilder();

            //read data till is sent a blank line
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                data.append(line).append("\n");
                if (line.isEmpty()) break;
            }

            HTTPRequest req = new HTTPRequest(data.toString());
            HTTPResponse res = new HTTPResponse();

            //make responses
            try {
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
