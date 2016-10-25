package com.tec.diegogranados.construtec;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Buscar_Proyecto extends AppCompatActivity {

    String Nombre_Usuario;
    String Nivel_de_Acceso;
    String Busqueda;
    ArrayAdapter adapter;
    ListView listview;
    String[] values;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscar__proyecto);
        Nombre_Usuario = (String) getIntent().getExtras().getSerializable(String.valueOf(R.string.Nombre_Paso_Parametros));
        Nivel_de_Acceso= (String) getIntent().getExtras().getSerializable(String.valueOf(R.string.Acceso_Paso_Parametros));
        Busqueda= (String) getIntent().getExtras().getSerializable(String.valueOf(R.string.Busqueda_Paso_Parametros));

        this.Crear_List_Adapter();
    }

    private void Crear_List_Adapter(){
        listview = (ListView) findViewById(R.id.list_View_Buscar_Proyecto);
        values = new String[] { "Casa Enzo", "Casa Diego", "Abastecedor el Aguila",
                "El Halcon", "Vista Hermosa", "San Juan de Dios", "Tecnologico", "HP",
                "Teradyne", "UCR", "UNA", "ULICORI", "UCA", "UNED",
                "Biblioteca"};
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        this.Agregar_Accion(listview);

    }


    private void Agregar_Accion(ListView listview){

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                Intent siguiente;
                final String item = (String) parent.getItemAtPosition(position);
                siguiente = new Intent(Buscar_Proyecto.this, Info_Obra.class);
                siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
                siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
                siguiente.putExtra("Obra", item);
                startActivity(siguiente);
            }
        });
    }
}
