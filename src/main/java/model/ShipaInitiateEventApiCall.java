package model;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static Helper.BaseClass.client;

public class ShipaInitiateEventApiCall {
    private static final String API_URL = "https://dawak-apim-uat.azure-api.net/dawak-patient/api/shipa/webhook";

    public static void makeShipaInitiateEventApiCall(String AUTH_TOKEN, String orderID, String event) {
        try {
            MediaType mediaType = MediaType.parse("application/json");
            Gson gson = new Gson();
            ShipaInitiateEventApiCall shipaInitiateEventApiCall = new ShipaInitiateEventApiCall();
            String jsonPayload = gson.toJson(shipaInitiateEventApiCall.shipaInitiateEventRequest(orderID, event));
            RequestBody body = RequestBody.create(jsonPayload, mediaType);
            Request request = new Request.Builder()
                    .url(API_URL)
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer " + AUTH_TOKEN)
                    .build();
            Response response = client.newCall(request).execute();
            int  a = response.code();
            if (response.isSuccessful()) {
                System.out.println("API call successful!");
                System.out.println("Response: " + response.body().string());
            } else {
                System.out.println("API call failed!");
                System.out.println("Response: " + response.body().string());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ShipaInitiateEvent shipaInitiateEventRequest(String orderID, String event) {
        try (Reader reader = new InputStreamReader(this.getClass().getResourceAsStream("/ShipaInitiateEvent.json"))) {
            Gson gson = new Gson();
            ShipaInitiateEvent result = gson.fromJson(reader, ShipaInitiateEvent.class);
            result.setShipaRef(orderID);
            result.setDate(getCurrentDateTime());
            result.setEvent("order.dropoff."+ event);
            System.out.println(result);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return now.format(formatter);
    }
}
