public class HTTPRequest
{
    private String statusCode = null;
    private String uri = null; // path
    private String httpVersion = "1.1"; //default to HTTP/1.1 if request doesn't provide a version
    private String data;
    private String[] words;

    HTTPRequest(String data) {
        this.data = data; // request of the client formatted in string

        // array of words of the request line
        this.words = data.split("\n")[0].split(" ");
        parse(data);
    }

    public void setHttpVersion()
    {
        httpVersion = words[2];
    }

    public String getHttpVersion()
    {
        return httpVersion;
    }

    public void setUri()
    {
        uri = words[1];
    }

    public String getUri()
    {
        return uri;
    }

    public void setStatusCode()
    {
        statusCode = words[0];
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public void parse(String data)
    {
        if (words.length > 0) {
            setStatusCode();
        }

        if (words.length > 1) {
            setUri();
        }
        if (words.length > 2) {
            setHttpVersion();
        }
    }




}
