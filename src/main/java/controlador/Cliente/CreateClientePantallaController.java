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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Cliente.Cliente;
import modelo.Cliente.Cliente_DAO_Imp;
import modelo.AlertaFXML;


/**
 * FXML Controller class
 *
 * @author Ivan
 */
public class CreateClientePantallaController implements Initializable {
    
    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtEmail;

    @FXML
    private JFXButton btnRegistrar;
    
    private Cliente_DAO_Imp cliente_DAO;
    private Stage stageDialogoEdicion;
    private Cliente cliente;
    
    GeneralClientePantallaController gc;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cliente_DAO = new Cliente_DAO_Imp();
    }    
    
    @FXML
    void registrarCliente(ActionEvent event) {
        Cliente cliente = new Cliente();
        try{
            if(campoTextoValidoNombre() && campoNuloEmail() && campoTextoValidoEmail() ) {
                String nombreCliente = this.txtNombre.getText();
                String emailCliente = this.txtEmail.getText();
                cliente = new Cliente(nombreCliente, emailCliente);

                if(this.cliente_DAO.create(cliente)==true){
                    Stage stage = (Stage) this.btnRegistrar.getScene().getWindow();
                    Alert alert = new Alert(Alert.AlertType.NONE,"Se ha añadido con exito",ButtonType.OK);
                    alert.setTitle("Operación exitosa");          
                    alert.showAndWait();
                    cerrarVentana();
                }else{
                    Stage stage = (Stage) this.btnRegistrar.getScene().getWindow();
                    String errorMessage = "El tiempo de espera se ha agotado o se perdío la conexión\n" +"con la Base Datos.";
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error, No hay conexión con la Base de Datos");
                    alert.setHeaderText(" ¡Por favor! intentelo nuevamente");
                    alert.setContentText(errorMessage);
                    alert.initOwner(stageDialogoEdicion);
                    alert.showAndWait();
                }                
            }           
        }catch(Exception ex) {
            Stage stage = (Stage) this.btnRegistrar.getScene().getWindow();
            String errorMessage = "El tiempo de espera se ha agotado o se perdío la conexión\n" +"con la Base Datos.";
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, No hay conexión con la Base de Datos");
            alert.setHeaderText(" ¡Por favor! intentelo nuevamente");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
        }
    }
  
    private boolean campoTextoValidoNombre(){
        
        String errorMessage = "";
        
        if(this.txtNombre.getText() == null || this.txtNombre.getText().length() == 0){
          errorMessage +="Verifica el campo! \n";                         
        }
        if(errorMessage.length()==0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("El campo Nombre esta vacío");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
            return false;            
        }                                       
        
    }
    
    private boolean campoTextoEmail(){
        String errorMessage = "";
        boolean simbolo = false;
        String email = this.txtEmail.getText();
        for(int i = 0;i<email.length();i++){
            if(email.charAt(i) == '@'){
                simbolo = true;
                break;
            }
        }
        return simbolo;
    }
    
    private boolean campoNuloEmail(){
        String errorMessage = "";
        if(this.txtEmail.getText() == null || this.txtEmail.getText().length() == 0 ){
            errorMessage += "Verifica el campo! \n";
        }
                
        if(errorMessage.length() == 0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("El campo Email esta vacío");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
            return false;
        }
    }
    
    private boolean campoTextoValidoEmail(){
        String errorMessage = "";
        if(this.campoTextoEmail() == false){
            errorMessage += "Verifica el campo! \n";
        }
                
        if(errorMessage.length() == 0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("Ingresa una extención de correo válida");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
            return false;
        }
    } 
    
    public void cerrarVentana(){
        Stage stage = (Stage) this.btnRegistrar.getScene().getWindow();
        stage.close();
    }         
}
