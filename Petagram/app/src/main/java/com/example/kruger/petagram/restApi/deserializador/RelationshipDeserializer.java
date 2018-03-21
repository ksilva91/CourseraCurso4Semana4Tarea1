package com.example.kruger.petagram.restApi.deserializador;

import com.example.kruger.petagram.model.User;
import com.example.kruger.petagram.restApi.JsonKeys;
import com.example.kruger.petagram.restApi.model.MediaResponse;
import com.example.kruger.petagram.restApi.model.RelationshipResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Kruger on 25/1/2018.
 */

public class RelationshipDeserializer implements JsonDeserializer<RelationshipResponse> {
    @Override
    public RelationshipResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        RelationshipResponse relationshipResponse = gson.fromJson(json, RelationshipResponse.class);

        JsonObject mediaResponseData = json.getAsJsonObject().get(JsonKeys.MEDIA_RESPONSE_ARRAY).getAsJsonObject();
        JsonObject statusResponseData = json.getAsJsonObject().get(JsonKeys.META).getAsJsonObject();

        relationshipResponse.setOutgoing_status(mediaResponseData.get(JsonKeys.OUTGOING_STATUS).getAsString());
        try{
            relationshipResponse.setIncoming_status(mediaResponseData.get(JsonKeys.INCOMING_STATUS).getAsString());
        } catch (Exception e){
            relationshipResponse.setIncoming_status("");
        }
        relationshipResponse.setStatus(statusResponseData.get(JsonKeys.STATUS_CODE).getAsString());
        return relationshipResponse;
    }
}
