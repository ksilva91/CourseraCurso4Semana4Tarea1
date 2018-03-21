package com.example.kruger.petagram.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.kruger.petagram.model.Media;
import com.example.kruger.petagram.model.Pet;
import com.example.kruger.petagram.model.PetConstructor;
import com.example.kruger.petagram.model.User;
import com.example.kruger.petagram.restApi.EndpointsApi;
import com.example.kruger.petagram.restApi.adapter.RestApiAdapter;
import com.example.kruger.petagram.restApi.model.MediaResponse;
import com.example.kruger.petagram.utils.SharedPreferencesManager;
import com.example.kruger.petagram.vistaFragment.IGridListFragmentRV;
import com.example.kruger.petagram.vistaFragment.IListFragmentRV;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GridListFragmentPresenterImpl implements IGridListFragmentPresenter {

    private IGridListFragmentRV iGridListFragmentRV;
    private Context context;
    private PetConstructor petConstructor;
    private ArrayList<Media> mediaArrayList;
    private User user;

    public GridListFragmentPresenterImpl(IGridListFragmentRV iGridListFragmentRV, Context context) {
        this.iGridListFragmentRV = iGridListFragmentRV;
        this.context = context;
        obtenerMediosRecientes();
    }

    @Override
    public void obtenerMediosRecientes() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.buildGsonDeserializerMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.setConnectionRestApiInstagram(gsonMediaRecent);
        Call<MediaResponse> contactoResponseCall = endpointsApi.getRecentMediaByUserId(SharedPreferencesManager.getUserId(context));

        contactoResponseCall.enqueue(new Callback<MediaResponse>() {
            @Override
            public void onResponse(Call<MediaResponse> call, Response<MediaResponse> response) {
                MediaResponse mediaResponse = response.body();
                mediaArrayList = mediaResponse != null ? mediaResponse.getMediaArrayList() : new ArrayList<Media>();
                user = mediaResponse != null ? mediaResponse.getUser() : new User();
                mostrarData();
            }

            @Override
            public void onFailure(Call<MediaResponse> call, Throwable t) {
                Toast.makeText(context, "¡Al pasó en la conexión! Intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION", t.toString());
            }
        });
    }

    @Override
    public void mostrarData() {
        iGridListFragmentRV.initRVPetAdapter(iGridListFragmentRV.createPetGridAdapter(mediaArrayList));
        iGridListFragmentRV.generateGridLayout();
        iGridListFragmentRV.setUserDataToView(user);
    }
}
