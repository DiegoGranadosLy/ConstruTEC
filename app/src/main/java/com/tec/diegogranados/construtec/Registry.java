package com.tec.diegogranados.construtec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registry extends AppCompatActivity {

    Button ingresar;
    EditText editTextNombre;
    EditText editTextCedula;
    EditText editTextTelefono;
    EditText editTextContrasena;
    Intent siguiente;
    Comunicador comunicador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registry);
        editTextNombre = (EditText)findViewById(R.id.nombre_Registro);
        editTextCedula = (EditText)findViewById(R.id.cedula_Registro);
        editTextTelefono = (EditText)findViewById(R.id.telefono_Registro);
        editTextContrasena = (EditText)findViewById(R.id.contrasena_Registro);
        comunicador = new Comunicador();
        ingresar = (Button)findViewById(R.id.button_Registry_Registrar);

        ingresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(     editTextNombre.getText().toString().equals("") &&
                        editTextCedula.getText().toString().equals("") &&
                        editTextTelefono.getText().toString().equals("") &&
                        editTextContrasena.getText().toString().equals("")) {

                    Toast toast = Toast.makeText(getApplicationContext(), R.string.Bienvenido +
                            editTextNombre.getText().toString(), Toast.LENGTH_SHORT);
                    toast.show();
                    siguiente = new Intent(Registry.this, Menu_Principal.class);
                    siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),"Nombre del mae");
                    siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),"Nivel de acceso del Usuario");
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
