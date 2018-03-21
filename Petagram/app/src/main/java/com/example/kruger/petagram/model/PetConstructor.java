package com.example.kruger.petagram.model;

import android.content.ContentValues;
import android.content.Context;

import com.example.kruger.petagram.R;
import com.example.kruger.petagram.db.Database;
import com.example.kruger.petagram.db.DatabaseConstants;

import java.util.ArrayList;

/**
 * Created by Kruger on 10/1/2018.
 */

public class PetConstructor {

    private Context context;

    public PetConstructor(Context context) {
        this.context = context;
    }

    public ArrayList<Pet> getPets() {
        Database db = new Database(context);
        ArrayList<Pet> pets = db.getAllPets();
        if (pets.size() > 0) {
            return pets;
        }
        else {
            insertDummiesPets(db);
            return  db.getAllPets();
        }
    }

    public void insertDummiesPets(Database db){
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseConstants.TABLE_PET_NAME, "Bobby");
        contentValues.put(DatabaseConstants.TABLE_PET_IMAGEN, R.drawable.first_pet);
        contentValues.put(DatabaseConstants.TABLE_PET_RATING, 0);
        db.insertPet(contentValues);

        contentValues.put(DatabaseConstants.TABLE_PET_NAME, "Scooby");
        contentValues.put(DatabaseConstants.TABLE_PET_IMAGEN, R.drawable.second_pet);
        contentValues.put(DatabaseConstants.TABLE_PET_RATING, 0);
        db.insertPet(contentValues);

        contentValues.put(DatabaseConstants.TABLE_PET_NAME, "Max");
        contentValues.put(DatabaseConstants.TABLE_PET_IMAGEN, R.drawable.third_pet);
        contentValues.put(DatabaseConstants.TABLE_PET_RATING, 0);
        db.insertPet(contentValues);

        contentValues.put(DatabaseConstants.TABLE_PET_NAME, "Rufo");
        contentValues.put(DatabaseConstants.TABLE_PET_IMAGEN, R.drawable.fourth_pet);
        contentValues.put(DatabaseConstants.TABLE_PET_RATING, 0);
        db.insertPet(contentValues);

        contentValues.put(DatabaseConstants.TABLE_PET_NAME, "Bethoven");
        contentValues.put(DatabaseConstants.TABLE_PET_IMAGEN, R.drawable.fifth_pet);
        contentValues.put(DatabaseConstants.TABLE_PET_RATING, 0);
        db.insertPet(contentValues);

        contentValues.put(DatabaseConstants.TABLE_PET_NAME, "Lassie");
        contentValues.put(DatabaseConstants.TABLE_PET_IMAGEN, R.drawable.sixth_pet);
        contentValues.put(DatabaseConstants.TABLE_PET_RATING, 0);
        db.insertPet(contentValues);

        contentValues.put(DatabaseConstants.TABLE_PET_NAME, "Rudolf");
        contentValues.put(DatabaseConstants.TABLE_PET_IMAGEN, R.drawable.seventh_pet);
        contentValues.put(DatabaseConstants.TABLE_PET_RATING, 0);
        db.insertPet(contentValues);

        contentValues.put(DatabaseConstants.TABLE_PET_NAME, "Lulu");
        contentValues.put(DatabaseConstants.TABLE_PET_IMAGEN, R.drawable.eighth_pet);
        contentValues.put(DatabaseConstants.TABLE_PET_RATING, 0);
        db.insertPet(contentValues);
    }

    public long updateRatingToPet(Pet pet){
        Database database = new Database(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseConstants.TABLE_PET_RATING, pet.getRating());
        return database.updateRating(contentValues ,pet);
    }

    public ArrayList<Pet> getFavoritePets(int numberFavoritePets) {
        Database database = new Database(context);
        return database.getFavoritePets(numberFavoritePets);
    }

}
