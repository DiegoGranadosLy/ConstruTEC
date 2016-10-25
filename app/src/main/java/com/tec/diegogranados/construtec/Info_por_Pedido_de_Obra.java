package com.tec.diegogranados.construtec;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Info_por_Pedido_de_Obra extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Intent siguiente;
    Button b_comentar;
    Button b_comentarios;
    TextView Nombre_Obra;
    TextView Ing_Arq;
    TextView Cliente;
    TextView Fecha_Inicio;
    TextView Fecha_Fin;
    String Obra;
    String Etapa;
    String Nombre_Usuario;
    String Nivel_de_Acceso;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Obra = getIntent().getStringExtra("Obra");
        Etapa = getIntent().getStringExtra("Etapa");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_por_pedido_obra);
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
                Toast.makeText(Info_por_Pedido_de_Obra.this, "Jaja", Toast.LENGTH_SHORT).show();
                //se oculta el EditText
                searchView.setQuery("", false);
                searchView.setIconified(true);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                Info_por_Pedido_de_Obra.this.adapter.getFilter().filter(newText);
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
            siguiente = new Intent(Info_por_Pedido_de_Obra.this, Menu_Principal.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Ing_Arq) {
            siguiente = new Intent(Info_por_Pedido_de_Obra.this, Ingenieros_Y_Arquitectos.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Clientes) {
            siguiente = new Intent(Info_por_Pedido_de_Obra.this, Clientes.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Materiales) {
            siguiente = new Intent(Info_por_Pedido_de_Obra.this, Material.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Obras) {
            siguiente = new Intent(Info_por_Pedido_de_Obra.this, Obra.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Pedidos) {
            siguiente = new Intent(Info_por_Pedido_de_Obra.this, Pedido.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Salir) {
            siguiente = new Intent(Info_por_Pedido_de_Obra.this, Main.class);
            startActivity(siguiente);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void Crear_List_View(){
        ListView listview = (ListView) findViewById(R.id.list_View_Info_Obra);
        String[] values = new String[] { "Cemento", "ConcreMix", "Arena",
                "Piedra", "Silicon", "Tornillos", "Aguarraz", "Cola",
                "Zinc", "Agua", "Tuercas", "Cable Telefonico",
                "Algodon", "Tubo de 3 cuartos","Tubo Redondo","Vidrio","Desatorador",
                "Martillo","Llave Allen"};
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                Toast toast = Toast.makeText(getApplicationContext(), item + ": x unidades. $ x Dolares", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        this.Actividad();
    }

    private void Actividad (){
        TextView Nombre_Obra = (TextView) findViewById(R.id.Nombre_Obra);
        TextView Ing_Arq     = (TextView) findViewById(R.id.Ing_Arq);
        TextView Cliente     = (TextView) findViewById(R.id.Nombre_Cliente);
        TextView Fecha_Inicio= (TextView) findViewById(R.id.Fecha_Inicio);
        TextView Fecha_Fin   = (TextView) findViewById(R.id.Fecha_Fin);
        b_comentar    = (Button) findViewById(R.id.Button_Nuevo_Comentario);
        b_comentarios = (Button) findViewById(R.id.Button_Ver_Comentarios);

        Nombre_Obra.setText(Obra + ": " + Etapa);


        b_comentar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater = LayoutInflater.from(Info_por_Pedido_de_Obra.this);
                View promptView = layoutInflater.inflate(R.layout.comentarios, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Info_por_Pedido_de_Obra.this);
                alertDialogBuilder.setView(promptView);

                final EditText editText = (EditText) promptView.findViewById(R.id.Edit_Text_Comentar);
                // setup a dialog window
                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //editText.getText();
                            }
                        })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog alert = alertDialogBuilder.create();
                alert.show();
            }
        });

        b_comentarios.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                siguiente = new Intent(Info_por_Pedido_de_Obra.this, Comentarios_por_Obra.class);
                siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
                siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
                startActivity(siguiente);
            }
        });
    }


    private void Actividades (){
        Nombre_Obra = (TextView) findViewById(R.id.Nombre_Obra);
        Ing_Arq     = (TextView) findViewById(R.id.Ing_Arq);
        Cliente     = (TextView) findViewById(R.id.Nombre_Cliente);
        Fecha_Inicio= (TextView) findViewById(R.id.Fecha_Inicio);
        Fecha_Fin   = (TextView) findViewById(R.id.Fecha_Fin);
        b_comentar    = (Button) findViewById(R.id.Button_Nuevo_Comentario);
        b_comentarios = (Button) findViewById(R.id.Button_Ver_Comentarios);

        Nombre_Obra.setText(Obra + ": " + Etapa);

        this.Acciones_Botton();

    }

    private void Acciones_Botton(){
        b_comentar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater = LayoutInflater.from(Info_por_Pedido_de_Obra.this);
                View promptView = layoutInflater.inflate(R.layout.comentarios, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Info_por_Pedido_de_Obra.this);
                alertDialogBuilder.setView(promptView);

                final EditText editText = (EditText) promptView.findViewById(R.id.Edit_Text_Comentar);
                // setup a dialog window
                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //editText.getText();
                            }
                        })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog alert = alertDialogBuilder.create();
                alert.show();
            }
        });

        b_comentarios.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                siguiente = new Intent(Info_por_Pedido_de_Obra.this, Comentarios_por_Obra.class);
                siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
                siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
                startActivity(siguiente);
            }
        });
    }
}