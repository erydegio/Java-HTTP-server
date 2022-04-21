import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer
{

    private Integer         port;
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private boolean         running = false;
    private BufferedReader  in       =  null;
    private PrintWriter     out       =  null;



    public TCPServer(Integer port)
    {
        this.port = port;
    }

    public void startServer() throws IOException
    {
        try
        {
            server = new ServerSocket(port);

            // -- thread should start ----
            running = true;

            while (running)
            {
                try
                {
                    System.out.println("Server listening on port " + port);
                    socket = server.accept();

                    System.out.println("Connected by " + socket);

                    // request/response handler
                    // echoes the data received
                    handleRequest();
                }
                finally
                {
                    // stop connection
                    socket.close();
                    System.out.println("Connection closed");
                }

                // stop listening
                running = false;
            }
        }
        finally
        {
            // close server
            server.close();
            System.out.println("Server closed");
        }

    }

    public void handleRequest() throws IOException
    {
        // read all the data, and send a response
        String data = receiveData();
        HTTPRequest req = new HTTPRequest(data);
        System.out.println();
        String response = makeResponse(data.toString());
        sendData(response);
    }

    public String receiveData() throws IOException
    {
        // read all the data received from the client and print them after inserting an empty line
        in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        String line = null;
        StringBuilder data = new StringBuilder();

        while ((line = in.readLine()) != null)
        {
            System.out.println(line);
            data.append(line).append("\n");
            if (line.isEmpty()) break;
        }
        return data.toString();
    }

    // handle incoming data and return a response
    // echoes the data sent by the client
    public String makeResponse( String data)
    {
        return data;
    }

   public void sendData(String response) throws IOException
   {
       OutputStream output = socket.getOutputStream();
       out = new PrintWriter(output, true);
       out.println(response);
   }

    // method to stop the server


}
