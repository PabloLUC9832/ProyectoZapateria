package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import modelo.Empleado.Empleado;
import modelo.Empleado.Empleado_DAO_Imp;

/**
 * FXML Controller class
 *
 * @author Pablo
 */
public class LoginPantallaController implements Initializable {

    @FXML
    private ImageView btnExit;

    @FXML
    private TextField campoUsuario;

    @FXML
    private PasswordField campoPass;

    @FXML
    private Button btnLogin;    
        
    @FXML
    private ComboBox<?> comboPuesto;    
    
    public String puesto="";
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btnExit.setOnMouseClicked(event ->{
            System.exit(0);
        });        
        
        ObservableList list = FXCollections.observableArrayList("Empleado","Gerente");
        comboPuesto.setItems(list);        
    }    
    
    @FXML
    void iniciarSesion(ActionEvent event) {
        String usuario = this.campoUsuario.getText();
        String pass = this.campoPass.getText();
        puesto = this.comboPuesto.getValue().toString();   
        
        Empleado_DAO_Imp empleadoDAO_Imp = new Empleado_DAO_Imp();
        
        if(esUsuarioValido()){
            Empleado empleado = null;
            
            try{
                empleado = empleadoDAO_Imp.read(pass);
            }catch(Exception ex){
                System.out.println("EX ERROR AL INICIAR SESIÓN:  "+ex);
            }
            if (empleado.getUsuario().equals(usuario) && empleado.getPuesto().equals(puesto)){
                System.out.println("INGRESANDO");     
                
                this.cerrarVentanaInicio();
                this.mostrarVentanaPrincipal();
            }else{
                
                System.out.println("ERROR AL ACCEDER ----");
            }
            
        }else{
            System.out.println("CONTRASEÑA INVALIDA");
        }
    }
                
    private boolean esUsuarioValido(){
        String errorMessage = "";
        
        if(this.campoUsuario.getText()==null ||this.campoUsuario.getText().length()==0){
            errorMessage="USUARIO INVALIDO";
        }
        if (this.campoUsuario.getText().contains(" ")){
            errorMessage = "EL USUARIO CONTIENE ESPACIOS\n";
        }
        
        if(errorMessage.length()==0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("CAMPO INVALIDO");
            alert.setHeaderText("REALIZAR LO SIGUIENTE");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    public void cerrarVentanaInicio(){
        Stage stage = (Stage) this.btnLogin.getScene().getWindow();
        stage.close();
    }    
    
    public void mostrarVentanaPrincipal(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/MainPantalla.fxml"));
            Parent ventanaPrincipal = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(ventanaPrincipal));
            stage.show();
            
        }catch(IOException e){
            System.out.println("Error al abrir ventana principal"+e);
        }
    }
    
}
