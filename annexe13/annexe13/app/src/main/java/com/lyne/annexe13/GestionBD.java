package com.lyne.annexe13;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GestionBD extends SQLiteOpenHelper {
    private static GestionBD instance;
    private SQLiteDatabase database;
    public static GestionBD getInstance(Context context){
        if(instance == null)
            instance = new GestionBD(context);
        return instance;
    }
    public GestionBD(@Nullable Context context) {
        super(context, "annexe13", null, 1);
        database = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE evaluation(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nom TEXT, microbrasserie TEXT, etoile INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS evaluation");
        onCreate(db);
    }
    public void ajouterEvaluation(Evaluation e){
        ContentValues cv = new ContentValues();
        cv.put("nom", e.getNom());
        cv.put("microbrasserie", e.getMicrobrasserie());
        cv.put("etoile", e.getEtoile());
        database.insert("evaluation", null, cv);
    }

    public ArrayList<String> retournerMeilleure() throws Exception{
        ArrayList<String> listeEvaluations = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT nom FROM evaluation ORDER BY etoile DESC LIMIT 3",null);
        while (cursor.moveToNext()){
            listeEvaluations.add(cursor.getString(0));
            //select * => getString(1)
            //select microbrasserie => getString(1)
        }
        if(listeEvaluations.size() < 3){
            throw new Exception("Moins de 3 evaluations");
        }
        cursor.close();
        return listeEvaluations;
    }
}
