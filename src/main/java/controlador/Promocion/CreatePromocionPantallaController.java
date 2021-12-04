package controlador.Promocion;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Promocion.Promocion;
import modelo.Promocion.Promocion_DAO_Imp;

public class CreatePromocionPantallaController implements Initializable{

    @FXML
    private TextField txtNombreProducto;
    
    @FXML
    private DatePicker fechaInicio;
    
    @FXML
    private TextField txtDiasDuracion;
    
    @FXML
    private Label txtFechaCierre;

    @FXML
    private TextField txtMensaje;
  
    @FXML
    private Label txtFechaInicio;

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
            if (campoTextoValidoNombreProducto() && campoTextoValidoMensaje() && campoTextoValidoDescuento() &&
               campoNuloPrecioAnterior() && campoNumericoValidoPrecioAnterior() && campoNuloPrecioNuevo() && 
               campoNumericoValidoPrecioNuevo() && campoTextoValidoDias() && campoNumericoDias()) {

                String nombreProducto = this.txtNombreProducto.getText();
                String mensaje = this.txtMensaje.getText();
                String descuento = this.txtDescuento.getText();
                float precioAnterior = Float.parseFloat(txtPrecioAnterior.getText());
                float precioNuevo = Float.parseFloat(txtPrecioNuevo.getText());   
                
                LocalDate fecha1 = fechaInicio.getValue();
                String formatoFechaInicio = fecha1.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
                txtFechaInicio.setText(formatoFechaInicio);
                String fechaInicio = this.txtFechaInicio.getText();
                
                int duracion = Integer.parseInt(txtDiasDuracion.getText());
                int diasDuracion = duracion;
                
                LocalDate nuevaFecha = fecha1.plusDays(duracion);
                String formatoFechaCierre = nuevaFecha.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
                txtFechaCierre.setText(formatoFechaCierre);
                String fechaCierre = formatoFechaCierre;
                
                promocion = new Promocion(0,nombreProducto,mensaje,descuento,precioAnterior,precioNuevo,fechaInicio,fechaCierre,diasDuracion);
                
                if(this.promocion_DAO.create(promocion)==true){
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
    
    
    @FXML
    public void getDateInicio(ActionEvent event) {
        LocalDate fecha1 = fechaInicio.getValue();
        String formatoFechaInicio = fecha1.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
        txtFechaInicio.setText(formatoFechaInicio);
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
   
   private boolean campoTextoValidoDias(){
        
        String errorMessage = "";
        
        if(this.txtDiasDuracion.getText() == null || this.txtDiasDuracion.getText().length() == 0){
          errorMessage +="Verifica el campo! \n";                         
        }
        if(errorMessage.length()==0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("El campo días de duración esta vacío");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
            return false;            
        }                                       
        
    }
   
   private boolean campoNumericoDias(){
        String errorMessage = "";
        int duracion = Integer.parseInt(txtDiasDuracion.getText());
        int diasDuracion = duracion;
        
        if(diasDuracion>15){
          errorMessage +="Verifica el campo! \n";                         
        }
        if(errorMessage.length()==0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText(errorMessage);
            alert.setContentText("Las promociones no deben durar más\n"+"de 15 días.");
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

