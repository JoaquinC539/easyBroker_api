package easyBroker;
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class App{
    
    public static void main(String [] args) throws IOException{
        OkHttpClient client=new OkHttpClient();

        Request request=new Request.Builder()
        .url("https://reqres.in/api/users?page=2")
        .build();

        try(Response response = client.newCall(request).execute()){
            if(!response.isSuccessful()){
                throw new IOException("Unexteped code "+response);
            }
            System.out.println(response.body().string());
            
        }
    }
}