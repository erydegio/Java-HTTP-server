
import java.lang.reflect.Method;


public class HTTPServer extends TCPServer
{
    public HTTPServer(Integer port)
    {
        super(port);
    }

    // REQUESTS HANDLING
    @Override
    public String handleRequest(String data)
    {
        HTTPRequest req = new HTTPRequest(data);
        HTTPResponse res = new HTTPResponse();

        //make responses
        try
        {
            Method m = res.getClass().getMethod("handle" + "_" + req.getMeth());
            Object resData = m.invoke(req);
            res.setResponse((String) resData);

        } catch (Exception e)
        {
            res.setResponse(res.handleUnknownMethods());
        }

        return res.getResponse();

    }


}
