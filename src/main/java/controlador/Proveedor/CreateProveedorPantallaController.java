/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.Proveedor;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import com.jfoenix.controls.JFXButton;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Proveedor.Proveedor;
import modelo.Proveedor.Proveedor_DAO_Imp;

/**
 *
 * @author horus
 */

public class CreateProveedorPantallaController implements Initializable{
    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtProducto;

    @FXML
    private JFXButton btnRegistrar;
    private Proveedor_DAO_Imp proveedor_DAO;
    private Proveedor proveedor;
    private Stage stageDialogoEdicion;
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        proveedor_DAO =new Proveedor_DAO_Imp();
    } 
    
    @FXML
    void registrarProveedor(ActionEvent event) {
           Proveedor proveedor = new Proveedor();
           try{
            if (camposValidos()) {

                String nombre = this.txtNombre.getText();
                String telefono = this.txtTelefono.getText();
                String direccion = this.txtDireccion.getText();
                String producto = this.txtProducto.getText();                
                proveedor = new Proveedor(nombre,telefono,direccion,producto);
                this.proveedor_DAO.create(proveedor);
            }
            
        }catch(Exception ex){
            Logger.getLogger(CreateProveedorPantallaController.class.getName()).log(Level.SEVERE,null,ex);
        }
           
    }
    
    public void setStageDialog(Stage stageDialogoEdicion){
        this.stageDialogoEdicion = stageDialogoEdicion;
    }
    
    private boolean camposValidos(){
        
        String errorMessage = "";
        
        if(this.txtNombre.getText() == null ||
           this.txtTelefono.getText() == null   ||
           this.txtDireccion.getText() == null   ||
           this.txtProducto.getText() == null   
        ){
          errorMessage +="ALGUNOS CAMPOS ESTAN VACIOS\n";                         
        }
        
        if(errorMessage.length()==0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campo Invalido");
            alert.setHeaderText("Realizar lo siguiente");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
            return false;            
        }                                       
        
    }
}

