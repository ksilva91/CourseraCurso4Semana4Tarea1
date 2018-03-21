package com.example.kruger.petagram.vistaFragment;

import com.example.kruger.petagram.adapters.PetAdapter;
import com.example.kruger.petagram.model.Pet;

import java.util.ArrayList;

/**
 * Created by Kruger on 25/1/2018.
 */

public interface IListFragmentRV {

    public void generateVerticalLinearLayout();

    public PetAdapter createPetAdapter(ArrayList<Pet> pets);

    public void initRVPetAdapter(PetAdapter adaptador);

}
