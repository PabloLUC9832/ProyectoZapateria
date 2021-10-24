package modelo;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 *
 * @author Pablo
 */
public class AlertaFXML extends Alerta {

    private Alert alert;

    public AlertaFXML(Stage ventanaPropietaria) {
        super(ventanaPropietaria);
    }

    private void alertaDatos(String titulo, String encabezado, String contenido) {
        this.alert.setTitle(titulo);
        this.alert.setHeaderText(encabezado);
        this.alert.setContentText(contenido);
        this.alert.initOwner(this.ventanaPropietaria);
    }

    @Override
    public void alertaInformacion(String titulo, String encabezado, String contenido) {
        this.alert = new Alert(Alert.AlertType.INFORMATION);
        this.alertaDatos(titulo, encabezado, contenido);
        this.alert.showAndWait();
    }

    @Override
    public void alertaError(String titulo, String encabezado, String contenido) {
        this.alert = new Alert(Alert.AlertType.ERROR);
        this.alertaDatos(titulo, encabezado, contenido);
        this.alert.showAndWait();
    }

    @Override
    public boolean alertaConfirmacion(String titulo, String encabezado, String contenido) {
        boolean respuesta = true;
        this.alert = new Alert(Alert.AlertType.CONFIRMATION);
        this.alertaDatos(titulo, encabezado, contenido);
        ButtonType resultado = this.alert.showAndWait().orElse(ButtonType.NO);
        if(ButtonType.NO.equals(resultado)){
            respuesta = false;
        }
        return respuesta;
    }

}
