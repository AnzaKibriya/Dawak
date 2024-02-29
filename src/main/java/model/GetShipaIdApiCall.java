package model;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import static Helper.BaseClass.*;
import static Helper.BaseClass.accessToken;

public class GetShipaIdApiCall {
    static String deliveryID;

    public static void makeShipaIdApiCall(String AUTH_TOKEN) {
        try {
            String apiUrl = "https://dawak-apim-uat.azure-api.net/dawak-patient/api/shipa/get-delivery-details?encounterId=4021";
//                    + getDeliveryID();
            MediaType mediaType = MediaType.parse("application/json");
            Gson gson = new Gson();
            Request request = new Request.Builder()
                    .url(apiUrl)
                    .get()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer " + AUTH_TOKEN)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                JSONObject jsonResponse = new JSONObject(response.body().string());
                JSONObject data = jsonResponse.getJSONObject("data");
                accessToken = data.getString("access_token");
            } else {
                System.out.println("API call failed!");
                System.out.println("Response: " + response.body().string());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setDeliveryID(String deliveryID) {
        GetShipaIdApiCall.deliveryID = deliveryID;
    }

    public static String getDeliveryID() {
        return deliveryID;
    }

}
