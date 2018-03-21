package com.example.kruger.petagram.vistaFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kruger.petagram.R;
import com.example.kruger.petagram.adapters.PetAdapter;
import com.example.kruger.petagram.model.Pet;
import com.example.kruger.petagram.presentador.IListFragmentPresenter;
import com.example.kruger.petagram.presentador.ListFragmentPresenterImpl;

import java.util.ArrayList;

/**
 * Created by anahisalgado on 20/04/16.
 */
public class ListFragmentRVImpl extends Fragment implements IListFragmentRV {
    private ArrayList<Pet> pets;
    private RecyclerView rvPets;
    private IListFragmentPresenter presenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        rvPets = (RecyclerView) v.findViewById(R.id.rv_pet_list);
        presenter = new ListFragmentPresenterImpl(this, getContext());
        return v;
    }

    @Override
    public void generateVerticalLinearLayout() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvPets.setLayoutManager(llm);
    }

    @Override
    public PetAdapter createPetAdapter(ArrayList<Pet> pets) {
        PetAdapter adaptador = new PetAdapter(pets, getActivity());
        return adaptador;
    }

    @Override
    public void initRVPetAdapter(PetAdapter adaptador) {
        rvPets.setAdapter(adaptador);
    }
}
