package com.example.kruger.petagram.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.kruger.petagram.R;
import com.example.kruger.petagram.restApi.EndpointsApi;
import com.example.kruger.petagram.restApi.adapter.RestApiAdapter;
import com.example.kruger.petagram.restApi.model.RelationshipResponse;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Kruger on 20/3/2018.
 */

public class FollowUnfollowBroadcastL extends BroadcastReceiver {
    private Context context;

    private String id_user;
    private String action;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        String ACTION_KEY = "F_U";
        String action = intent.getAction();
        if (ACTION_KEY.equals(action)){
            id_user = intent.getStringExtra("id_user");
            getRelationshipUser(id_user);
        }

    }

    private void getRelationshipUser(final String id_user) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.buildGsonRelationshipDeserializer();
        EndpointsApi endpoints = restApiAdapter.setConnectionRestApiInstagram(gson);
        Call<RelationshipResponse> getRelationshipResponseCall = endpoints.getRelationship(id_user);
        getRelationshipResponseCall.enqueue(new Callback<RelationshipResponse>() {
            @Override
            public void onResponse(Call<RelationshipResponse> call, Response<RelationshipResponse> response) {
                if(response.body().getStatus().equals("200")) {
                    String action = "";
                    if(response.body().getOutgoing_status().equals("follows")) {
                        action = "unfollow";
                    }else {
                        action = "follow";
                    }
                    postRelationshipUser(id_user,action);
                }else {
                    Toast.makeText(context, R.string.unexpected_error, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RelationshipResponse> call, Throwable throwable) {
                Toast.makeText(context, R.string.unexpected_error, Toast.LENGTH_LONG).show();
            }
        });
    }


    private void postRelationshipUser(String id_user, String action) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.buildGsonRelationshipDeserializer();
        EndpointsApi endpoints = restApiAdapter.setConnectionRestApiInstagram(gson);
        Call<RelationshipResponse> postRelationshipResponseCall = endpoints.setRelationship(id_user, action);
        postRelationshipResponseCall.enqueue(new Callback<RelationshipResponse>() {
            @Override
            public void onResponse(Call<RelationshipResponse> call, Response<RelationshipResponse> response) {
                if(response.body().getStatus().equals("200")) {
                    if(response.body().getOutgoing_status().equals("follows")) {
                        Toast.makeText(context, "Se está siguiento al usuario", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(context, "Se ha dejado de seguir al usuario", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(context, R.string.unexpected_error, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RelationshipResponse> call, Throwable throwable) {
                Toast.makeText(context, R.string.unexpected_error, Toast.LENGTH_LONG).show();
            }
        });
    }

}

