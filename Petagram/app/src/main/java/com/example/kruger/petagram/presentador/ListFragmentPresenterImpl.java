package com.example.kruger.petagram.presentador;

import android.content.Context;

import com.example.kruger.petagram.model.Pet;
import com.example.kruger.petagram.model.PetConstructor;
import com.example.kruger.petagram.vistaFragment.IListFragmentRV;

import java.util.ArrayList;


public class ListFragmentPresenterImpl implements IListFragmentPresenter {

    private IListFragmentRV iListFragmentRV;
    private Context context;
    private PetConstructor petConstructor;
    private ArrayList<Pet> pets;

    public ListFragmentPresenterImpl(IListFragmentRV iListFragmentRV, Context context) {
        this.iListFragmentRV = iListFragmentRV;
        this.context = context;
        obtenerContactosBaseDatos();
        //obtenerMediosRecientes();
    }

    @Override
    public void obtenerContactosBaseDatos() {
        petConstructor = new PetConstructor(context);
        pets = petConstructor.getPets();
        mostrarContactosRV();
    }

    @Override
    public void mostrarContactosRV() {
        iListFragmentRV.initRVPetAdapter(iListFragmentRV.createPetAdapter(pets));
        iListFragmentRV.generateVerticalLinearLayout();
    }
}
