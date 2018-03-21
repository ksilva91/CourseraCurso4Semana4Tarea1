package com.example.kruger.petagram.restApi.deserializador;

import com.example.kruger.petagram.model.Media;
import com.example.kruger.petagram.model.User;
import com.example.kruger.petagram.restApi.JsonKeys;
import com.example.kruger.petagram.restApi.model.MediaResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Kruger on 25/1/2018.
 */

public class UserDeserializer implements JsonDeserializer<MediaResponse> {
    @Override
    public MediaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MediaResponse mediaResponse = gson.fromJson(json, MediaResponse.class);
        JsonArray mediaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        mediaResponse.setUser(deserializarUserDeJson(mediaResponseData));
        return mediaResponse;
    }

    private User deserializarUserDeJson(JsonArray responseData){
        User user = new User();

        JsonObject contactoResponseDataObject = responseData.get(0).getAsJsonObject();

        String id               = contactoResponseDataObject.get(JsonKeys.USER_ID).getAsString();
        String fullname         = contactoResponseDataObject.get(JsonKeys.USER_FULLNAME).getAsString();
        String profilePhoto     = contactoResponseDataObject.get(JsonKeys.USER_PROFILE_PICTURE).getAsString();
        String username         = contactoResponseDataObject.get(JsonKeys.USER_USERNAME).getAsString();

        user.setId(id);
        user.setFull_name(fullname);
        user.setProfile_picture(profilePhoto);
        user.setUsername(username);
        return user;
    }
}
