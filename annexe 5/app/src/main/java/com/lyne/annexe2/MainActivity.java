package com.lyne.annexe2;

import android.app.AlertDialog;
import android.icu.text.DecimalFormat;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.PointerIcon;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Ecouteur ec;
    Compte comptechoisi;
    Button boutonValider;
    Button bouttonEnvoyer;
    EditText courriel,transfert;

    TextView champSolde;
    ArrayList<String> choix;

    Spinner champNomCompte;
    int solde;


    DecimalFormat ft = new DecimalFormat("#,###.00");

    HashMap<String,Compte> hm = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bouttonEnvoyer = findViewById(R.id.boutonEnvoyer);
        boutonValider = findViewById(R.id.boutonValider);
        champSolde = findViewById(R.id.solde);
        courriel = findViewById(R.id.mailAdress);
        transfert = findViewById(R.id.montant);
        champNomCompte = findViewById(R.id.compteChoisi);
        choix = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,choix);


        hm.put("cheques",new Compte("cheque",1800));
        hm.put("épargnes",new Compte("épargne",200));
        hm.put("épargnesplus",new Compte("épargne plus",490));
        choix.addAll(hm.keySet()); // mettre les cles dans le choix et le choix dans le spinner
        champNomCompte.setAdapter(adapter);
        // étape 1

        ec = new Ecouteur();
        // étape 2

        bouttonEnvoyer.setOnClickListener(ec);
        champNomCompte.setOnItemSelectedListener(ec);

    }

    //étape 3

    private class Ecouteur implements View.OnClickListener, AdapterView.OnItemSelectedListener {

        @Override
        public void onClick(View source) {


                String mail = courriel.getText().toString();


               if(!mail.isEmpty()){
                   String montant = String.valueOf(transfert.getText().toString());
                   int transfertt = Integer.parseInt(montant) ;

                   // a faire
                   if(comptechoisi.transfert(transfertt)){
                       champSolde.setText(ft.format(comptechoisi.getSolde()));
                       transfert.setText("");

                   }else{
                       transfert.setText("");
                       new AlertDialog.Builder(MainActivity.this).
                               setTitle("ATTENTION")
                               .setMessage("Il manque de fonds")
                               .show();
                   }

               }else{
                   courriel.setText("Indiquer un destinataire ");
               }


        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            // methode 1
            String texte = parent.getAdapter().getItem(position).toString();
            // methode 2
            String texte2 = (String) parent.getSelectedItem();
           // methode 3
           String texte3 = choix.get(position);
           //methode 4
            TextView temp =(TextView) view;
            String texte4 = temp.getText().toString();
            // methode simple
            String texte5 = (String) parent.getItemAtPosition(position);
            // trouver le commpte si je connais le nom du compte
            comptechoisi = hm.get(texte4);
            // afficher le solde du ciompte dans le champSolde
            champSolde.setText(ft.format(comptechoisi.getSolde()));




            Toast.makeText(MainActivity.this,"",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }


    }