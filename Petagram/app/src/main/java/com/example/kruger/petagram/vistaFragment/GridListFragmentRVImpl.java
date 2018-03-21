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
import android.widget.TextView;

import com.example.kruger.petagram.R;
import com.example.kruger.petagram.adapters.PetAdapter;
import com.example.kruger.petagram.adapters.PetGridAdapter;
import com.example.kruger.petagram.model.Media;
import com.example.kruger.petagram.model.Pet;
import com.example.kruger.petagram.model.User;
import com.example.kruger.petagram.presentador.GridListFragmentPresenterImpl;
import com.example.kruger.petagram.presentador.IGridListFragmentPresenter;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GridListFragmentRVImpl extends Fragment implements IGridListFragmentRV {
    private RecyclerView rvPets;
    private TextView tvName;
    private CircularImageView civProfile;
    private IGridListFragmentPresenter presenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_grid_list, container, false);
        rvPets      = v.findViewById(R.id.rv_pet_grid_list);
        tvName      = v.findViewById(R.id.tv_account_name);
        civProfile  = v.findViewById(R.id.civ_profile_image);
        presenter = new GridListFragmentPresenterImpl(this, getContext());
        return v;
    }

    @Override
    public void generateGridLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        rvPets.setLayoutManager(gridLayoutManager);
    }

    @Override
    public PetGridAdapter createPetGridAdapter(ArrayList<Media> mediaArrayList) {
        PetGridAdapter adaptador = new PetGridAdapter(mediaArrayList, getActivity());
        return adaptador;
    }

    @Override
    public void initRVPetAdapter(PetGridAdapter adaptador) {
        rvPets.setAdapter(adaptador);
    }

    @Override
    public void setUserDataToView(User user) {
        tvName.setText(user.getFull_name());
        Picasso.with(getActivity())
                .load(user.getProfile_picture())
                .placeholder(R.drawable.image_not_found)
                .into(civProfile);
    }
}
