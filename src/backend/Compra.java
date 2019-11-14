/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

/**
 *
 * @author Santiago
 */
public class Compra {
    private Articulo articulo;
    private Envase envase;
    private int cantidad;

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Envase getEnvase() {
        return envase;
    }

    public void setEnvase(Envase envase) {
        this.envase = envase;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Compra(){
        this.setArticulo(new Articulo());
        this.setEnvase(new Envase());
        this.setCantidad(0);
    }
    
    public Compra(Articulo articulo, Envase envase, int cantidad) {
        this.setArticulo(articulo);
        this.setEnvase(envase);
        this.setCantidad(cantidad);
    }
    
    public int total(){
        return articulo.getPrecio()*cantidad;
    }
    
}
