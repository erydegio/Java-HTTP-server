public class HTTPRequest
{
    private String meth = null;
    private String uri = null; // path
    private String httpVersion = "1.1"; //default to HTTP/1.1 if request doesn't provide a version
    private String data;
    private String[] words;

    HTTPRequest(String data)
    {
        this.data = data; // request of the client formatted in string
        this.words = data.split("\n")[0].split(" "); // array of words of the request line
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

    public void setMeth()
    {
        meth = words[0];
    }

    public String getMeth()
    {
        return meth;
    }

    public void parse(String data)
    {
        setMeth();

        if (words.length > 1) {
            setUri();
        }
        if (words.length > 2) {
            setHttpVersion();
        }
    }




}
