package com.tec.diegogranados.construtec;


import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.tec.diegogranados.construtec.R.layout.obtener_fecha;


public class Agregar_Etapa extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Intent siguiente;
    Button Fecha_Inicio;
    Button Fecha_Finalizacion;
    Button Agregar;

    String Nombre_Usuario;
    String Nivel_de_Acceso;

    Calendar myCalendar;

    Lista_Adaptador_Pedido adapter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar__etapa);
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

        Fecha_Inicio = (Button) findViewById(R.id.Button_Fecha_Inicio_Nueva_Etapa);
        Fecha_Finalizacion = (Button) findViewById(R.id.Button_Fecha_Fin_Nueva_Etapa);
        Agregar = (Button) findViewById(R.id.Button_Agregar_Etapa);

        String[][] misPedidos ={{"Arena","5"}
                ,{"Piedra","10"},{"Canoas","100"}
                ,{"Martillos","15"},{"Serruchos","0"}
                ,{"Madera","1"},{"Cemento","22"}
                ,{"Palas","37"},{"Desatorador","21"}
                ,{"Tubo cuadrado","1001"},{"Guantes","63"}
                ,{"Tuberia","32"},{"Tubo Redondo","0"}
                ,{"Taipe","14"},{"Compresores","0"}
                ,{"Ceramica","2"},{"Espuma","0"}};

        ArrayList<Lista_Entrada_Pedido> datos = new ArrayList<Lista_Entrada_Pedido>();
        for(int i = 0; i < misPedidos.length; i++){
            if((misPedidos[i][1]).equals("0")){
                datos.add(new Lista_Entrada_Pedido(R.mipmap.ic_cancel_black_24dp,misPedidos[i][0],misPedidos[i][1]));
            }
            else{
                datos.add(new Lista_Entrada_Pedido(R.mipmap.ic_check_black_24dp,misPedidos[i][0],misPedidos[i][1]));
            }
        }

        final ListView lista = (ListView) findViewById(R.id.List_View_Agregar_Etapa);

        this.Set_Adapter(lista, datos);
        this.AccionItemLista(lista);

        this.botonesFecha();
        this.botonAgregar();
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
                Toast.makeText(Agregar_Etapa.this, "Jaja", Toast.LENGTH_SHORT).show();
                //se oculta el EditText
                searchView.setQuery("", false);
                searchView.setIconified(true);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                Agregar_Etapa.this.adapter.getFilter().filter(newText);
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
            Toast toast = Toast.makeText(Agregar_Etapa.this, "nada que decir", Toast.LENGTH_LONG);
            toast.show();
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
            siguiente = new Intent(Agregar_Etapa.this, Menu_Principal.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);
        }
        else if (id == R.id.nav_Ing_Arq) {
            siguiente = new Intent(Agregar_Etapa.this, Ingenieros_Y_Arquitectos.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);
        } else if (id == R.id.nav_Clientes) {

        } else if (id == R.id.nav_Materiales) {
            siguiente = new Intent(Agregar_Etapa.this, Material.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Obras) {
            siguiente = new Intent(Agregar_Etapa.this, Obra.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Pedidos) {
            siguiente = new Intent(Agregar_Etapa.this, Pedido.class);
            siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
            siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
            startActivity(siguiente);

        } else if (id == R.id.nav_Salir) {
            siguiente = new Intent(Agregar_Etapa.this, Main.class);
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
                Toast toast = Toast.makeText(Agregar_Etapa.this, texto, Toast.LENGTH_LONG);
                toast.show();

                LayoutInflater layoutInflater = LayoutInflater.from(Agregar_Etapa.this);
                View promptView = layoutInflater.inflate(R.layout.pop_up_cantidad, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Agregar_Etapa.this);
                alertDialogBuilder.setView(promptView);

//                final EditText editText = (EditText) promptView.findViewById(R.id.Edit_Text_Comentar);
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

    }

    private void botonesFecha(){
        this.Fecha_Inicio.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(Agregar_Etapa.this);
                View promptView = layoutInflater.inflate(obtener_fecha, null);
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Agregar_Etapa.this);
                alertDialogBuilder.setView(promptView);

//                final EditText editText = (EditText) promptView.findViewById(R.id.Edit_Text_Comentar);
                // setup a dialog window
                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                DatePicker fecha = (DatePicker) findViewById(R.id.Date_Picker);


//                                FrameLayout rootLayout = (FrameLayout)findViewById(android.R.id.content);
//
//
//
//                                DatePicker fecha = (DatePicker) findViewById(R.id.Date_Picker);
                                String Ano = Integer.toString(fecha.getYear());
//                                String Mes = Integer.toString(fecha.getMonth()+1);
//                                String Dia = Integer.toString(fecha.getDayOfMonth());
//                                TextView FechaInicio = (TextView) findViewById(R.id.Fecha_Inicio_Nueva_Etapa);
//                                FechaInicio.setText(Dia+"/"+Mes+"/"+Ano);
//                                FechaInicio.setText("Jasa");

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

        this.Fecha_Finalizacion.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(Agregar_Etapa.this);
                View promptView = layoutInflater.inflate(obtener_fecha, null);
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Agregar_Etapa.this);
                alertDialogBuilder.setView(promptView);

//                final EditText editText = (EditText) promptView.findViewById(R.id.Edit_Text_Comentar);
                // setup a dialog window
                alertDialogBuilder.setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


//                                FrameLayout rootLayout = (FrameLayout)findViewById(android.R.id.content);
//
//
//
//                                DatePicker fecha = (DatePicker) findViewById(R.id.Date_Picker);
//                                String Ano = Integer.toString(fecha.getYear());
//                                String Mes = Integer.toString(fecha.getMonth()+1);
//                                String Dia = Integer.toString(fecha.getDayOfMonth());
//                                TextView FechaInicio = (TextView) findViewById(R.id.Fecha_Inicio_Nueva_Etapa);
//                                FechaInicio.setText(Dia+"/"+Mes+"/"+Ano);
//                                FechaInicio.setText("Jasa");

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
    }

    private void botonAgregar(){
        Agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "Falta agregar la etapa", Toast.LENGTH_SHORT);
                toast.show();
                siguiente = new Intent(Agregar_Etapa.this, Obra.class);
                siguiente.putExtra(String.valueOf(R.string.Nombre_Paso_Parametros),Nombre_Usuario);
                siguiente.putExtra(String.valueOf(R.string.Acceso_Paso_Parametros),Nivel_de_Acceso);
                startActivity(siguiente);
            }
        });
    }
}

/*  Acordar integrar a las referencias el uso de la clase Adapter de:
http://jarroba.com/listview-o-listado-en-android/
el 18/10/16 a la 1:40am
*/


/*
Acordar integrar a las referencias
http://tublogdelprogramador.blogspot.com/2014/05/popup-para-seleccionar-una-fecha.html
el 21/10/16 5:30pm
 */
