package com.tec.diegogranados.construtec;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Comentarios_por_Obra extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String Nombre_Usuario;
    String Nivel_de_Acceso;
    Intent siguiente;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.comentarios_por_obra);
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
                Toast.makeText(Comentarios_por_Obra.this, "Jaja", Toast.LENGTH_SHORT).show();
                //se oculta el EditText
                searchView.setQuery("", false);
                searchView.setIconified(true);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                Comentarios_por_Obra.this.adapter.getFilter().filter(newText);
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Home) {
            siguiente = new Intent(Comentarios_por_Obra.this, Menu_Principal.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Ing_Arq) {
            siguiente = new Intent(Comentarios_por_Obra.this, Ingenieros_Y_Arquitectos.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Clientes) {
            siguiente = new Intent(Comentarios_por_Obra.this, Clientes.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Materiales) {
            siguiente = new Intent(Comentarios_por_Obra.this, Material.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Obras) {
            siguiente = new Intent(Comentarios_por_Obra.this, Obra.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Pedidos) {
            siguiente = new Intent(Comentarios_por_Obra.this, Pedido.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Salir) {
            siguiente = new Intent(Comentarios_por_Obra.this, Main.class);
            startActivity(siguiente);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void Crear_List_View(){
        ListView listview = (ListView) findViewById(R.id.list_View_Obra);
        String[] values = new String[] { "Anita", "Pepito", "Juana de Arco",
                "Cristobal Colon", "Kevin Colon", "San Juan de Dios", "Pepe", "Maria",
                "Juan Alberto", "Pepita la del Barrio", "Don Luis", "Mario Gomez"
                , "Fernanda Zuñiga", "Armando Hoyos",
                "Pepe Santoran"};
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        this.MessageAlert(listview);
    }

    private void MessageAlert(ListView list_View){

        list_View.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {

                final String item = (String) parent.getItemAtPosition(position);

                AlertDialog.Builder builder1 = new AlertDialog.Builder(Comentarios_por_Obra.this);
                builder1.setTitle(item);
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