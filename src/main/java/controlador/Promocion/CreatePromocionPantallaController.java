package controlador.Promocion;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Promocion.Promocion;
import modelo.Promocion.Promocion_DAO_Imp;
import modelo.AlertaFXML;

public class CreatePromocionPantallaController implements Initializable{

    @FXML
    private TextField txtNombreProducto;

    @FXML
    private TextField txtMensaje;

    @FXML
    private JFXButton btnRegistrar;

    @FXML
    private TextField txtDescuento;

    @FXML
    private TextField txtPrecioAnterior;

    @FXML
    private TextField txtPrecioNuevo;
    
    private Promocion_DAO_Imp  promocion_DAO;
    private Stage stageDialogoEdicion;
    private Promocion promocion;
    
    
    public void setStageDialog(Stage stageDialogoEdicion){
        this.stageDialogoEdicion = stageDialogoEdicion;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        promocion_DAO = new Promocion_DAO_Imp();
    }
    
    @FXML
    void registrarPromocion(ActionEvent event) {
        
        Promocion promocion = new Promocion();
        try{
            if (camposValidosTexto() && camposValidosNumeros()) {

                String nombreProducto = this.txtNombreProducto.getText();
                String mensaje = this.txtMensaje.getText();
                String descuento = this.txtDescuento.getText();
                float precioAnterior = Float.parseFloat(txtPrecioAnterior.getText());
                float precioNuevo = Float.parseFloat(txtPrecioNuevo.getText());                
                promocion = new Promocion(0,nombreProducto,mensaje,descuento,precioAnterior,precioNuevo);                                
                
                if(this.promocion_DAO.create(promocion)==true){
                    Stage stage = (Stage) this.btnRegistrar.getScene().getWindow();
                    AlertaFXML alerta = new AlertaFXML(stage);
                    alerta.alertaConfirmacion("Agregado con exito", "Promoción agregado con exito", "La promoción ha sido agregado exitosamente");                    
                    cerrarVentana();
                }else{
                    Stage stage = (Stage) this.btnRegistrar.getScene().getWindow();
                    AlertaFXML alerta = new AlertaFXML(stage);
                    alerta.alertaError("Error al agregar", "Error al agregar", "Ha ocurrido al agregar la promoción, intentelo nuevamente.");                                                        
                }                                                   
            }
            
        }catch(Exception ex) {
            Stage stage = (Stage) this.btnRegistrar.getScene().getWindow();
            AlertaFXML alerta = new AlertaFXML(stage);
            alerta.alertaError("Error al agregar", "Error al agregar", "Ha ocurrido al agregar la promoción, intentelo nuevamente.Más información \n"+ex);             
            Logger.getLogger(CreatePromocionPantallaController.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
    
    private boolean camposValidosTexto(){
        
        String errorMessage = "";
        
        if(this.txtNombreProducto.getText() == null || this.txtNombreProducto.getText().length() == 0 ||
           this.txtMensaje.getText() == null   || this.txtMensaje.getText().length() == 0 ||
           this.txtDescuento.getText() == null   || this.txtDescuento.getText().length() == 0
        ){
          errorMessage +="Verifica los cambios\n";                         
        }
        
        if(errorMessage.length()==0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campo Invalido");
            alert.setHeaderText("Revisa los campos Nombre Producto, Mensaje, Descuento");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
            return false;            
        }                                       
        
    }

      private boolean camposValidosNumeros(){
        String errorMessage = "";
        if(this.txtPrecioAnterior.getText() == null || this.txtPrecioNuevo.getText() == null || 
                this.txtPrecioAnterior.getText().length() == 0 || this.txtPrecioNuevo.getText().length() == 0 || 
                this.campoNumericoPrecioAnterior() == false || this.campoNumericoPrecioNuevo() == false){
            errorMessage += "Verifica los campos! \n";
        }
                
        if(errorMessage.length() == 0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campo Invalido");
            alert.setHeaderText("Atencion, verifica los campos Precio Anterior y Precio Nuevo");
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
    
        private boolean campoNumericoPrecioNuevo(){
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

    public void cerrarVentana(){
        Stage stage = (Stage) this.btnRegistrar.getScene().getWindow();
        stage.close();
    }       
    
    
}

