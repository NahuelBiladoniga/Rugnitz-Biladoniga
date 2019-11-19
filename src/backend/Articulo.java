/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import javafx.scene.image.Image;

/**
 *
 * @author Santiago
 */
public class Articulo {

    //TODO: agregar mas supongo
    public enum Tipo {
        BEBIDA, SECO, HUMEDO
    };

    public enum Categoria {
        LIBRE_DE_AZUCAR, LIBRE_DE_GLUTEN, VEGANO, ORGANICO, BAJAS_CALORIAS;

    };

    private String nombre;
    private String origen;
    private int precio;
    private String descripcion;
    private Categoria[] categorias;
    private int id;
    private boolean disponible;
    private Tipo tipo;
    private int vecesComprado;
    private Image imagen;
    private double valoracion;
    private int vecesValorado;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria[] getCategorias() {
        return categorias;
    }

    public void setCategorias(Categoria[] categorias) {
        this.categorias = categorias;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getVecesComprado() {
        return vecesComprado;
    }

    public void setVecesComprado(int vecesComprado) {
        this.vecesComprado = vecesComprado;
    }

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public double getValoracion() {
        return valoracion;
    }

    public void setValoracion(double valoracion) {
        this.valoracion = valoracion;
    }

    private int getVecesValorado() {
        return vecesValorado;
    }

    private void setVecesValorado(int vecesValorado) {
        this.vecesValorado = vecesValorado;
    }

    public Articulo() {
        this.setNombre("");
        this.setOrigen("");
        this.setDescripcion("");
        this.setPrecio(0);
        this.setId(-1);
        this.setDisponible(false);
        this.setTipo(Tipo.SECO);
        this.setVecesComprado(0);
        this.setImagen(null);
        this.setValoracion(0);
        this.setCategorias(new Categoria[0]);
        this.setVecesValorado(0);

    }

    public Articulo(String nombre, String origen,String descripcion, int precio, int id, Tipo tipo, Image imagen, Categoria[] categorias) {
        this.setNombre(nombre);
        this.setOrigen(origen);
        this.setDescripcion(descripcion);
        this.setPrecio(precio);
        this.setId(id);
        this.setDisponible(true);
        this.setTipo(tipo);
        this.setVecesComprado(0);
        this.setImagen(imagen);
        this.setValoracion(0);
        this.setCategorias(categorias);
        this.setVecesValorado(0);

    }

    public void aumentarUso(int n) {
        this.setVecesComprado(this.getVecesComprado() + n);
    }
    /**
    * Actualiza la valoracion y la cantidad de valoraciones según la valoracion recibida. 
    * valoracion es un promedio de todas las valoraciones realizadas
    * 1- Se divide entre la cantidad de valoraciones para obtener la sumatoria de valoraciones
    * 2- Se agrega la valoracion nueva y se divide entre la nueva cant de valoraciones
    * @param valoracion valoracion nueva a agregar 
    */
    public void agregarValoracion(double valoracion) {
        this.setValoracion(this.getValoracion() * this.getVecesValorado() + valoracion);
        this.setVecesValorado(this.getVecesValorado() + 1);
        this.setValoracion(this.getValoracion() / this.getVecesValorado());
    }

}
