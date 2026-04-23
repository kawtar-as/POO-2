package com.kawtar.annexe13;
import static android.provider.Contacts.SettingsColumns.KEY;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

// c'est un  singleton car on a besoin d'un seul objet de ce type pour ce projet
public class GestionBD extends SQLiteOpenHelper {
    private static GestionBD instance; // réference a lui meme
    private SQLiteDatabase database;

    public static GestionBD getInstance(Context contexte){

        if(instance == null)
            instance = new GestionBD(contexte);
        return instance;
    }

    // constructeur doit etre prive car singleton
    private GestionBD(@Nullable Context context) {
        super(context, "annexe13", null, 1);
        ouvrirConnexionDB(); // creer la database  ou on aurait pu le faire dans le oncreate de l'activite
    }
    // c'est pas comme le onreate de activite, il est execute une seule fois  lorsque on installe sur le téléphone
    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("CREATE TABLE evaluation ( _id INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT, microbrasserie TEXT,rate REAL)");
//        ajouterBiere(new Evaluation("Brise de lac",""),db);



    }

    public void ajouterBiere (Evaluation e ){

        ContentValues cv =  new ContentValues(); // comme une hashmap
        cv.put("nom", e.getNom());
        cv.put("microbrasserie",e.getMicrobrasserie());
        cv.put("rate",e.getRate());
        database.insert("evaluation",null,cv);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists evaluation");
        onCreate(db);
    }

    public void ouvrirConnexionDB(){
        database = this.getReadableDatabase();
    }
    public void fermerAcces(){
        database.close();
    }


    public ArrayList<String>retournerMeilleures() throws Exception {
        ArrayList<String>liste = new ArrayList<>();
        int i = 0;
        // GENRE TU VEUX LES ORDER ETC
        Cursor cursor = database.rawQuery("select nom from evaluation ORDER BY rate DESC LIMIT 3", null);
        while (cursor.moveToNext()){
            liste.add(cursor.getString(0));// dans l examen pourquoi paas 1 pca c a indique nom colonne
        }
        cursor.close();
        if(liste.size()<3){
            throw new Exception("moins de 3 évluations");
        }

        return liste;
    }

}
