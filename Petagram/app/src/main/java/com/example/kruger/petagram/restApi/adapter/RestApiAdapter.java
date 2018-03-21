package com.example.kruger.petagram.restApi.adapter;


import com.example.kruger.petagram.restApi.ConstantesRestApi;
import com.example.kruger.petagram.restApi.EndpointsApi;
import com.example.kruger.petagram.restApi.deserializador.MediaDeserializer;
import com.example.kruger.petagram.restApi.deserializador.RelationshipDeserializer;
import com.example.kruger.petagram.restApi.deserializador.UniversalDeserializer;
import com.example.kruger.petagram.restApi.deserializador.UserDeserializer;
import com.example.kruger.petagram.restApi.model.MediaResponse;
import com.example.kruger.petagram.restApi.model.RelationshipResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestApiAdapter {

    public EndpointsApi setConnectionRestApiInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.INSTAGRAM_ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndpointsApi.class);
    }

    public Gson buildGsonDeserializerMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MediaResponse.class, new MediaDeserializer());
        return gsonBuilder.create();
    }

    public Gson buildGsonDeserializerGetUser(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MediaResponse.class, new UserDeserializer());
        return gsonBuilder.create();
    }

    public EndpointsApi setConnectionRestApiServer(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.SERVER_ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(EndpointsApi.class);
    }

    public Gson buildGsonUniversalDeserializer(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(UniversalDeserializer.class, new UniversalDeserializer());
        return gsonBuilder.create();
    }

    public Gson buildGsonRelationshipDeserializer(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(RelationshipResponse.class, new RelationshipDeserializer());
        return gsonBuilder.create();
    }


}
