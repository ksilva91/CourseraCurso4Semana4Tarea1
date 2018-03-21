package com.example.kruger.petagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kruger.petagram.adapters.PetGridAdapter;
import com.example.kruger.petagram.adapters.SeeUserAdapter;
import com.example.kruger.petagram.model.Media;
import com.example.kruger.petagram.model.User;
import com.example.kruger.petagram.restApi.EndpointsApi;
import com.example.kruger.petagram.restApi.adapter.RestApiAdapter;
import com.example.kruger.petagram.restApi.model.MediaResponse;
import com.example.kruger.petagram.utils.SharedPreferencesManager;
import com.google.gson.Gson;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeeUserActivity extends AppCompatActivity {
    private RecyclerView rvPets;
    private TextView tvName;
    private CircularImageView civProfile;
    private ArrayList<Media> mediaArrayList;
    private SeeUserAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_user);
        rvPets      = findViewById(R.id.rv_pet_grid_list);
        tvName      = findViewById(R.id.tv_account_name);
        civProfile  = findViewById(R.id.civ_profile_image);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String id_user = bundle.getString("id_user");
            obtenerMediosRecientes(id_user);
        }
    }

    public void obtenerMediosRecientes(String userId) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.buildGsonDeserializerMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.setConnectionRestApiInstagram(gsonMediaRecent);
        Call<MediaResponse> contactoResponseCall = endpointsApi.getRecentMediaByUserId(userId);

        contactoResponseCall.enqueue(new Callback<MediaResponse>() {
            @Override
            public void onResponse(Call<MediaResponse> call, Response<MediaResponse> response) {
                MediaResponse mediaResponse = response.body();
                mediaArrayList = mediaResponse != null ? mediaResponse.getMediaArrayList() : new ArrayList<Media>();
                User user = mediaResponse != null ? mediaResponse.getUser() : new User();
                mostrarData(user);
            }

            @Override
            public void onFailure(Call<MediaResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "¡Al pasó en la conexión! Intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION", t.toString());
            }
        });
    }

    public void mostrarData(User user){
        tvName.setText(user.getFull_name());
        Picasso.with(this)
                .load(user.getProfile_picture())
                .placeholder(R.drawable.image_not_found)
                .into(civProfile);
        adaptador = new SeeUserAdapter(mediaArrayList, this);
        rvPets.setAdapter(adaptador);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rvPets.setLayoutManager(gridLayoutManager);
    }
}
