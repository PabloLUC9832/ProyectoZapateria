/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.Cliente;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Cliente.Cliente;
import modelo.Cliente.Cliente_DAO_Imp;


/**
 * FXML Controller class
 *
 * @author theiv
 */
public class CreateClientePantallaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtEmail;

    @FXML
    private JFXButton btnRegistrar;
    
    private Cliente_DAO_Imp cliente_DAO;
    private Stage stageDialogoEdicion;
    private Cliente cliente;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cliente_DAO = new Cliente_DAO_Imp();
    }    
    
    @FXML
    void registrarCliente(ActionEvent event) {
        Cliente cliente = new Cliente();
        try{
            if(CamposValidos()) {
                String nombreCliente = this.txtNombre.getText();
                String emailCliente = this.txtEmail.getText();
                cliente = new Cliente(nombreCliente, emailCliente);
                this.cliente_DAO.create(cliente);
            }
        }catch(Exception ex) {
            Logger.getLogger(CreateClientePantallaController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }

    private boolean CamposValidos() {
        
        String errorMessage = "";
        
        if(this.txtNombre.getText() == null || 
           this.txtEmail.getText() == null
        ){
        errorMessage +="CAMPOS VACÍOS";
    }
                
    if(errorMessage.length()== 0) {
        return true;
    }else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Campo Invalido");
        alert.setHeaderText("Realizar lo siguiente");
        alert.setContentText(errorMessage);
        alert.initOwner(stageDialogoEdicion);
        alert.showAndWait();
        return false;
    }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
