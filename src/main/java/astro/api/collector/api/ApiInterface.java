package astro.api.collector.api;

public interface ApiInterface {
    public String request();
    public String parse(String responseBody);
    public String call();
}
