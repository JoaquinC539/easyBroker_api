package easyBroker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class AppTest {

    @Test
    public void testApp() throws IOException{
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.stagingeb.com/v1/properties?page=1&limit=2")
                .get()
                .addHeader("X-Authorization", "l7u502p8v46ba3ppgvj5y2aad50lb9")
                .addHeader("accept", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            String jsonResponse = response.body().string();
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);
            JsonArray contentArray = jsonObject.getAsJsonArray("content");

            assertNotNull(contentArray);
            assertEquals(2, contentArray.size());
            for (int i = 0; i < contentArray.size(); i++) {
                JsonObject item = contentArray.get(i).getAsJsonObject();
                assertNotNull(item);
                assertNotNull(item.get("title"));
                System.out.println(item.get("title").getAsString());
            }
        }
    }
}
