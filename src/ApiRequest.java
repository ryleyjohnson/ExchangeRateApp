import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


import com.fasterxml.jackson.databind.ObjectMapper;


public class ApiRequest {

    static final String _API_URI = "http://data.fixer.io/api/latest?access_key=d1a4bce59676611d5fbd3174625efa19";


    public Object pullData() {

        //pulls data from fixer.io server and returns the contents from the parser.

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(_API_URI)).build();
        HttpClient client = HttpClient.newHttpClient();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(ApiRequest::parse)
                .join();
    }


    public static Object parse(String responseBody) {

        //Parses the API data to a map and returns it.

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        try {

            // convert JSON string to Map

            map = mapper.readValue(responseBody, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
}



