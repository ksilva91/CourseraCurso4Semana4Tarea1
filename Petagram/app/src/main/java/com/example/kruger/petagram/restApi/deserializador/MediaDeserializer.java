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


public class MediaDeserializer implements JsonDeserializer<MediaResponse> {
    @Override
    public MediaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MediaResponse mediaResponse = gson.fromJson(json, MediaResponse.class);
        JsonArray mediaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        mediaResponse.setMediaArrayList(deserializarMediaDeJson(mediaResponseData));
        mediaResponse.setUser(deserializarUserDeJson(mediaResponseData));
        return mediaResponse;
    }

    private ArrayList<Media> deserializarMediaDeJson(JsonArray mediaResponseData){
        ArrayList<Media> mediaArrayList = new ArrayList<>();
        for (int i = 0; i < mediaResponseData.size() ; i++) {
            JsonObject contactoResponseDataObject = mediaResponseData.get(i).getAsJsonObject();

            JsonObject userJson     = contactoResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id               = contactoResponseDataObject.get(JsonKeys.MEDIA_ID).getAsString();
            String nombreCompleto   = userJson.get(JsonKeys.USER_FULLNAME).getAsString();

            JsonObject imageJson            = contactoResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject stdResolutionJson    = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String urlFoto                  = stdResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likesJson = contactoResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            Media media = new Media();
            media.setId(id);
            media.setName(nombreCompleto);
            media.setUrlPhoto(urlFoto);
            media.setLikes(likes);

            mediaArrayList.add(media);
        }
        return mediaArrayList;
    }

    private User deserializarUserDeJson(JsonArray responseData){
        User user = new User();

        JsonObject contactoResponseDataObject = responseData.get(0).getAsJsonObject();

        JsonObject userJson     = contactoResponseDataObject.getAsJsonObject(JsonKeys.USER);
        String id               = userJson.get(JsonKeys.USER_ID).getAsString();
        String fullname         = userJson.get(JsonKeys.USER_FULLNAME).getAsString();
        String profilePhoto     = userJson.get(JsonKeys.USER_PROFILE_PICTURE).getAsString();
        String username         = userJson.get(JsonKeys.USER_USERNAME).getAsString();

        user.setId(id);
        user.setFull_name(fullname);
        user.setProfile_picture(profilePhoto);
        user.setUsername(username);
        return user;
    }
}
