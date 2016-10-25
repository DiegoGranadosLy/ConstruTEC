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
import android.widget.TextView;
import android.widget.Toast;

public class Menu_Principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String Nombre_Usuario;
    String Nivel_de_Acceso;
    Intent siguiente;
    TextView Usuario;
    TextView Acceso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main__menu);
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

        Usuario = (TextView) findViewById(R.id.Usuario_Main_Menu);
        Acceso = (TextView) findViewById(R.id.Nivel_de_acceso_Main_Menu);
        Usuario.setText(Nombre_Usuario);
        Acceso.setText(Nivel_de_Acceso);
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
                if (query.startsWith("Material:")){
                    siguiente = new Intent(Menu_Principal.this, Buscar_Material.class);
                    siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
                    siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
                    siguiente.putExtra(String.valueOf(R.string.Busqueda_Paso_Parametros),query.replaceAll("Proyecto:",""));
                    startActivity(siguiente);
                }
                if (query.startsWith("material:")){
                    siguiente = new Intent(Menu_Principal.this, Buscar_Material.class);
                    siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
                    siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
                    siguiente.putExtra(String.valueOf(R.string.Busqueda_Paso_Parametros),query.replaceAll("proyecto:",""));
                    startActivity(siguiente);
                }
                if (query.startsWith("Proyecto:")){
                    siguiente = new Intent(Menu_Principal.this, Buscar_Proyecto.class);
                    siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
                    siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
                    siguiente.putExtra(String.valueOf(R.string.Busqueda_Paso_Parametros),query.replaceAll("Proyecto:",""));
                    startActivity(siguiente);
                }
                if (query.startsWith("proyecto:")){
                    siguiente = new Intent(Menu_Principal.this, Buscar_Proyecto.class);
                    siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
                    siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
                    siguiente.putExtra(String.valueOf(R.string.Busqueda_Paso_Parametros),query.replaceAll("proyecto:",""));
                    startActivity(siguiente);
                }
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
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

        } else if (id == R.id.nav_Ing_Arq) {
            siguiente = new Intent(Menu_Principal.this, Ingenieros_Y_Arquitectos.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Clientes) {
            siguiente = new Intent(Menu_Principal.this, Clientes.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Materiales) {
            siguiente = new Intent(Menu_Principal.this, Material.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Obras) {
            siguiente = new Intent(Menu_Principal.this, Obra.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Pedidos) {
            siguiente = new Intent(Menu_Principal.this, Pedido.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Salir) {
            siguiente = new Intent(Menu_Principal.this, Main.class);
            startActivity(siguiente);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
