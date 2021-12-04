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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import modelo.Empleado.Empleado;
import modelo.Empleado.Empleado_DAO_Imp;

/**
 * FXML Controller class
 *
 * @author Pablo
 */
public class CreateEmpleadoPantallaController implements Initializable {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtUser;

    @FXML
    private PasswordField txtPass;

    @FXML
    private ComboBox<?> comboPuesto;

    @FXML
    private JFXButton btnRegistrar;    
    
    private Empleado_DAO_Imp  empleado_DAO;
    private Stage stageDialogoEdicion;
    private Empleado empleado;
    
    public String puesto;
    
    ObservableList<String> puestoItems = FXCollections.observableArrayList();
    
    public void setStageDialog(Stage stageDialogoEdicion){
        this.stageDialogoEdicion = stageDialogoEdicion;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        empleado_DAO = new Empleado_DAO_Imp();
        ObservableList list = FXCollections.observableArrayList("Empleado","Gerente");
        comboPuesto.setItems(list);
        txtNombre.addEventHandler(KeyEvent.KEY_TYPED, event -> soloLetras(event));
    }    
   
    @FXML
    void registrarEmpleado(ActionEvent event) {

        Empleado empleado = new Empleado();
        try{
            if (camposValidos()) {

                String nombre = this.txtNombre.getText();
                String user = this.txtUser.getText();
                String pass = this.txtPass.getText();
                puesto = this.comboPuesto.getValue().toString();                
                empleado = new Empleado(0,nombre,user,pass,puesto);
                                
                if(this.empleado_DAO.create(empleado)==true){
                    Stage stage = (Stage) this.btnRegistrar.getScene().getWindow();
                    Alert alert = new Alert(Alert.AlertType.NONE,"Se ha añadido con exito",ButtonType.OK);
                    alert.setTitle("Operación exitosa");          
                    alert.showAndWait();
                    cerrarVentana();                    
                }else{
                    Stage stage = (Stage) this.btnRegistrar.getScene().getWindow();
                    String errorMessage = "Ha ocurrido un error al añadir, intentalo nuevamente.";
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(" ¡Por favor! intentelo nuevamente");
                    alert.setContentText(errorMessage);
                    alert.initOwner(stageDialogoEdicion);
                    alert.showAndWait();
                }                 
                                                                                
            }
            
        }catch(Exception ex) {
            Stage stage = (Stage) this.btnRegistrar.getScene().getWindow();
            String errorMessage = "Se ha perdido la conexión al servidor, revisa tu conexión a internet e intentalo nuevamente.\n"+ex;            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(" ¡Por favor! intentelo nuevamente");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
        }
                
    }    
        
    private boolean camposValidos(){
        
        String errorMessage = "";
        
        if(this.txtNombre.getText() == null ||
           this.txtUser.getText() == null   ||
           this.txtPass.getText() == null
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
    
    public void cerrarVentana(){
        Stage stage = (Stage) this.btnRegistrar.getScene().getWindow();
        stage.close();
    }       
        
    public void soloLetras(KeyEvent keyEvent){
        try{
            char key = keyEvent.getCharacter().charAt(0);
            if(!Character.isLetter(key) && !Character.isSpaceChar(key) ){
                keyEvent.consume();
            }
        }catch(Exception e){
            System.out.println("soloLetras"+e);
        }
            
    }
    
}
