package model;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static Helper.BaseClass.client;

public class PrescriptionApiCall {

    private static final String API_URL = "https://dawak-apim-training.azure-api.net/dawak-portal/api/prescription/new";

    public static void makePrescriptionApiCall(String AUTH_TOKEN, String orderID) {
        try {
            MediaType mediaType = MediaType.parse("application/json");
            Gson gson = new Gson();
            PrescriptionApiCall prescriptionApiCall = new PrescriptionApiCall();
            prescriptionApiCall.getPrescriptionRequest().getOrder().setParentFin(orderID);
            prescriptionApiCall.getPrescriptionRequest().getOrder().setPhysicianOrderDate(getCurrentDateTime());
            prescriptionApiCall.getPrescriptionRequest().getOrder().setOrderVisitDate(getCurrentDateTime());
            String jsonPayload = gson.toJson(prescriptionApiCall.getPrescriptionRequest());
            RequestBody body = RequestBody.create(jsonPayload, mediaType);
            Request request = new Request.Builder()
                    .url(API_URL)
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer " + AUTH_TOKEN)
                    .build();
            Response response = client.newCall(request).execute();
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

    public PrescriptionRequest getPrescriptionRequest() {
        try (Reader reader = new InputStreamReader(this.getClass()
                .getResourceAsStream("/CreatingOrder.json"))) {
            PrescriptionRequest result = new Gson().fromJson(reader, PrescriptionRequest.class);
            System.out.println(result.getPatient().getAddress());
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
