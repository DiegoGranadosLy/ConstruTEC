package com.tec.diegogranados.construtec;

/**
 * Created by Diego Granados on 18/10/2016.
 */

public class Lista_Entrada_Pedido {
    private int idImagen;
    private String Titulo;
    private String Descripcion;

    public Lista_Entrada_Pedido (int idImagen, String pTitulo, String pDescripcion) {
        this.idImagen = idImagen;
        this.Titulo = pTitulo;
        this.Descripcion = pDescripcion;
    }

    public String get_Titulo() {
        return this.Titulo;
    }

    public String get_Descripcion() {
        return this.Descripcion;
    }

    public int get_idImagen() {
        return idImagen;
    }
}