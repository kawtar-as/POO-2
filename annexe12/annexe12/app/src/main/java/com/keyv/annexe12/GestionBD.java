package com.keyv.annexe12;

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
        super(context, "annexe12", null, 1);
        ouvrirConnexionDB(); // creer la database  ou on aurait pu le faire dans le oncreate de l'activite
    }
    // c'est pas comme le onreate de activite, il est execute une seule fois  lorsque on installe sur le téléphone
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE inventeur ( _id INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT, origin TEXT, invention TEXT,  annee INTEGER)");
        ajouterInventeur(new Inventaire("Lazlo Biro","hongrie","Stylo a bille",1938),db);
        ajouterInventeur(new Inventaire("Benjamen Franklin","Etats-Unis","Paratonnerre",1752),db);
        ajouterInventeur(new Inventaire("Mary Anderson","Etats-Unis","Essuie-glace",1903),db);
        ajouterInventeur(new Inventaire("Grace Hopper","Etats-Unis","Compilateur",1952),db);
        ajouterInventeur(new Inventaire("Benoit Rouquayrot","Etats-Unis","Scaphandre",1864),db);


    }

    public void ajouterInventeur (Inventaire i , SQLiteDatabase db ){

        ContentValues cv =  new ContentValues(); // comme une hashmap
        cv.put("nom", i.getNom());
        cv.put("origin", i.getOrigine());
        cv.put("invention", i.getInvention());
        cv.put("annee", i.getAnnee());
        db.insert("inventeur",null,cv);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists inventeur");
        onCreate(db);
    }

    public void ouvrirConnexionDB(){
        database = this.getReadableDatabase();
    }

    public ArrayList<String>retournerInventions(){
        ArrayList<String>listeInventions = new ArrayList<>();
        Cursor cursor = database.rawQuery("select origin, invention from inventeur", null);
        while (cursor.moveToNext()){
            listeInventions.add(cursor.getString(1));
        }
        cursor.close();
        return listeInventions;
    }
    public boolean hasBonneReponse (String nom, String invention){
        String[] parametres = {nom,invention};
        Cursor c = database.rawQuery("select nom,invention FROM inventeur WHERE NOM = ? AND invemtion = ?",parametres);
        boolean reponse = c.moveToFirst();
        c.close();
        return reponse;
    }
}
