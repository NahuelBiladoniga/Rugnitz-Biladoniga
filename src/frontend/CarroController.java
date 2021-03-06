package frontend;

import backend.Compra;
import backend.Sistema;
import static frontend.Utilitarios.confirmarPopup;
import static frontend.Utilitarios.crearError;
import static frontend.Utilitarios.ir_historial;
import static frontend.Utilitarios.ir_propuestas;
import static frontend.Utilitarios.ir_puntosVenta;
import static frontend.Utilitarios.ir_tienda;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * @author Nahuel Biladoniga-211138
 * @author Santiago Rugnitz-215381
 */
public class CarroController implements Initializable {

    @FXML
    private Label lbl_cantidad_carro;
    @FXML
    private Label lbl_total;
    @FXML
    private Label lbl_subtotal;
    @FXML
    private VBox lista_compras;
    @FXML
    private Button productos;
    @FXML
    private DatePicker fechaEntrega;

    private Sistema sistema;

    public void inicializarDatos(Sistema sistema) {
        this.sistema = sistema;
        this.lbl_cantidad_carro.setText(String.valueOf(sistema.getCarrito()));
        this.cargarArticulos();
        Integer precioTotal = this.sistema.getCarrito().getTotal();
        this.lbl_subtotal.setText("$" + String.valueOf(precioTotal));
        this.lbl_total.setText("$" + String.valueOf(precioTotal));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        restringirFecha(fechaEntrega, LocalDate.now(),
                LocalDate.now().plusDays(14));
    }

    public void restringirFecha(DatePicker datePicker, LocalDate minDate, LocalDate maxDate) {
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(minDate)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        } else if (item.isAfter(maxDate)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        datePicker.setDayCellFactory(dayCellFactory);
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        frontend.Utilitarios.cerrarSesion(this, event, this.sistema);
    }

    @FXML
    private void comprar(ActionEvent event) {
        if (confirmarPopup(this)) {

            if (sistema.cantCarrito() > 0) {
                LocalDate fecha = this.fechaEntrega.getValue();
                this.sistema.getCarrito().setFecha(fecha == null
                        ? LocalDate.now() : fecha);
                String html = this.sistema.registrarVenta();
                mostrarFactura(html);
                productos.fire();

            } else {
                Utilitarios.crearError(this, "Carrito Vacío");
            }
        }
    }

    public void cargarArticulos() {
        String cant = String.valueOf(sistema.cantCarrito());
        this.lbl_cantidad_carro.setText(cant);

        List<Compra> listCompras = this.sistema.getCarrito().getCompras();
        this.lista_compras.getChildren().clear();
        String precioTotal = "$" + String.valueOf(sistema.getCarrito().getTotal());
        lbl_total.setText(precioTotal);
        lbl_subtotal.setText(precioTotal);

        if (sistema.cantCarrito() == 0) {
            crearError(this, "Carrito Vacio");
            productos.fire();
        }

        for (int i = 0; i < listCompras.size(); i++) {
            try {

                //Cargar el objeto
                FXMLLoader fxml = new FXMLLoader(
                        getClass().getResource("/frontend/CarroItem.fxml"));
                Parent nodo = fxml.load();

                //Carga los datos
                CarroItemController controller = fxml.getController();

                controller.inicializarDatos(sistema,
                        this.lbl_total,
                        this.lbl_subtotal,
                        this,
                        i);

                //Cargo el nuevo objeto
                this.lista_compras.getChildren().add(nodo);

            } catch (IOException ex) {
                Logger.getLogger(TiendaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void mostrarFactura(String html) {
        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().
                getResource("/frontend/Factura.fxml"));

        try {

            Parent root = loader.load();
            Scene scene = new Scene(root);

            stage.initStyle(StageStyle.UNDECORATED);
            FacturaController controller = loader.getController();
            controller.inicializarDatos(html);
            loader.setController(controller);
            stage.centerOnScreen();
            stage.setTitle("Factura");
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException ex) {

            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void tienda(ActionEvent event) {
        ir_tienda(this, event, sistema);
    }

    @FXML
    private void historial(ActionEvent event) {
        ir_historial(this, event, sistema);
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
