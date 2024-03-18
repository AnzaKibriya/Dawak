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

public class CreateOtpCpApiCall {

    static String apiUrl = "https://dawak-apim-uat.azure-api.net/dawak-auth/api/auth/createOtp";
    public static void createOtpApiCall() {
        try{
            MediaType mediaType = MediaType.parse("application/json");
            Gson gson = new Gson();
            CreateOtpCpApiCall createOtpcpApiCall = new CreateOtpCpApiCall();
            String jsonPayload = gson.toJson(createOtpcpApiCall.getCreateOtpCp());
            RequestBody body = RequestBody.create(jsonPayload, mediaType);
            Request request = new Request.Builder()
                    .url(apiUrl)
                    .post(RequestBody.create(jsonPayload, MediaType.parse("application/json")))
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                JSONObject jsonResponse = new JSONObject(response.body().string());
                test.log(Status.PASS, " Create otp API verified");
//                JSONObject data = jsonResponse.getJSONObject("data");
//                accessToken = data.getString("access_token");
            } else {
                System.out.println("API call failed!");
                System.out.println("Response: " + response.body().string());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public CreateOtpCp getCreateOtpCp() {
        try (Reader reader = new InputStreamReader(this.getClass()
                .getResourceAsStream("/CreateOTPCP.json"))) {
            CreateOtpCp result = new Gson().fromJson(reader, CreateOtpCp.class);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
