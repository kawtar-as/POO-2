package com.kawtar.tp2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GestionBD extends SQLiteOpenHelper {
    Context context;
    private static GestionBD instance;
    private SQLiteDatabase database;
    public static GestionBD getInstance(Context context){
        if(instance == null)
            instance = new GestionBD(context);
        return instance;
    }
    public GestionBD(@Nullable Context context) {
        super(context, "tp2", null, 1);
        this.context = context;
        ouvrirBD();
    }

    public int executerFichier(SQLiteDatabase db, int resourceID) throws IOException {
        int compteur = 0;
        InputStream insertStream = context.getResources().openRawResource(resourceID);
        BufferedReader br = new BufferedReader(new InputStreamReader(insertStream));
        while(br.ready()){
            String enonce = br.readLine();
            db.execSQL(enonce);
            compteur++;
        }
        br.close(); //toujours fermer le canal
        return compteur;
    }

    public void ouvrirBD(){
        database = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE lexique (ortho TEXT,`phon` TEXT,`lemme` TEXT,`cgram` TEXT,`genre` TEXT,`nombre` TEXT,`freqlemfilms` REAL,`freqlemlivres` REAL,`freqfilms` REAL,`freqlivres` REAL,`infover` TEXT,`nbhomogr` INTEGER,`nbhomoph` INTEGER,`islem` INTEGER,`nblettres` INTEGER,`nbphons` INTEGER,`cvcv` TEXT,`p_cvcv` TEXT,`voisorth` INTEGER,`voisphon` INTEGER,`puorth` INTEGER,`puphon` INTEGER,`syll` TEXT,`nbsyll` INTEGER,`cv_cv` TEXT,`orthrenv` TEXT,`phonrenv` TEXT,`orthosyll` TEXT)");
        db.execSQL("CREATE TABLE pointage( point INTEGER , date TEXT DEFAULT CURRENT_TIMESTAMP)");

        try {
            executerFichier(db, R.raw.data);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean motExist(String mot){
        String [] tab = {mot};
        Cursor c = database.rawQuery("select * from lexique where ortho = ?",tab);
        boolean rep = c.moveToFirst();
        c.close();
        return rep;
    }

    public void ajouterPointage(Pointage p ){
        ContentValues cv = new ContentValues();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//        String dateString = sdf.format(new Date());
        cv.put("point", p.getPoint());
        cv.put("date", p.getDate());
        database.insert("pointage", null, cv);

    }
}
