package controlador.Empleado;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
        //puesto = this.comboPuesto.getValue().toString();
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
            //this.comboPuesto.getValue(empleado.getPuesto());
        }
    }    
    
    @FXML
    void editarEmpleado(ActionEvent event) {

            this.empleado.setNombre(this.txtNombre.getText());
            this.empleado.setUsuario(this.txtUsuario.getText());
            this.empleado.setPass(this.txtPass.getText());
            //this.empleado.setPuesto(this.txtPuesto.getText());
            this.empleado.setPuesto(this.comboPuesto.getValue().toString());
            
            this.esEdicion = true;
            this.stageDialogoEdicion.close();
        //}   
    }
    
    @FXML
    void cancelarEdicion(ActionEvent event) {
        stageDialogoEdicion.close();
    }    
    
    
}
