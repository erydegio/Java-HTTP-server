import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class TCPServer
{

    private final Integer   port;
    private Socket          socket   = null;
    private ServerSocket    server   = null;


    public TCPServer(Integer port)
    {
        this.port = port;
    }

    public void startServer() throws Exception
    {
        try
        {
            server = new ServerSocket(port);

            // -- thread should start ----
            boolean running = true;

            while (running)
            {
                try
                {
                    System.out.println("Server listening on port " + port);
                    socket = server.accept();

                    System.out.println("Connected by " + socket);

                    // request/response handler
                    // echoes the data received
                    String data = receiveData(); //return data in string format from bytearray
                    String response = handleRequest(data); // from data string return a request after parsing it!! genera request
                    sendData(response); // send the response


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

    public String handleRequest(String data)
    {
        return data;
    }

    public String receiveData() throws IOException
    {
        // read all the data received from the client and return them in string format
        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        String line;
        StringBuilder data = new StringBuilder();

        while ((line = in.readLine()) != null)
        {
            System.out.println(line);
            data.append(line).append("\n");
            if (line.isEmpty()) break;
        }
        return data.toString();
    }



   public void sendData(String response) throws IOException
   {
       OutputStream output = socket.getOutputStream();
       PrintWriter out = new PrintWriter(output, true);
       out.println(response);
   }

    // method to stop the server


}
