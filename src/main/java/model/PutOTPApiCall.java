package model;

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

public class PutOTPApiCall {
    static String apiUrl = "https://dawak-apim-uat.azure-api.net/dawak-auth/api/auth/verifyOtp";
    public static void OTPApiCall() {
        try{
            MediaType mediaType = MediaType.parse("application/json");
            Gson gson = new Gson();
            PutOTPApiCall putOTPApiCall = new PutOTPApiCall();
            String jsonPayload = gson.toJson(putOTPApiCall.getPutOtp());
            RequestBody body = RequestBody.create(jsonPayload, mediaType);
            Request request = new Request.Builder()
                    .url(apiUrl)
                    .post(RequestBody.create(jsonPayload, MediaType.parse("application/json")))
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                JSONObject jsonResponse = new JSONObject(response.body().string());
                JSONObject data = jsonResponse.getJSONObject("data");
                String accessToken = String.valueOf(data.getJSONObject("token"));
                System.out.println(accessToken);
            } else {
                System.out.println("API call failed!");
                System.out.println("Response: " + response.body().string());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public PutOTP getPutOtp() {
        try (Reader reader = new InputStreamReader(this.getClass()
                .getResourceAsStream("/PutOTP.json"))) {
            PutOTP result = new Gson().fromJson(reader, PutOTP.class);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
