package model;

import com.aventstack.extentreports.Status;
import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import static Helper.BaseClass.client;
import static Helper.BaseClass.test;

public class LogincpApiCall {

    static String apiUrl = "https://dawak-apim-uat.azure-api.net/dawak-auth/api/auth/v2/web-login";
    public static void makeLoginApiCall() {
        try{
            MediaType mediaType = MediaType.parse("application/json");
            Gson gson = new Gson();
            LogincpApiCall logincpApiCall = new LogincpApiCall();
            String jsonPayload = gson.toJson(logincpApiCall.getLogincp());
            RequestBody body = RequestBody.create(jsonPayload, mediaType);
            Request request = new Request.Builder()
                    .url(apiUrl)
                    .post(RequestBody.create(jsonPayload, MediaType.parse("application/json")))
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                JSONObject jsonResponse = new JSONObject(response.body().string());
                System.out.println(jsonResponse);
                test.log(Status.PASS, " Login API verified");
            } else {
                System.out.println("API call failed!");
                System.out.println("Response: " + response.body().string());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public Logincp getLogincp() {
        try (Reader reader = new InputStreamReader(this.getClass()
                .getResourceAsStream("/Logincp.json"))) {
            Logincp result = new Gson().fromJson(reader, Logincp.class);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
