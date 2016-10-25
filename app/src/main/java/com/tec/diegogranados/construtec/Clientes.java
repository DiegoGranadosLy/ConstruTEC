package com.tec.diegogranados.construtec;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Clientes extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String Nombre_Usuario;
    String Nivel_de_Acceso;
    ListView lista;
    Lista_Adaptador_Tabla_General adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.clientes);
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

        this.Crear_Tabla();
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
                Toast.makeText(Clientes.this, query, Toast.LENGTH_SHORT).show();
                //se oculta el EditText
                searchView.setQuery("", false);
                searchView.setIconified(true);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                Clientes.this.adapter.getFilter().filter(newText);
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
        if (id == R.id.action_search)
            return true;


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent siguiente;
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_Home) {
            siguiente = new Intent(Clientes.this, Menu_Principal.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);
        }
        else if (id == R.id.nav_Ing_Arq) {
            siguiente = new Intent(Clientes.this, Ingenieros_Y_Arquitectos.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);
        } else if (id == R.id.nav_Clientes) {

        } else if (id == R.id.nav_Materiales) {
            siguiente = new Intent(Clientes.this, Material.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Obras) {
            siguiente = new Intent(Clientes.this, Obra.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Pedidos) {
            siguiente = new Intent(Clientes.this, Pedido.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Salir) {
            siguiente = new Intent(Clientes.this, Main.class);
            startActivity(siguiente);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void Crear_Tabla(){
        String[][] misIng ={{"NOMBRE","CEDULA","TELEFONO"},{"Mauricio","304860692","25920054"}
                ,{"Diego","304860692","25920054"},{"Dennis","304860692","25920054"}
                ,{"Marcela","304860692","25920054"},{"David","304860692","25920054"}
                ,{"Sury","304860692","25920054"},{"Lucy","304860692","25920054"}
                ,{"Anibal","304860692","25920054"},{"Leopoldo","304860692","25920054"}
                ,{"Sergio","304860692","25920054"},{"Carolina","304860692","25920054"}
                ,{"Diego","304860692","25920054"},{"Dennis","304860692","25920054"}
                ,{"Marcela","304860692","25920054"},{"David","304860692","25920054"}
                ,{"Sury","304860692","25920054"},{"Lucy","304860692","25920054"}
                ,{"Anibal","304860692","25920054"},{"Leopoldo","304860692","25920054"}
                ,{"Sergio","304860692","25920054"},{"Carolina","304860692","25920054"}};
        ArrayList<Lista_Entrada_Tabla_General> datos = new ArrayList<Lista_Entrada_Tabla_General>();
        for(int i = 0; i < misIng.length; i++){
            datos.add(new Lista_Entrada_Tabla_General(misIng[i][0],misIng[i][1],misIng[i][2],""));
        }

        lista = (ListView) findViewById(R.id.Tabla_Clientes);

        this.Set_Adapter(lista, datos);
        this.AccionItemLista(lista);
    }

    private void Set_Adapter(ListView lista, ArrayList<Lista_Entrada_Tabla_General> datos){

        adapter = new Lista_Adaptador_Tabla_General(this, R.layout.fila_tablas, datos){
            @Override
            public void onEntrada(Object entrada, View view) {
                TextView texto_Nombre = (TextView) view.findViewById(R.id.colum_uno);
                texto_Nombre.getLayoutParams().width = 500;
                texto_Nombre.setText(((Lista_Entrada_Tabla_General) entrada).getNombre());

                TextView texto_Codigo = (TextView) view.findViewById(R.id.colum_dos);
                texto_Codigo.getLayoutParams().width = 500;
                texto_Codigo.setText(((Lista_Entrada_Tabla_General) entrada).getCodigo());

                TextView texto_Cantidad_Cedula = (TextView) view.findViewById(R.id.column_tres);
                texto_Cantidad_Cedula.getLayoutParams().width = 500;
                texto_Cantidad_Cedula.setText(((Lista_Entrada_Tabla_General) entrada).getCedula_Cantidad());

                TextView texto_Precio_Telefono = (TextView) view.findViewById(R.id.column_cuatro);
                texto_Precio_Telefono.getLayoutParams().width = 0;
                texto_Precio_Telefono.setText(((Lista_Entrada_Tabla_General) entrada).getTelefono_Precio());
            }
        };

        lista.setAdapter(adapter);
    }

    private void AccionItemLista(ListView lista){
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
                Lista_Entrada_Tabla_General elegido = (Lista_Entrada_Tabla_General) pariente.getItemAtPosition(posicion);

                CharSequence texto = "Seleccionado: " + elegido.getNombre() +": "+ elegido.getCodigo();
                Toast toast = Toast.makeText(Clientes.this, texto, Toast.LENGTH_LONG);
                toast.show();

            }
        });
    }
}


// http://www.tutorialsbuzz.com/2014/08/filter-custom-listviewbaseadapter.html
// 23/10/16 11:30am


//Desarrollar en la noche el listView con multicolumnas para las tablas de informacion General
// Link ejemplo a seguir http://techlovejump.com/android-multicolumn-listview/