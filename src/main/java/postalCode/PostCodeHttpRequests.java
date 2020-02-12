package postalCode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import postalCode.entities.PostCodeFullEntity;
import postalCode.entities.PostCodeList;
import postalCode.entities.Validator;

public class PostCodeHttpRequests {
    private OkHttpClient client;
    private Gson gson;
    private static String baseUrl="http://api.postcodes.io/";

    public PostCodeHttpRequests(){
        client = new OkHttpClient().newBuilder().build();
        gson = new GsonBuilder().create();
    }

    public Validator validatePostCode(String code) throws IOException {
        Request request = new Request.Builder()
                .url(baseUrl + "postcodes/"+code+"/validate")
                .method("GET", null)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .build();
        ResponseBody responseBody = client.newCall(request).execute().body();
        Validator entity = gson.fromJson(responseBody.string(), Validator.class);
        return entity;
    }

    public PostCodeFullEntity getPostCode(String code) throws IOException {
        Request request = new Request.Builder()
                .url(baseUrl + "postcodes/"+code)
                .method("GET", null)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .build();
        ResponseBody responseBody = client.newCall(request).execute().body();
        PostCodeFullEntity entity = gson.fromJson(responseBody.string(), PostCodeFullEntity.class);
        return entity;
    }


    public PostCodeList getListOfCountries(String code) throws IOException {
        Request request = new Request.Builder()
                .url(baseUrl + "postcodes/"+code+"/nearest")
                .method("GET", null)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .build();
        ResponseBody responseBody = client.newCall(request).execute().body();
        PostCodeList entity = gson.fromJson(responseBody.string(), PostCodeList.class);

        return entity;
    }

}
