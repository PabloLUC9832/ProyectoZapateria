package controlador.Empleado;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Empleado.Empleado;
/**
 * FXML Controller class
 *
 * @author Pablo
 */
public class EditarEmpleadoPantallaController implements Initializable {

   @FXML
    private Label etiquetaId;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtUsuario;

    @FXML
    private TextField txtPass;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private TextField txtPuesto;

    @FXML
    private JFXButton btnCancelar;    
    
    @FXML
    private ComboBox<?> comboPuesto;    
    
    private Empleado empleado;
    
    private boolean esEdicion;
    
    private Stage stageDialogoEdicion;
    
    public GeneralEmpleadoPantallaController gp;

    public String puesto;
                
    public Button getBotonEditar(){
        return this.btnEditar;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        esEdicion = false;
        ObservableList list = FXCollections.observableArrayList("Empleado","Gerente");
        comboPuesto.setItems(list);        
    }    

    public void setStageDialog(Stage stageDialogoEdicion){
        this.stageDialogoEdicion = stageDialogoEdicion;
    }

    public boolean getEsEdicion(){
        return esEdicion;
    }

    public void setEmpleado(Empleado empleado){
        if(empleado != null){
            this.empleado = empleado;
            if(empleado.getNempleado()== 0){
                this.etiquetaId.setText("Será generada");
            }else{
                this.etiquetaId.setText(Integer.toString(empleado.getNempleado()));
            }
            this.txtNombre.setText(empleado.getNombre());
            this.txtUsuario.setText(empleado.getUsuario());
            this.txtPass.setText(empleado.getPass());
            this.txtPuesto.setText(empleado.getPuesto());
        }
    }    
    
    @FXML
    void editarEmpleado(ActionEvent event) {
        
       this.esEdicion = false;
       if(campoTextoValidoNombre() && campoTextoValidoUsuario() && campoTextoValidoPass()){
            this.empleado.setUsuario(this.txtUsuario.getText());
            this.empleado.setPass(this.txtPass.getText());
            this.empleado.setPuesto(this.comboPuesto.getValue().toString());
            this.esEdicion = true;
            this.stageDialogoEdicion.close();
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
    
    private boolean campoTextoValidoUsuario(){
        
        String errorMessage = "";
        
        if(this.txtUsuario.getText() == null || this.txtUsuario.getText().length() == 0){
          errorMessage +="Verifica el campo! \n";                         
        }
        if(errorMessage.length()==0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("El campo Usuario esta vacío");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
            return false;            
        }                                       
        
    }
    
    private boolean campoTextoValidoPass(){
        
        String errorMessage = "";
        
        if(this.txtPass.getText() == null || this.txtPass.getText().length() == 0){
          errorMessage +="Verifica el campo! \n";                         
        }
        if(errorMessage.length()==0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("El campo Contraseña esta vacío");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
            return false;            
        }                                       
        
    }
    
    /*AQUÍ TRUENA*/
    private boolean campoTextoValidoPuesto(){
        String errorMessage = "";
        
        if(this.comboPuesto.getValue().toString() == "Selecciona el puesto"){
          errorMessage +="Verifica el campo! \n";
          Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("No has seleccionado el puesto");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
        }
        if(errorMessage.length()==20){
            return false;
        }else{
            return true;            
        }                                       
    }
    
    @FXML
    void cancelarEdicion(ActionEvent event) {
        stageDialogoEdicion.close();
    }    
    
    
}
