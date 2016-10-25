package com.tec.diegogranados.construtec;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Pedido extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String Nombre_Usuario;
    String Nivel_de_Acceso;
    Intent siguiente;
    ListView lista;
    Lista_Adaptador_Pedido adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pedido);
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

        String[][] misPedidos ={{"Tecnologico","Cimientos","Concluido"}
                ,{"UNA","Paredes","Incompleto"},{"UCR","Cielo","Concluido"}
                ,{"HP","Instalacion Electrica","Incompleto"},{"Teradyne","Instalacion Pluvial","Incompleto"}
                ,{"Casa Diego","Techo","Concluido"},{"Casa Alerto","Mueble Pintura","Incompleto"}
                ,{"El Aguila","Tuberia","75","Incompleto"},{"Casa Enso","Canoas","Incompleto"}
                ,{"UNA","Paredes","Incompleto"},{"UCR","Cielo","Concluido"}
                ,{"HP","Instalacion Electrica","Incompleto"},{"Teradyne","Instalacion Pluvial","Incompleto"}
                ,{"Casa Diego","Techo","Concluido"},{"Casa Alerto","Mueble Pintura","Incompleto"}
                ,{"El Aguila","Tuberia","75","Incompleto"},{"Casa Enso","Canoas","Incompleto"}};

        ArrayList<Lista_Entrada_Pedido> datos = new ArrayList<Lista_Entrada_Pedido>();
        for(int i = 0; i < misPedidos.length; i++){
            if((misPedidos[i][2]).equals("Concluido")){
                datos.add(new Lista_Entrada_Pedido(R.mipmap.ic_check_black_24dp,misPedidos[i][0],misPedidos[i][1]));
            }
            else{
                datos.add(new Lista_Entrada_Pedido(R.mipmap.ic_cancel_black_24dp,misPedidos[i][0],misPedidos[i][1]));
            }
        }

        lista = (ListView) findViewById(R.id.List_View_Pedidos);

        this.Set_Adapter(lista, datos);
        this.AccionItemLista(lista);
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
        searchView.setQueryHint(getText(R.string.action_search));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(Pedido.this, query, Toast.LENGTH_SHORT).show();
                //se oculta el EditText
                searchView.setQuery("", false);
                searchView.setIconified(true);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                Pedido.this.adapter.getFilter().filter(newText);
//                Pedido.this.lista.getAdapter();
                return true;
            }
        });
        //Fin de la accion de busqueda

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
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
            siguiente = new Intent(Pedido.this, Menu_Principal.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Ing_Arq) {
            siguiente = new Intent(Pedido.this, Ingenieros_Y_Arquitectos.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Clientes) {
            siguiente = new Intent(Pedido.this, Clientes.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Materiales) {
            siguiente = new Intent(Pedido.this, Material.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Obras) {
            siguiente = new Intent(Pedido.this, Obra.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Pedidos) {
            siguiente = new Intent(Pedido.this, Pedido.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Salir) {
            siguiente = new Intent(Pedido.this, Main.class);
            startActivity(siguiente);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void Set_Adapter(ListView lista, ArrayList<Lista_Entrada_Pedido> datos){

        adapter = new Lista_Adaptador_Pedido(this, R.layout.pedido_list_view, datos){
            @Override
            public void onEntrada(Object entrada, View view) {
                TextView texto_superior_entrada = (TextView) view.findViewById(R.id.Obra_Pedidos);
                texto_superior_entrada.setText(((Lista_Entrada_Pedido) entrada).get_Titulo());

                TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.Etapa_Pedidos);
                texto_inferior_entrada.setText(((Lista_Entrada_Pedido) entrada).get_Descripcion());

                ImageView imagen_entrada = (ImageView) view.findViewById(R.id.Check);
                imagen_entrada.setImageResource(((Lista_Entrada_Pedido) entrada).get_idImagen());
            }
        };

        lista.setAdapter(adapter);
    }

    private void AccionItemLista(ListView lista){
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
                Lista_Entrada_Pedido elegido = (Lista_Entrada_Pedido) pariente.getItemAtPosition(posicion);

                CharSequence texto = "Seleccionado: " + elegido.get_Titulo() +": "+ elegido.get_Descripcion();
                Toast toast = Toast.makeText(Pedido.this, texto, Toast.LENGTH_LONG);
                toast.show();

                siguiente = new Intent(Pedido.this, Info_por_Pedido_de_Obra.class);
                siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
                siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
                siguiente.putExtra("Obra", elegido.get_Titulo());
                siguiente.putExtra("Etapa", elegido.get_Descripcion());

                startActivity(siguiente);

            }
        });
    }
}


/*  Acordar integrar a las referencias el uso de la clase Adapter de:
http://jarroba.com/listview-o-listado-en-android/
el 18/10/16 a la 1:40am
*/