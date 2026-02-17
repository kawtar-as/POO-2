package com.kawtar.annexe_4solutionnaire;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

        Ecouteur ec;
        LinearLayout main;
        TextView code;
        String passwd = "3261";
        String input = "";


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


        // le but c est mettre des ecouters sur les 10 bouttons au lieu de chacun
        code = findViewById(R.id.editTextNumberPassword);
        main = findViewById(R.id.main);
        //1ere etape
        ec = new Ecouteur();
        //2eme etape
        for (int i = 0; i < main.getChildCount() - 1; i++) { // on exclu le dernier enfant car on veut pas mettre d'écouteur dessus
            if (main.getChildAt(i) instanceof LinearLayout) { // exclur le text view du titre
                LinearLayout temp = (LinearLayout) main.getChildAt(i);
                for (int j = 0; j < temp.getChildCount(); j++) { // les enfants sont pouur la plupart des bouttons
                    if (temp.getChildAt(j) instanceof Button) { // on exclu les spaces
                        temp.getChildAt(j).setOnClickListener(ec);
                    }
                }
            }else if(main.getChildAt(i) instanceof Button){
                main.getChildAt(i).setOnClickListener(ec);
            }


        }
    }




    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View source) {
            Button boutonClic = (Button) source ; // on le transtype car on est certain que nos sources
            input += boutonClic.getText(); // on le transtype en boutton car on veut avoir acces a get text
            code.setText(input);

            if(input.length() == 4){
                if (input.equals(passwd)){
                    main.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.vert));
                    main.setBackgroundColor(Color.parseColor("#00ff00"));
                    //main.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.vert));

                }

                else {
                    main.setBackgroundColor(Color.RED);
                    input = "";
                }
            }
        }
    }
}
