package astro.api.collector.api;

public interface ApiInterface {
    public String request(String requestURL);
    public String parse(String responseBody);
    public String call(String requestURL);
}
