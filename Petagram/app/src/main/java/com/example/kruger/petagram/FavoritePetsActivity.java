package com.example.kruger.petagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.kruger.petagram.adapters.PetAdapter;
import com.example.kruger.petagram.model.Pet;
import com.example.kruger.petagram.model.PetConstructor;

import java.util.ArrayList;

public class FavoritePetsActivity extends AppCompatActivity {

    private ArrayList<Pet> pets = new ArrayList<>();
    private RecyclerView rvPets;
    private Toolbar toolbar;
    private PetConstructor petConstructor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_pets);
        rvPets = findViewById(R.id.rv_pet_list);
        toolbar = findViewById(R.id.action_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvPets.setLayoutManager(llm);
        initializeList();
        initializeAdapter();
    }

    private void initializeList(){
        petConstructor = new PetConstructor(FavoritePetsActivity.this);
        pets = petConstructor.getFavoritePets(5);
    }

    private void initializeAdapter(){
        PetAdapter pa = new PetAdapter(pets,FavoritePetsActivity.this);
        rvPets.setAdapter(pa);
    }
}
