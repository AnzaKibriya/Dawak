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

import static Helper.BaseClass.*;

public class PutOtpCpApiCall {

    static String apiUrl = "https://dawak-apim-uat.azure-api.net/dawak-auth/api/auth/verifyOtp";
    static String dpAccessToken;

    public static String OTPApiCall() {
        try {
            MediaType mediaType = MediaType.parse("application/json");
            Gson gson = new Gson();
            PutOtpCpApiCall putOTPApiCpCall = new PutOtpCpApiCall();
            String jsonPayload = gson.toJson(putOTPApiCpCall.getPutOtpCp());
            RequestBody body = RequestBody.create(jsonPayload, mediaType);
            Request request = new Request.Builder()
                    .url(apiUrl)
                    .header("Authorization", "Bearer " + authid)
                    .post(RequestBody.create(jsonPayload, MediaType.parse("application/json")))
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                JSONObject jsonResponse = new JSONObject(response.body().string());
                System.out.println(jsonResponse);
                JSONObject data = jsonResponse.getJSONObject("data");
                JSONObject token = data.getJSONObject("token");
                authid = String.valueOf(token.getString("accessToken"));
                test.log(Status.PASS, "Verify otp API verified  successfully");

                // System.out.println(authid);
            } else {
                System.out.println("API call failed!");
                System.out.println("Response: " + response.body().string());
            }
            return dpAccessToken;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public PutOtpCp getPutOtpCp() {
        try (Reader reader = new InputStreamReader(this.getClass()
                .getResourceAsStream("/PutOTPCP.json"))) {
            PutOtpCp result = new Gson().fromJson(reader, PutOtpCp.class);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
