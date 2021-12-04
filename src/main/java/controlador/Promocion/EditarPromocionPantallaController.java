package controlador.Promocion;

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
import modelo.Promocion.Promocion;

/**
 * FXML Controller class
 *
 * @author Jair Vasquez Rendón
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
        this.esEdicion = false;
        if(campoTextoValidoNombreProducto() && campoTextoValidoMensaje() && campoTextoValidoDescuento() &&
               campoNuloPrecioAnterior() && campoNumericoValidoPrecioAnterior() && campoNuloPrecioNuevo() && campoNumericoValidoPrecioNuevo()){
            this.promocion.setNombreProducto(this.txtNombreProducto.getText());
            this.promocion.setMensaje(this.txtMensaje.getText());
            this.promocion.setDescuento(this.txtDescuento.getText());
            this.promocion.setPrecioAnterior(Float.parseFloat(this.txtPrecioAnterior.getText()));
            this.promocion.setPrecioNuevo(Float.parseFloat(this.txtPrecioNuevo.getText()));
            this.esEdicion = true;
            this.stageDialogoEdicion.close();
        }
    }

    private boolean campoTextoValidoNombreProducto(){
        
        String errorMessage = "";
        
        if(this.txtNombreProducto.getText() == null || this.txtNombreProducto.getText().length() == 0){
          errorMessage +="Verifica el campo! \n";                         
        }
        if(errorMessage.length()==0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("El campo Nombre Producto esta vacío");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
            return false;            
        }                                       
        
    }
    
    private boolean campoTextoValidoMensaje(){
        
        String errorMessage = "";
        
        if(this.txtMensaje.getText() == null || this.txtMensaje.getText().length() == 0){
          errorMessage +="Verifica el campo! \n";                         
        }
        if(errorMessage.length()==0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("El campo Mensaje esta vacío");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
            return false;            
        }                                       
        
    }
    
        private boolean campoTextoValidoDescuento(){
        
        String errorMessage = "";
        
        if(this.txtDescuento.getText() == null || this.txtDescuento.getText().length() == 0){
          errorMessage +="Verifica el campo! \n";                         
        }
        if(errorMessage.length()==0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("El campo Descuento esta vacío");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
            return false;            
        }                                       
        
    }
    
    private boolean campoNuloPrecioAnterior(){
        String errorMessage = "";
        if(this.txtPrecioAnterior.getText() == null || this.txtPrecioAnterior.getText().length() == 0 ){
            errorMessage += "Verifica el campo! \n";
        }
                
        if(errorMessage.length() == 0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("El campo Precio Anterior esta vacío");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
            return false;
        }
    }
    
    private boolean campoNumericoPrecioAnterior(){
        boolean numero = false;
        String precioAnterior = this.txtPrecioAnterior.getText();
        for(int i = 0;i<precioAnterior.length();i++){
            if(precioAnterior.charAt(i) == '1' || precioAnterior.charAt(i) == '2'|| precioAnterior.charAt(i) == '3' ||
                    precioAnterior.charAt(i) == '4' || precioAnterior.charAt(i) == '5' || precioAnterior.charAt(i) == '6' ||
                    precioAnterior.charAt(i) == '7' || precioAnterior.charAt(i) == '8' || precioAnterior.charAt(i) == '9' ||
                    precioAnterior.charAt(i) == '.'){
                numero = true;
                break;
            }
        }
        return numero;
    }
   
   private boolean campoNumericoValidoPrecioAnterior(){
        String errorMessage = "";
        if(this.campoNumericoPrecioAnterior() == false){
            errorMessage += "Verifica el campo! \n";
        }
                
        if(errorMessage.length() == 0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("Ingresa solo numeros enteros o decimales");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
            return false;
        }
    } 
 
    private boolean campoNuloPrecioNuevo(){
        String errorMessage = "";
        if(this.txtPrecioNuevo.getText() == null || this.txtPrecioNuevo.getText().length() == 0 ){
            errorMessage += "Verifica el campo! \n";
        }
                
        if(errorMessage.length() == 0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("El campo Precio Nuevo esta vacío");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
            return false;
        }
    }
   
   private boolean campoNumericoPrecioNuevo(){
        String errorMessage = "";
        boolean numero = false;
        String precioNuevo = this.txtPrecioNuevo.getText();
        for(int i = 0;i<precioNuevo.length();i++){
            if(precioNuevo.charAt(i) == '1' || precioNuevo.charAt(i) == '2'|| precioNuevo.charAt(i) == '3' ||
                    precioNuevo.charAt(i) == '4' || precioNuevo.charAt(i) == '5' || precioNuevo.charAt(i) == '6' ||
                    precioNuevo.charAt(i) == '7' || precioNuevo.charAt(i) == '8' || precioNuevo.charAt(i) == '9' ||
                    precioNuevo.charAt(i) == '.'){
                numero = true;
                break;
            }
        }
        return numero;
    }
   
   private boolean campoNumericoValidoPrecioNuevo(){
        String errorMessage = "";
        if(this.campoNumericoPrecioNuevo() == false){
            errorMessage += "Verifica el campo! \n";
        }
                
        if(errorMessage.length() == 0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("Ingresa solo numeros enteros o decimales");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
            return false;
        }
    } 
 
    
    @FXML
    void cancelarEdicion(ActionEvent event) {
        stageDialogoEdicion.close();
    }
    
}
