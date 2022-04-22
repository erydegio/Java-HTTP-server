import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class HTTPServer extends TCPServer
{

    private static final String BODY = "<html><head><title>Hola</title></head><body><p>Hola!!!</p></body></html>";
    private final HashMap<Integer, String> statusCodes = new HashMap<>();
    private final HashMap<String, String> resHeaders = new HashMap<>();


    public HTTPServer(Integer port)
    {
        super(port);
        setStatusCodes();
        setResHeaders();
    }

    public void setStatusCodes()
    {
        statusCodes.put(200, "OK\r\n");
        statusCodes.put(501, "Not Implemented\r\n");
    }

    public void setResHeaders()
    {
        resHeaders.put("Server: ", "JavaServer\r\n");
        resHeaders.put("Content-Type: ", "text/html\r\n");
        resHeaders.put("Content-Length: ", BODY.length() + "\r\n");
    }

    // RESPONSE HANDLING
    @Override
    public String makeResponse(String data)
    {
        String resLine = responseLine(200);
        String headers = responseHeaders(null);
        String blankLine = "\r\n";

        return resLine + headers + blankLine + BODY;
    }

    public String responseLine(Integer statusCode)
    {
        String reason = statusCodes.get(statusCode);
        return "HTTP/1.1 " + statusCode + " " + reason;
    }

    public String responseHeaders(HashMap<String,String> extraHeaders)
    {
        HashMap<String, String> resHeadersShallowCopy = new HashMap<String, String>(resHeaders);

        // add extra-headers to current headers
        if (extraHeaders != null)
            resHeadersShallowCopy.putAll(extraHeaders);

        StringBuilder headers = new StringBuilder();
        for (String el : resHeadersShallowCopy.keySet())
        {
            headers.append(el).append(resHeadersShallowCopy.get(el));
        }

        return headers.toString();
    }

    // REQUESTS HANDLING
    @Override
    public String handleRequest(String data) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        HTTPRequest req =
                new HTTPRequest(data);

        Method m = req.getClass().getMethod("handle" + "_" + req.getMeth());
        var res = m.invoke(req);

        return data;
    }

    public void handle_GET()
    {
        System.out.println("triggered handle_GET");
    }






}
