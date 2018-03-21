package com.example.kruger.petagram;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.kruger.petagram.adapters.PageAdapter;
import com.example.kruger.petagram.model.Media;
import com.example.kruger.petagram.model.User;
import com.example.kruger.petagram.restApi.EndpointsApi;
import com.example.kruger.petagram.restApi.adapter.RestApiAdapter;
import com.example.kruger.petagram.restApi.model.MediaResponse;
import com.example.kruger.petagram.restApi.model.UserResponse;
import com.example.kruger.petagram.utils.SharedPreferencesManager;
import com.example.kruger.petagram.vistaFragment.GridListFragmentRVImpl;
import com.example.kruger.petagram.vistaFragment.ListFragmentRVImpl;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.action_bar);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        if(toolbar != null){
            setSupportActionBar(toolbar);
        }
        setUpViewPager();

    }

    private ArrayList<Fragment> addFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new ListFragmentRVImpl());
        fragments.add(new GridListFragmentRVImpl());
        return fragments;
    }

    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),addFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.mipmap.ic_home);
        tabLayout.getTabAt(1).setIcon(R.mipmap.ic_dog);

    }

    private void receiveNotifications(){
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.setConnectionRestApiServer();
        Call<UserResponse> userResponseCall  = endpointsApi.registerUser(refreshedToken, SharedPreferencesManager.getUserId(MainActivity.this));

        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                Toast.makeText(MainActivity.this, "Se ha registrado el usuario exitosamente, ahora podrá recibir notificaciones", Toast.LENGTH_LONG).show();
                Log.e("COURSERA", "CONEXIÓN EXITOSA");
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, "¡Algo pasó en la conexión! Intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e("COURSERA", "FALLO LA CONEXION - " + throwable.toString());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_star:
                Intent intent = new Intent(MainActivity.this, FavoritePetsActivity.class);
                startActivity(intent);
                break;
            case R.id.mContact:
                Intent intent2 = new Intent(MainActivity.this, ContactoActivity.class);
                startActivity(intent2);
                break;
            case R.id.mAbout:
                Intent intent3 = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(intent3);
                break;
            case R.id.mSetupAccount:
                Intent intent4 = new Intent(MainActivity.this, SetupAccountActivity.class);
                startActivity(intent4);
                break;
            case R.id.mReceiveNotifications:
                receiveNotifications();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
