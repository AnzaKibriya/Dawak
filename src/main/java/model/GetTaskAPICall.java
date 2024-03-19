package model;

import com.aventstack.extentreports.Status;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import static Helper.BaseClass.*;

public class GetTaskAPICall {

    static String apiUrl = "https://dawak-apim-uat.azure-api.net/dawak-portal/api/pharmacist/get-tasks";

    public static void getTaskApicall() throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        Gson gson = new Gson();
        GetTaskAPICall getTaskApiCall = new GetTaskAPICall();
        String jsonPayload = gson.toJson(getTaskApiCall.getTask());
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
           // Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(String.valueOf(jsonResponse), JsonObject.class);
            JsonArray dataArray = jsonObject.getAsJsonArray("data");
            test.log(Status.PASS, " getTask API verified");


            // Iterate over data array to extract finNumber and encounterId
            for (JsonElement element : dataArray) {
                JsonObject dataObject = element.getAsJsonObject();
                try {
                    String finNumber = dataObject.get("finNumber").getAsString();
                    String encounterId = dataObject.get("encounterId").getAsString();
                    System.out.println("finNumber: " + finNumber);
                    System.out.println("encounterId: " + encounterId);
                }
                catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }


        } else {
            System.out.println("API call failed!");
            System.out.println("Response: " + response.body().string());
        }


    }

    public GetTask getTask() {
        try (Reader reader = new InputStreamReader(this.getClass()
                .getResourceAsStream("/GetTask.json"))) {
            GetTask result = new Gson().fromJson(reader, GetTask.class);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}