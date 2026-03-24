package com.kawtar.tp1;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DialogTrait extends Dialog {
    MainActivity m;
    SeekBar seekbar;
    Button ok;
    TextView taille;
    Ecouteur e;
    public DialogTrait(@NonNull Context context) {
        super(context);
        m = (MainActivity) context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trait_largeur);
        e = new Ecouteur();
        seekbar = findViewById(R.id.seekBar);
        ok = findViewById(R.id.button);
        taille  = findViewById(R.id.taille);
        seekbar.setOnSeekBarChangeListener(e);
        ok.setOnClickListener(e);

    }
    private class Ecouteur implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
        @Override
        public void onClick(View v) {
            if(v == ok){
                dismiss();
            }
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            taille.setText(String.valueOf(progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            m.changerWidth(seekBar.getProgress());
        }
    }
}

