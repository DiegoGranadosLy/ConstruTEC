package com.tec.diegogranados.construtec;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Info_Obra extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Intent siguiente;
    Button b_agregar_Etapa;
    Button b_generar_presupuesto;
    String Nombre_Usuario;
    String Nivel_de_Acceso;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.info__obra);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Nombre_Usuario = (String) getIntent().getExtras().getSerializable(String.valueOf(R.string.Nombre_Paso_Parametros));
        Nivel_de_Acceso= (String) getIntent().getExtras().getSerializable(String.valueOf(R.string.Acceso_Paso_Parametros));

        this.Crear_List_View();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);

        //Inicio de la opcion de busqueda
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        //permite modificar el hint que el EditText muestra por defecto
        searchView.setQueryHint(getText(R.string.action_search));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(Info_Obra.this, query, Toast.LENGTH_SHORT).show();
                searchView.setQuery("", false);
                searchView.setIconified(true);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                Info_Obra.this.adapter.getFilter().filter(newText);
                return true;
            }
        });
        //Fin de la accion de busqueda
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent siguiente;
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Home) {
            siguiente = new Intent(Info_Obra.this, Menu_Principal.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Ing_Arq) {
            siguiente = new Intent(Info_Obra.this, Ingenieros_Y_Arquitectos.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Clientes) {
            siguiente = new Intent(Info_Obra.this, Clientes.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Materiales) {
            siguiente = new Intent(Info_Obra.this, Material.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Obras) {
            siguiente = new Intent(Info_Obra.this, Obra.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Pedidos) {
            siguiente = new Intent(Info_Obra.this, Pedido.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Salir) {
            siguiente = new Intent(Info_Obra.this, Main.class);
            startActivity(siguiente);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void Crear_List_View(){
        ListView listview = (ListView) findViewById(R.id.list_View_Info_Obra);
        String[] values = new String[] { "Trabajo Preliminar", "Cimientos", "Paredes",
                "Concreto Reforzado", "Techos", "Cielos", "Repello", "Entrepisos",
                "Pisos", "Enchapes", "Instalacion Pluvial", "Instalacion Sanitaria",
                "Instalacion Electrica", "Puertas","Cerrajeria","Ventanas","Closets",
                "Mueble Pintura","Escaleras"};
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        this.Event(listview);
    }


    private void Event(ListView listview){
        final String Obra = getIntent().getStringExtra("Obra");
        b_agregar_Etapa = (Button) findViewById(R.id.Button_Nueva_Etapa);
        b_generar_presupuesto = (Button) findViewById(R.id.Button_Presupuesto);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                siguiente = new Intent(Info_Obra.this, Info_por_Pedido_de_Obra.class);
                siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
                siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
                siguiente.putExtra("Obra",Obra);
                siguiente.putExtra("Etapa",item);
                startActivity(siguiente);
            }
        });

        TextView Nombre_Obra = (TextView) findViewById(R.id.Nombre_Obra_Info_Obra);
        TextView Ing_Arq     = (TextView) findViewById(R.id.Ing_Arq_Info_Obra);
        TextView Cliente     = (TextView) findViewById(R.id.Cliente_Info_Obra);
        Nombre_Obra.setText(Obra);

        b_agregar_Etapa.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                siguiente = new Intent(Info_Obra.this, Agregar_Etapa.class);
                siguiente.putExtra("Obra", Obra);
                siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
                siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
                startActivity(siguiente);
            }
        });

        b_generar_presupuesto.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(Info_Obra.this);
                builder1.setTitle("Presupuesto de la Obra");
                builder1.setMessage("Mi comentario de esta etapa..!!");
                builder1.setCancelable(true);
                builder1.setNeutralButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
    }

}
