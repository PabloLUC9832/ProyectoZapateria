package controlador.Producto;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.AlertaFXML;
import modelo.Producto.Producto;
import modelo.Producto.Producto_DAO_Imp;

/**
 *
 * @author jhair
 */
public class EditarProductoPantallaController implements Initializable{
     @FXML
    private JFXButton btnCancelar;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private Label etiquetaClave;

    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtPrecioC;

    @FXML
    private TextField txtPrecioV;

    @FXML
    private TextField txtProveedor;
    
    private Producto_DAO_Imp producto_DAO;
    private Producto producto;
    private boolean esEdicion;
    private Stage stageDialogoEdicion;
    private EditarProductoPantallaController controlador;
    private Stage stageInicio;
    private Stage stagePrincipal;
    public float PrecioV;
    public float PrecioC;
    
    public Button getBotonEditar() {
        return this.btnEditar;
    }
    
    //public GeneralProductoPantallaController gp;

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
    
    public void setProducto(Producto producto){
        if(producto != null){
            this.producto = producto;
            
            if(producto.getClaveProducto() == 0){
                this.etiquetaClave.setText("Será generada");
            }else{
                this.etiquetaClave.setText(Integer.toString(producto.getClaveProducto()));
            }
            this.txtMarca.setText(producto.getMarcaProducto());
            this.txtProveedor.setText(producto.getProveedorProducto());
            this.txtPrecioC.setText(Float.toString(producto.getPrecioCProducto()));
            this.txtPrecioV.setText(Float.toString(producto.getPrecioVProducto()));
        }
    }
    
    @FXML
    void editarProductox(ActionEvent event) {
        this.esEdicion = false;
        if(campoTextoValidoMarcaProducto() && campoTextoValidoProveedorProducto() && ConfirmarEdicion()){
            this.producto.setMarcaProducto(this.txtMarca.getText());
            this.producto.setProveedorProducto(this.txtProveedor.getText());
            this.producto.setPrecioCProducto(Float.parseFloat(this.txtPrecioC.getText()));
            this.producto.setPrecioVProducto(Float.parseFloat(this.txtPrecioV.getText()));
            this.esEdicion = true;
            this.stageDialogoEdicion.close();
        }
    }
    
    private boolean campoTextoValidoMarcaProducto(){
        
        String errorMessage = "";
        
        if(this.txtMarca.getText() == null || this.txtMarca.getText().length() == 0){
          errorMessage +="Verifica el campo! \n";                         
        }
        if(errorMessage.length()==0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("El campo Marca esta vacío");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;            
        }                                       
    }
    
    private boolean campoTextoValidoProveedorProducto(){
        
        String errorMessage = "";
        
        if(this.txtProveedor.getText() == null || this.txtProveedor.getText().length() == 0){
          errorMessage +="Verifica el campo! \n";                         
        }
        if(errorMessage.length()==0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("El campo Proveedor esta vacío");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
            return false;            
        }                                       
    }
    
       /*private boolean campoTextoValidoPrecioCProducto(){
        
        String errorMessage = "";
        
        if(this.txtPrecioC.getText() == null || this.txtPrecioC.getText().length() == 0){
          errorMessage +="Verifica el campo! \n";                         
        }
        if(errorMessage.length()==0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("El precio de compra es mayor al precio de venta");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
            return false;            
        }                                       
    }*/
        
        /*private boolean campoTextoValidoPrecioVProducto(){
        
        String errorMessage = "";
        //this.txtPrecioV.getText().length() >= this.txtPrecioC.getText().length())
        PrecioV = Float.parseFloat(this.txtPrecioV.getText());
        PrecioC = Float.parseFloat(this.txtPrecioC.getText());
        if(this.txtPrecioV.getText() == null || this.txtPrecioV.getText().length() == 0 || PrecioV < PrecioC){
            ConfirmarEdicion();
        }
        if(errorMessage.length()==0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("El precio de compra es mayor al precio de venta");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
            return false;            
        }                                       
    }*/

    @FXML
    void cancelarEdicionx(ActionEvent event) {
        stageDialogoEdicion.close();

    }
    
        
    public void confirmar(){
        Stage stage = (Stage) this.btnEditar.getScene().getWindow();
        AlertaFXML alerta = new AlertaFXML(this.stagePrincipal);
        stageDialogoEdicion.close();
    }

    public boolean ConfirmarEdicion() {
        String errorMessage = "";
        //this.txtPrecioV.getText().length() >= this.txtPrecioC.getText().length())
        PrecioV = Float.parseFloat(this.txtPrecioV.getText());
        PrecioC = Float.parseFloat(this.txtPrecioC.getText());
        if(this.txtPrecioV.getText() == null || this.txtPrecioV.getText().length() == 0 || PrecioV < PrecioC) {
           try{
            AlertaFXML alerta = new AlertaFXML(this.stagePrincipal);
            alerta.alertaConfirmacion("ALERTA", "El precio de compra es mayor al precio de venta", "¿Seguro desea realizar la operacion?");
            FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/vista/Producto/GeneralProductoPantalla.fxml"));
            Parent root1= (Parent)fxmlLoader.load();
            Stage stage= new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            confirmar();
 
        }catch(Exception ex){
            AlertaFXML alerta = new AlertaFXML(this.stageInicio);
            alerta.alertaError("Error", "Ocurrió un error al realizar una operación", ex.getMessage());
        } 
        }
        if(errorMessage.length()==0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("El precio de compra es mayor al precio de venta");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
            return false;            
        }                 
        
    }
}
    

