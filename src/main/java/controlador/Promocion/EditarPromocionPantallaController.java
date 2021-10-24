package controlador.Promocion;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Promocion.Promocion;

/**
 * FXML Controller class
 *
 * @author Jair
 */
public class EditarPromocionPantallaController implements Initializable {

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private TextField txtMensaje;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private TextField txtDescuento;

    @FXML
    private TextField txtPrecioAnterior;

    @FXML
    private TextField txtPrecioNuevo;

    @FXML
    private Label etiquetaId;

    @FXML
    private JFXButton btnCancelar;
    
    private Promocion promocion;
    
    private boolean esEdicion;
    
    private Stage stageDialogoEdicion;
    
    public Button getBotonEditar(){
        return this.btnEditar;
    }
    
    public GeneralPromocionPantallaController gp;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        esEdicion = false;
    }
    
    public void setStageDialog(Stage stageDialogoEdicion){
        this.stageDialogoEdicion = stageDialogoEdicion;
    }

    public boolean getEsEdicion(){
        return esEdicion;
    }
    
    public void setPromocion(Promocion promocion){
        if(promocion != null){
            this.promocion = promocion;
            if(promocion.getIdPromocion() == 0){
                this.etiquetaId.setText("Será generada");
            }else{
                //this.etiquetaId.setText(Integer.toString(promocion.getIdPromocion()));
                this.etiquetaId.setText(Integer.toString(promocion.getIdPromocion()));
            }
            this.txtNombreProducto.setText(promocion.getNombreProducto());
            this.txtMensaje.setText(promocion.getMensaje());
            this.txtDescuento.setText(promocion.getDescuento());
            this.txtPrecioAnterior.setText(Float.toString(promocion.getPrecioAnterior()));
            this.txtPrecioNuevo.setText(Float.toString(promocion.getPrecioNuevo()));
        }
    }
    
    @FXML
    void editarPromocion(ActionEvent event) {
        //if(esNombreValido()){
            this.promocion.setNombreProducto(this.txtNombreProducto.getText());
            this.promocion.setMensaje(this.txtMensaje.getText());
            this.promocion.setDescuento(this.txtDescuento.getText());
            this.promocion.setPrecioAnterior(Float.parseFloat(this.txtPrecioAnterior.getText()));
            this.promocion.setPrecioNuevo(Float.parseFloat(this.txtPrecioNuevo.getText()));
            this.esEdicion = true;
            this.stageDialogoEdicion.close();
        //}   
    }
    
    @FXML
    void cancelarEdicion(ActionEvent event) {
        stageDialogoEdicion.close();
    }
    
}
