package com.example.kruger.petagram.restApi.deserializador;

import com.example.kruger.petagram.model.Media;
import com.example.kruger.petagram.model.User;
import com.example.kruger.petagram.restApi.JsonKeys;
import com.example.kruger.petagram.restApi.model.MediaResponse;
import com.example.kruger.petagram.restApi.model.UniversalResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class UniversalDeserializer implements JsonDeserializer<UniversalResponse> {
    @Override
    public UniversalResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        UniversalResponse universalResponse = gson.fromJson(json, UniversalResponse.class);
        JsonArray universalResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.META);
        universalResponse.setStatusCode(deserializarUniversalDeJson(universalResponseData));
        return universalResponse;
    }

    private String deserializarUniversalDeJson(JsonArray universalResponseData){
        JsonObject universalResponseDataObject = universalResponseData.get(0).getAsJsonObject();
        String code = universalResponseDataObject.get(JsonKeys.USER_ID).getAsString();
        return code;
    }
}
