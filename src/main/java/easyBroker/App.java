package easyBroker;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class App{
    
    public static void main(String [] args) throws IOException{
        OkHttpClient client=new OkHttpClient();

        Request request=new Request.Builder()
        .url("https://api.stagingeb.com/v1/properties?page=1&limit=2")
        .get()
        .addHeader("X-Authorization", "l7u502p8v46ba3ppgvj5y2aad50lb9")
        .addHeader("accept", "application/json")
        .build();

        try(Response response = client.newCall(request).execute()){
            if(!response.isSuccessful()){
                throw new IOException("Unexteped code "+response);
            }
            String jsonResponse = response.body().string();
            Gson gson=new Gson();
            JsonObject jsonObject=gson.fromJson(jsonResponse, JsonObject.class);
            JsonArray contentArray=jsonObject.get("content").getAsJsonArray();
            for(int i=0;i<contentArray.size();i++)
            {
                System.out.println(contentArray.get(i).getAsJsonObject().get("title"));
            }
            
        }
    }
}