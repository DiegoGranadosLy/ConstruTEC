package com.tec.diegogranados.construtec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

public class Log_In extends AppCompatActivity {

    Button ingresar;
    EditText editTextNombre;
    EditText editTextContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log__in);

        ingresar = (Button)findViewById(R.id.button_Ingresar_Log_In);
        editTextNombre = (EditText)findViewById(R.id.nombre_Log_In);
        editTextContrasena = (EditText)findViewById(R.id.contrasena_Log_In);

        ingresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(editTextContrasena.getText().toString().equals("")
                        && editTextNombre.getText().toString().equals("")){

                    Toast toast = Toast.makeText(getApplicationContext(), R.string.Bienvenido
                        +editTextNombre.getText().toString(), Toast.LENGTH_SHORT);
                    toast.show();
                    Intent siguiente = new Intent(Log_In.this, Menu_Principal.class);
                    siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),"Nombre del Mae");
                    siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),"Nivel de Acceso del Usuario");
                    startActivity(siguiente);
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.Invalido, Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });

    }
}
