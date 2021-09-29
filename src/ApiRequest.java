import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.TimeZone;

import com.fasterxml.jackson.databind.ObjectMapper;


public class ApiRequest {



    public static Object pullData(){

        //pulls data from fixer.io server and returns the contents from the parser.

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://data.fixer.io/api/latest?access_key=d1a4bce59676611d5fbd3174625efa19")).build();
        HttpClient client = HttpClient.newHttpClient();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(ApiRequest::parse)
                .join();


    }

    public static Object parse(String responseBody){

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


    /*saved for future use  ... is probably broken
    public static void fileWrite(Object map){
        File file = new File("currentData.out");

        try (FileOutputStream fos = new FileOutputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             DataOutputStream dos = new DataOutputStream(bos)) {
            dos.writeBytes(map.toString());
            System.out.println("Successfully written data to the file");
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }

     //saved for future use.. might be broken??
    public static void fileBuilder() {


        try {

            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            formatter.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
            Path file = Paths.get("currentData.out");
            String str = formatter.format(date);
            BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
            if (!(attr.lastModifiedTime().toString().contains(str))) {
                pullData();
            }
        } catch (Exception e) {
            pullData();
        }
    } */

}
