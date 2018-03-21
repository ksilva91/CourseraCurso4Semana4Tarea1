package com.example.kruger.petagram.adapters;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kruger.petagram.R;
import com.example.kruger.petagram.model.Pet;
import com.example.kruger.petagram.model.PetConstructor;

import java.util.ArrayList;

/**
 * Created by Kruger on 27/12/2017.
 */

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder> {
    private ArrayList<Pet> pets;
    private PetConstructor petConstructor;
    private Context context;

    public PetAdapter(ArrayList<Pet> pets, Context context) {
        this.pets = pets;
        this.petConstructor = new PetConstructor(context);
        this.context = context;
    }

    @Override
    public PetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_pet, parent, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PetViewHolder holder, int position) {
        final Pet pet = pets.get(position);
        holder.ivImage.setImageResource(pet.getImage());
        holder.tvPetName.setText(pet.getName());
        holder.tvPetRating.setText(String.valueOf(pet.getRating()));
        holder.ivBone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int newRating = pet.getRating() + 1;
                pet.setRating(newRating);
                if(petConstructor.updateRatingToPet(pet) > 0){
                    holder.tvPetRating.setText(String.valueOf(pet.getRating()));
                }else {
                    pet.setRating(newRating - 1);
                    Snackbar.make(view, context.getString(R.string.error_rating), Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public static class PetViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImage;
        ImageView ivBone;
        TextView tvPetName;
        TextView tvPetRating;

        public PetViewHolder(View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.iv_image);
            ivBone = itemView.findViewById(R.id.iv_bone);
            tvPetName = itemView.findViewById(R.id.tv_pet_name);
            tvPetRating = itemView.findViewById(R.id.tv_rating);
        }
    }
}
