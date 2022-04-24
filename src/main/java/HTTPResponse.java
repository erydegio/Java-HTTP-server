import java.util.HashMap;

public class HTTPResponse {

    private final HashMap<String, String> resHeaders = new HashMap<>();
    private final HashMap<Integer, String> resStatusCodes = new HashMap<>();
    private static final java.util.Date date = new java.util.Date();
    private static final String BODY = "<html><head><title>Hello!</title></head><body><p>Hello!!!</p>" + date + "</body></html>";
    private String response;

    public HTTPResponse() {
        setResHeaders();
        setResStatusCodes();
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResHeaders()
    {
        resHeaders.put("Server: ", "JavaServer\r\n");
        resHeaders.put("Content-Type: ", "text/html\r\n");
        resHeaders.put("Content-Length: ", BODY.length() + "\r\n");
    }

    public void setResStatusCodes()
    {
        resStatusCodes.put(200, "OK\r\n");
        resStatusCodes.put(501, "Not Implemented\r\n");
    }


    public String responseLine(Integer statusCode)
    {
        String reason = resStatusCodes.get(statusCode);
        return "HTTP/1.1 " + statusCode + " " + reason;
    }

    public String responseHeaders(HashMap<String,String> extraHeaders)
    {
        HashMap<String, String> resHeadersShallowCopy = new HashMap<>(resHeaders);

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

    public String handleUnknownMethods()
    {
        String responseLine = responseLine(501);
        String responseHeaders = responseHeaders(null);
        String blankLine = "\r\n";
        String responseBody =  "<h1>501 Not Implemented</h1>";

        return  responseLine + responseHeaders + blankLine + responseBody;
    }

    public String handle_GET()
    {
        String resLine = responseLine(200);
        String headers = responseHeaders(null);
        String blankLine = "\r\n";

        return resLine + headers + blankLine + BODY;
    }

}
