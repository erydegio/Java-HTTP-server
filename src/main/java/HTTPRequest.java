public class HTTPRequest
{
    private String method = null;
    private String uri = null;
    private String httpVersion = "1.1"; //default to HTTP/1.1 if request doesn't provide a version
    private String data;
    private String[] words;

    HTTPRequest(String data)
    {
        this.data = data;
        this.words = data.split("\n")[0].split(" ");
        setMethod();
        //seturi
        //sethttpversion
    }

    public void setMethod()
    {
        method = words[0];
    }

    public String getMethod()
    {
        return method;
    }

    public void parse(String data)
    {
        //change here
        String[] lines = data.split("\n");
        String[] words = data.split("\n")[0].split(" ");

        if (words.length > 1) {
            uri = words[1];
        }
        if (words.length > 2) {
            httpVersion = words[2];
        }
    }


}
