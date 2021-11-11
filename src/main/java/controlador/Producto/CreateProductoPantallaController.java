package controlador.Producto;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Producto.Producto;
import modelo.Producto.Producto_DAO_Imp;

/**
 *
 * @author jhair
 */
public class CreateProductoPantallaController implements Initializable {
    
    @FXML
    private JFXButton btnRegistrar;

    @FXML
    private TextField txtClave;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtPrecioC;

    @FXML
    private TextField txtPrecioV;

    @FXML
    private TextField txtProveedor;

    @FXML
    private TextField txtStock;
    
    private Producto_DAO_Imp producto_DAO;
    private Stage stageDialogoEdicion;
    private Producto producto;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        producto_DAO = new Producto_DAO_Imp();
    }
    
    @FXML
    void registrarProducto(ActionEvent event) {
        
        Producto producto = new Producto();
        try{
            if(campoTextoValidoMarcaProducto() && campoTextoValidoDescripcionProducto() && campoTextoValidoProveedorProducto() &&
                    campoNumericoPrecioCompraProducto() && campoNuloPrecioCompraProducto() && campoNumericoPrecioVentaProducto() &&
                    campoNuloPrecioVentaProducto() && campoNumericoStock() && campoNuloStock()) {
               float precioCProducto = Float.parseFloat(txtPrecioC.getText());
               float precioVProducto = Float.parseFloat(txtPrecioV.getText());  
               int stockProducto = Integer.parseInt(txtStock.getText());              
               String marcaProducto = this.txtMarca.getText();
               String descripcionProducto = this.txtDescripcion.getText();
               String proveedorProducto = this.txtProveedor.getText();
               producto = new Producto(0, marcaProducto, descripcionProducto, proveedorProducto, precioCProducto, precioVProducto, stockProducto);
               
               if(this.producto_DAO.create(producto)==true){
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
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
            return false;            
        }                                        
    }
    
    private boolean campoTextoValidoDescripcionProducto(){
        
        String errorMessage = "";
        
        if(this.txtDescripcion.getText() == null || this.txtDescripcion.getText().length() == 0){
          errorMessage +="Verifica el campo! \n";                         
        }
        if(errorMessage.length()==0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("El campo Descripcion esta vacío");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
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
    
    private boolean campoNuloPrecioCompraProducto(){
        String errorMessage = "";
        if(this.txtPrecioC.getText() == null || this.txtPrecioC.getText().length() == 0 ){
            errorMessage += "Verifica el campo! \n";
        }
                
        if(errorMessage.length() == 0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("El campo Precio Compra esta vacío");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
            return false;
        }
    }
    
    private boolean campoNumericoPrecioCompraProducto(){
        boolean numero = false;
        String precioCompra = this.txtPrecioC.getText();
        for(int i = 0;i<precioCompra.length();i++){
            if(precioCompra.charAt(i) == '1' || precioCompra.charAt(i) == '2'|| precioCompra.charAt(i) == '3' ||
                    precioCompra.charAt(i) == '4' || precioCompra.charAt(i) == '5' || precioCompra.charAt(i) == '6' ||
                    precioCompra.charAt(i) == '7' || precioCompra.charAt(i) == '8' || precioCompra.charAt(i) == '9' ||
                    precioCompra.charAt(i) == '.'){
                numero = true;
                break;
            }
        }
        return numero;
    }
    
    private boolean campoNuloPrecioVentaProducto(){
        String errorMessage = "";
        if(this.txtPrecioV.getText() == null || this.txtPrecioV.getText().length() == 0 ){
            errorMessage += "Verifica el campo! \n";
        }
                
        if(errorMessage.length() == 0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("El campo Precio Venta esta vacío");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
            return false;
        }
    }
    
    private boolean campoNumericoPrecioVentaProducto(){
        boolean numero = false;
        String precioVenta = this.txtPrecioC.getText();
        for(int i = 0;i<precioVenta.length();i++){
            if(precioVenta.charAt(i) == '1' || precioVenta.charAt(i) == '2'|| precioVenta.charAt(i) == '3' ||
                    precioVenta.charAt(i) == '4' || precioVenta.charAt(i) == '5' || precioVenta.charAt(i) == '6' ||
                    precioVenta.charAt(i) == '7' || precioVenta.charAt(i) == '8' || precioVenta.charAt(i) == '9' ||
                    precioVenta.charAt(i) == '.'){
                numero = true;
                break;
            }
        }
        return numero;
    }
    
    private boolean campoNuloStock(){
        String errorMessage = "";
        if(this.txtStock.getText() == null || this.txtStock.getText().length() == 0 ){
            errorMessage += "Verifica el campo! \n";
        }
                
        if(errorMessage.length() == 0){
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("El campo Stock esta vacío");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
            return false;
        }
    }
    
    private boolean campoNumericoStock(){
        boolean numero = false;
        String stock = this.txtStock.getText();
        for(int i = 0;i<stock.length();i++){
            if(stock.charAt(i) == '1' || stock.charAt(i) == '2'|| stock.charAt(i) == '3' ||
                    stock.charAt(i) == '4' || stock.charAt(i) == '5' || stock.charAt(i) == '6' ||
                    stock.charAt(i) == '7' || stock.charAt(i) == '8' || stock.charAt(i) == '9' ||
                    stock.charAt(i) == '.'){
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
           
