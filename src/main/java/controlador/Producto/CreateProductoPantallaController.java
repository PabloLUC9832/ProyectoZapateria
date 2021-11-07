/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.Producto;
import com.jfoenix.controls.JFXButton;
import controlador.Promocion.CreatePromocionPantallaController;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.AlertaFXML;
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
    
    //GeneralProductoPantallaController gc;

    /*public void setStageDialog(Stage stageDialogoEdicion){
        this.stageDialogoEdicion = stageDialogoEdicion;
    }*/
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        producto_DAO = new Producto_DAO_Imp();
    }
    
    @FXML
    void registrarProducto(ActionEvent event) {
        
        Producto producto = new Producto();
        try{
            if(CamposValidos()) {
               float precioCProducto = Float.parseFloat(txtPrecioC.getText());
               float precioVProducto = Float.parseFloat(txtPrecioV.getText());  
               float stockProducto = Float.parseFloat(txtStock.getText());               
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
    
     private boolean CamposValidos() {
         String errorMessage = "";
        
        if(this.txtDescripcion.getText() == null ||    
                this.txtClave.getText() == null ||
                this.txtPrecioC.getText() == null ||
                this.txtPrecioV.getText() == null ||
                this.txtStock.getText() == null ||
                this.txtMarca.getText() == null ||
                this.txtProveedor.getText() == null) {
            
                errorMessage +="CAMPOS VACIOS";
        }
        
        if(errorMessage.length() == 0) {
            return true;
        }else {
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
    
}
           
