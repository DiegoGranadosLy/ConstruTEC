package com.tec.diegogranados.construtec;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class Main extends AppCompatActivity {

    Button ingresar_Main;
    Button registro_Main;
    Intent accion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ingresar_Main = (Button) findViewById(R.id.button_Ingresar_Main);
        registro_Main = (Button) findViewById(R.id.button_Registrar_Main);

        ingresar_Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accion = new Intent(Main.this, Log_In.class);
                startActivity(accion);
            }
        });

        registro_Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accion = new Intent(Main.this, Registry.class);
                startActivity(accion);
            }
        });
    }
}
