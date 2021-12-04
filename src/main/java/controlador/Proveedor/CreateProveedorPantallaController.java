package controlador.Proveedor;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import modelo.Proveedor.Proveedor;
import modelo.Proveedor.Proveedor_DAO_Imp;

/**
 *
 * @author Horus Alejandro Hernandez Cabrera
 */

public class CreateProveedorPantallaController implements Initializable{

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
        txtTelefono.addEventHandler(KeyEvent.KEY_TYPED, event -> soloNumeros(event));
    } 
    
    @FXML
    void registrarProveedor(ActionEvent event) {
           Proveedor proveedor = new Proveedor();
           try{
            if (campoTextoValidoNombre() && campoNuloTelefono() && campoNumericoValidoTelefono() && 
                    campoTextoValidoDireccion() && campoTextoValidoProducto()) {

                String nombre = this.txtNombre.getText();
                String telefono = this.txtTelefono.getText();
                String direccion = this.txtDireccion.getText();
                String producto = this.txtProducto.getText();                
                proveedor = new Proveedor(0,nombre,telefono,direccion,producto);                                                
                
                if(this.proveedor_DAO.create(proveedor)==true){
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
            String errorMessage = "Trata de igresar un Proveedor existente en la Base de Datos\n";
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, Problemas con la Base de Datos");
            alert.setHeaderText(" ¡Por favor! intentelo nuevamente");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
        }
           
    }
    
    public void setStageDialog(Stage stageDialogoEdicion){
        this.stageDialogoEdicion = stageDialogoEdicion;
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
    
        private boolean campoNuloTelefono(){
        String errorMessage = "";
        if(this.txtTelefono.getText() == null || this.txtTelefono.getText().length() == 0 || this.txtTelefono.getText().length() > 10 ){
            errorMessage += "Verifica el campo! \n";
        }
                
        if(errorMessage.length() == 0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("El campo Teléfono esta vacío o tiene mas de 10 digitos");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
            return false;
        }
    }
   
   private boolean campoNumericoTelefono(){
        String errorMessage = "";
        boolean numero = false;
        String telefono = this.txtTelefono.getText();
        for(int i = 0;i<telefono.length();i++){
            if(telefono.charAt(i) == '1' || telefono.charAt(i) == '2'|| telefono.charAt(i) == '3' ||
                    telefono.charAt(i) == '4' || telefono.charAt(i) == '5' || telefono.charAt(i) == '6' ||
                    telefono.charAt(i) == '7' || telefono.charAt(i) == '8' || telefono.charAt(i) == '9' ||
                    telefono.charAt(i) == '.'){
                numero = true;
                break;
            }
        }
        return numero;
    }
   
   private boolean campoNumericoValidoTelefono(){
        String errorMessage = "";
        if(this.campoNumericoTelefono() == false){
            errorMessage += "Verifica el campo! \n";
        }
                
        if(errorMessage.length() == 0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("Ingresa solo numeros");
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
            alert.setHeaderText("El campo Dirección esta vacío");
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
            alert.setHeaderText("El campo Producto esta vacío");
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
    
    public void soloNumeros(KeyEvent keyEvent){
        try{
            char key = keyEvent.getCharacter().charAt(0);
            if(!Character.isDigit(key)){
                keyEvent.consume();
            }
        }catch(Exception e){
          
        }
    }
    
    
}

