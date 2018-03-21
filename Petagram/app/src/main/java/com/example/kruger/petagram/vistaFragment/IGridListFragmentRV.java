package com.example.kruger.petagram.vistaFragment;

import com.example.kruger.petagram.adapters.PetAdapter;
import com.example.kruger.petagram.adapters.PetGridAdapter;
import com.example.kruger.petagram.model.Media;
import com.example.kruger.petagram.model.Pet;
import com.example.kruger.petagram.model.User;

import java.util.ArrayList;

/**
 * Created by Kruger on 25/1/2018.
 */

public interface IGridListFragmentRV {

    void generateGridLayout();

    PetGridAdapter createPetGridAdapter(ArrayList<Media> mediaArrayList);

    void initRVPetAdapter(PetGridAdapter adaptador);

    void setUserDataToView(User user);

}
