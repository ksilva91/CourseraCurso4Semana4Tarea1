package com.example.kruger.petagram.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.kruger.petagram.model.Media;
import com.example.kruger.petagram.model.User;
import com.example.kruger.petagram.restApi.ConstantesRestApi;
import com.example.kruger.petagram.restApi.EndpointsApi;
import com.example.kruger.petagram.restApi.adapter.RestApiAdapter;
import com.example.kruger.petagram.restApi.model.MediaResponse;
import com.example.kruger.petagram.utils.SharedPreferencesManager;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Kruger on 25/1/2018.
 */

public class SetupAccountPresenterImpl implements ISetupAccountPresenter{

    private Context context;

    public SetupAccountPresenterImpl(Context context) {
        this.context = context;
    }

    @Override
    public void getUsername(String username) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonGetUser = restApiAdapter.buildGsonDeserializerGetUser();
        EndpointsApi endpointsApi = restApiAdapter.setConnectionRestApiInstagram(gsonGetUser);
        Call<MediaResponse> getUserResponseCall = endpointsApi.getUser(username, ConstantesRestApi.INSTAGRAM_ACCESS_TOKEN);

        getUserResponseCall.enqueue(new Callback<MediaResponse>() {
            @Override
            public void onResponse(Call<MediaResponse> call, Response<MediaResponse> response) {
                MediaResponse mediaResponse = response.body();
                String idUser = mediaResponse != null ? mediaResponse.getUser().getId() : "";
                if(idUser.equals("")){
                    Toast.makeText(context,"No se pudo encontrar el username..! Vuelva a intentar",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context,"Usuario actualizado exitosamente",Toast.LENGTH_LONG).show();
                    saveIdUser(idUser);
                }
            }

            @Override
            public void onFailure(Call<MediaResponse> call, Throwable t) {
                Toast.makeText(context, "¡Al pasó en la conexión! Intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION", t.toString());
            }
        });

    }

    @Override
    public void saveIdUser(String id) {
        SharedPreferencesManager.setUserId(context,id);
    }


}
