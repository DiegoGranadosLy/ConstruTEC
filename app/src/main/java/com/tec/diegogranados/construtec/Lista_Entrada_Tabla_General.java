package com.tec.diegogranados.construtec;

/**
 * Created by Diego Granados on 24/10/2016.
 */

public class Lista_Entrada_Tabla_General {
    private String Nombre;
    private String Codigo;
    private String Cedula_Cantidad;
    private String Telefono_Precio;

    public Lista_Entrada_Tabla_General(String pNombre, String pCodigo,
                                       String pCedula_Cantidad, String pTelefono_Precio){
        this.Nombre = pNombre;
        this.Codigo = pCodigo;
        this.Cedula_Cantidad = pCedula_Cantidad;
        this.Telefono_Precio = pTelefono_Precio;
    }

    public String getNombre(){
        return Nombre;
    }

    public String getCodigo(){
        return Codigo;
    }

    public String getCedula_Cantidad(){
        return Cedula_Cantidad;
    }

    public String getTelefono_Precio(){
        return Telefono_Precio;
    }
}
