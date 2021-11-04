 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.Proveedor;

/**
 *
 * @author horus
 */

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Proveedor.Proveedor;

/**
 *
 * @author horus
 */

public class EditarProveedorPantallaController implements Initializable {
    
    @FXML
    private TextField txtNombreProveedor;

    @FXML
    private TextField txtTelefono;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtProducto;

    @FXML
    private JFXButton btnCancelar;
    
    @FXML
    private Label etiquetaId;
    
    private Proveedor proveedor;
    
    private boolean esEdicion;
    
    private Stage stageDialogoEdicion;
    
    public Button getBotonEditar(){
        return this.btnEditar;
    }
    
    public GeneralProveedorPantallaController gp;
    
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
    
    public void setProveedor(Proveedor proveedor){
        if(proveedor != null){
            this.proveedor = proveedor;
            
            if(proveedor.getProveedorId() == 0){
                this.etiquetaId.setText("Será generada");
            }else{
                //this.etiquetaId.setText(Integer.toString(promocion.getIdPromocion()));
                this.etiquetaId.setText(Integer.toString(proveedor.getProveedorId()));
            }
            this.txtNombreProveedor.setText(proveedor.getProveedorNombre());
            this.txtTelefono.setText(proveedor.getProveedorTelefono());
            this.txtDireccion.setText(proveedor.getProveedorDireccion());
            this.txtProducto.setText(proveedor.getProveedorProducto());
        }
    }

    @FXML
    void editarProveedorx(ActionEvent event) {
        this.esEdicion = false;
        if(campoTextoValidoNombreProveedor() && campoTextoValidoTelefono() && campoTextoValidoDireccion() && campoTextoValidoProducto()){
            this.proveedor.setProveedorNombre(this.txtNombreProveedor.getText());
            this.proveedor.setProveedorTelefono(this.txtTelefono.getText());
            this.proveedor.setProveedorDireccion(this.txtDireccion.getText());
            this.proveedor.setProveedorProducto(this.txtProducto.getText());
            this.esEdicion = true;
            this.stageDialogoEdicion.close();
        }
    }
    
    private boolean campoTextoValidoNombreProveedor(){
        
        String errorMessage = "";
        
        if(this.txtNombreProveedor.getText() == null || this.txtNombreProveedor.getText().length() == 0){
          errorMessage +="Verifica el campo! \n";                         
        }
        if(errorMessage.length()==0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("El campo Nombre Proveedor esta vacío");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
            return false;            
        }                                       
    }
    
    private boolean campoTextoValidoTelefono(){
        
        String errorMessage = "";
        
        if(this.txtTelefono.getText() == null || this.txtTelefono.getText().length() == 0){
          errorMessage +="Verifica el campo! \n";                         
        }
        if(errorMessage.length()==0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("El campo Telefono esta vacío");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
            return false;            
        }                                       
    }
    
    private boolean campoTextoValidoDireccion(){
        
        String errorMessage = "";
        
        if(this.txtDireccion.getText() == null || this.txtDireccion.getText().length() == 0){
          errorMessage +="Verifica el campo! \n";                         
        }
        if(errorMessage.length()==0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("El campo Direccion esta vacío");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
            return false;            
        }                                       
    }
    
    private boolean campoTextoValidoProducto(){
        
        String errorMessage = "";
        
        if(this.txtProducto.getText() == null || this.txtProducto.getText().length() == 0){
          errorMessage +="Verifica el campo! \n";                         
        }
        if(errorMessage.length()==0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("El campo Telefono esta vacío");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
            return false;            
        }                                       
    }
    
    @FXML
    void cancelarEdicionx(ActionEvent event) {
        stageDialogoEdicion.close();
    }
}
