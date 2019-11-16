/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

import backend.Sistema;
import backend.Venta;
import static frontend.Utilitarios.ir_carrito;
import static frontend.Utilitarios.ir_propuestas;
import static frontend.Utilitarios.ir_puntosVenta;
import static frontend.Utilitarios.ir_tienda;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Nahuel
 */
public class HistorialController implements Initializable {

    @FXML
    private VBox lista_ordenes;
    @FXML
    private VBox lista_ventas;
    @FXML
    private Label lbl_cantidad_carro;

    private Sistema sistema;

    public void inicializarDatos(Sistema sistema) {
        this.sistema = sistema;
        String cantidadCarro = String.valueOf(sistema.cantCarrito());
        this.lbl_cantidad_carro.setText(cantidadCarro);
        this.cargarHistorial();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void cargarHistorial() {
        List<Venta> listCompras = sistema.getEsAdmin()?
                this.sistema.getVentas():this.sistema.getVentasCliente();
        
        this.lista_ordenes.getChildren().clear();

        for (int i = 0; i < listCompras.size(); i++) {
            try {

                //Cargar el objeto
                FXMLLoader fxml = new FXMLLoader(
                        getClass().getResource("/frontend/HistorialOrden.fxml"));
                Parent nodo = fxml.load();

                //Carga los datos
                HistorialOrdenController controller = fxml.getController();

                controller.inicializarDatos(listCompras.get(i), i, lista_ventas);

                //Cargo el nuevo objeto
                this.lista_ordenes.getChildren().add(nodo);

            } catch (IOException ex) {
                Logger.getLogger(TiendaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void carrito(ActionEvent event) {
        ir_carrito(this, event, sistema);
    }

    @FXML
    private void tienda(ActionEvent event) {
        ir_tienda(this, event, sistema);
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        frontend.Utilitarios.cerrarSesion(this, event, this.sistema);
    }

    @FXML
    private void puntosVenta(ActionEvent event) {
        ir_puntosVenta(this, event, sistema);
    }

    @FXML
    private void propuestas(ActionEvent event) {
        ir_propuestas(this, event, sistema);
    }
}
