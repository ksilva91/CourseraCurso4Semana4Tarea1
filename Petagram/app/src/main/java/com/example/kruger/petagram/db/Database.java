package com.example.kruger.petagram.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.kruger.petagram.model.Pet;

import java.util.ArrayList;

/**
 * Created by Kruger on 9/1/2018.
 */

public class Database extends SQLiteOpenHelper {

    private Context context;

    public Database(Context context) {
        super(context, DatabaseConstants.DATABASE_NAME, null, DatabaseConstants.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String queryCreatePetTable = "CREATE TABLE " + DatabaseConstants.TABLE_PET + " ( " +
                                    DatabaseConstants.TABLE_PET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                    DatabaseConstants.TABLE_PET_NAME + " TEXT, " +
                                    DatabaseConstants.TABLE_PET_IMAGEN + " INTEGER, " +
                                    DatabaseConstants.TABLE_PET_RATING + " INTEGER " +
                                    ")";
        sqLiteDatabase.execSQL(queryCreatePetTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseConstants.TABLE_PET);
        onCreate(sqLiteDatabase);
    }

    public ArrayList<Pet> getAllPets(){
        ArrayList<Pet> pets = new ArrayList<>();
        String query = "SELECT * FROM " + DatabaseConstants.TABLE_PET;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            Pet pet = new Pet();
            pet.setId(registros.getInt(0));
            pet.setName(registros.getString(1));
            pet.setImage(registros.getInt(2));
            pet.setRating(registros.getInt(3));
            pets.add(pet);
        }
        db.close();
        return pets;
    }

    public void insertPet(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DatabaseConstants.TABLE_PET,null, contentValues);
        db.close();
    }

    public long updateRating(ContentValues contentValues, Pet pet) {
        long result;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        result = sqLiteDatabase.update(DatabaseConstants.TABLE_PET, contentValues, DatabaseConstants.TABLE_PET_ID + " = ?", new String[] { String.valueOf(pet.getId()) });
        sqLiteDatabase.close();
        return result;
    }

    public ArrayList<Pet> getFavoritePets(int numberPets) {
        ArrayList<Pet> pets = new ArrayList<>();
        String query = "SELECT * FROM " + DatabaseConstants.TABLE_PET + " ORDER BY " + DatabaseConstants.TABLE_PET_RATING + " DESC LIMIT " + numberPets;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        while (cursor.moveToNext()) {
            Pet pet = new Pet();
            pet.setId(cursor.getInt(0));
            pet.setName(cursor.getString(1));
            pet.setImage(cursor.getInt(2));
            pet.setRating(cursor.getInt(3));
            pets.add(pet);
        }
        sqLiteDatabase.close();
        return pets;
    }
}
