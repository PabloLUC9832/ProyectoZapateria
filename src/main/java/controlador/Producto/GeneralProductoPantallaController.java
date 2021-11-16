package controlador.Producto;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.AlertaFXML;
import modelo.Producto.Producto;
import modelo.Producto.Producto_DAO_Imp;

/**
 *
 * @author jhair
 */
public class GeneralProductoPantallaController implements Initializable {
    
    @FXML
    private TableColumn<Producto, Integer> columnaClave;

    @FXML
    private TableColumn<Producto, String> columnaDescripcion;

    @FXML
    private TableColumn<Producto, String> columnaMarca;

    @FXML
    private TableColumn<Producto, Float> columnaPrecioC;

    @FXML
    private TableColumn<Producto, Float> columnaPrecioV;

    @FXML
    private TableColumn<Producto, String> columnaProveedor;

    @FXML
    private TableColumn<Producto, Float> columnaStock;

    @FXML
    private TableView<Producto> tablaProductos;
    
    @FXML
    private Label textoClave;

    @FXML
    private Label textoDescripcion;

    @FXML
    private Label textoMarca;

    @FXML
    private Label textoPrecioC;

    @FXML
    private Label textoPrecioV;

    @FXML
    private Label textoProducto;

    @FXML
    private Label textoProveedor;

    @FXML
    private Label textoStock;

    @FXML
    private TextField txtBusqueda;
    
    @FXML
    private JFXButton btnAgregar;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnEliminar;
    
    @FXML
    private JFXButton btnactualizarVentana;
    
    
    private Producto_DAO_Imp producto_DAO;
    private Stage stageDialogoEdicion;
    private Producto producto;
    private ObservableList<Producto> listaProducto;
    private Stage stagePrincipal;
    private EditarProductoPantallaController controlador;
    
    private void setStagePrincipal(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
    }
    
    public void setControlador(EditarProductoPantallaController controlador) {
        this.controlador = controlador;
    }
    
    @Override 
    public void initialize(URL url, ResourceBundle rb) {
        producto_DAO = new Producto_DAO_Imp();
        listaProducto = FXCollections.observableArrayList();
        this.colocarProductosTabla();
        try {
            buscarProducto();
        } catch (Exception ex) {
            String errorMessage = "El tiempo de espera se ha agotado o se perdío la conexión\n" +"con la Base Datos.";
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, No hay conexión con la Base de Datos");
            alert.setHeaderText(" ¡Por favor! intentelo nuevamente");
            alert.setContentText(errorMessage);
            alert.showAndWait();
        }
        this.tablaProductos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> this.mostrarProducto((Producto) newValue));

    }
    
    @FXML
    void actualizarVentana(ActionEvent event){
            colocarProductosTabla();
    }
    
    public void obtenerProductos() {
        List listaConsulta = null;
        
        try{
            listaConsulta = producto_DAO.readAll();
        }catch (Exception ex) {
            String errorMessage = "El tiempo de espera se ha agotado o se perdío la conexión\n" +"con la Base Datos.";
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, No hay conexión con la Base de Datos");
            alert.setHeaderText(" ¡Por favor! intentelo nuevamente");
            alert.setContentText(errorMessage);
            alert.showAndWait();
        }
        Iterator it = listaConsulta.iterator();
        listaProducto.clear();
        while (it.hasNext()) {
            listaProducto.add((Producto) it.next());
        }
    }
    
    public void colocarProductosTabla() {
        this.obtenerProductos();
        this.columnaClave.setCellValueFactory(new PropertyValueFactory<>("claveProducto"));
        this.columnaMarca.setCellValueFactory(new PropertyValueFactory<>("marcaProducto"));
        this.columnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcionProducto"));
        this.columnaProveedor.setCellValueFactory(new PropertyValueFactory<>("proveedorProducto"));
        this.columnaPrecioC.setCellValueFactory(new PropertyValueFactory<>("precioCProducto"));
        this.columnaPrecioV.setCellValueFactory(new PropertyValueFactory<>("precioVProducto"));
        this.columnaStock.setCellValueFactory(new PropertyValueFactory<>("stockProducto"));
        this.tablaProductos.setItems(this.listaProducto);
    }
    
    private void buscarProducto() throws Exception {
        
        txtBusqueda.setOnKeyReleased((e) -> {
            if(txtBusqueda.getText().equals("")){
                try {
                    colocarProductosTabla();
                } catch (Exception ex) {
                    String errorMessage = "El tiempo de espera se ha agotado o se perdío la conexión\n" +"con la Base Datos.";
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error, No hay conexión con la Base de Datos");
                    alert.setHeaderText(" ¡Por favor! intentelo nuevamente");
                    alert.setContentText(errorMessage);
                    alert.showAndWait();
                }
            }else{
                listaProducto.clear();                
                try {
                    listaProducto = producto_DAO.search(txtBusqueda.getText());
                    tablaProductos.setItems(listaProducto);
                } catch (Exception ex) {
                    String errorMessage = "El tiempo de espera se ha agotado o se perdío la conexión\n" +"con la Base Datos.";
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error, No hay conexión con la Base de Datos");
                    alert.setHeaderText(" ¡Por favor! intentelo nuevamente");
                    alert.setContentText(errorMessage);
                    alert.showAndWait();
                }
            }            
        });
    }
    
     
     private void mostrarProducto(Producto producto) {
        if (producto != null) {
            this.textoClave.setText(Integer.toString(producto.getClaveProducto()));
            this.textoMarca.setText(producto.getMarcaProducto());
            this.textoDescripcion.setText(producto.getDescripcionProducto());
            this.textoProveedor.setText(producto.getProveedorProducto());
            this.textoPrecioC.setText(Float.toString(producto.getPrecioCProducto()));
            this.textoPrecioV.setText(Float.toString(producto.getPrecioVProducto()));
            this.textoStock.setText(Integer.toString(producto.getStockProducto()));

        }else {
            this.textoClave.setText("");
            this.textoMarca.setText("");
            this.textoDescripcion.setText("");
            this.textoProveedor.setText("");
            this.textoPrecioC.setText("");
            this.textoPrecioV.setText("");
            this.textoStock.setText("");
        
        }
    }
    
    public boolean mostrarDialogoEditar(Producto producto) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vista/Producto/EditarProductoPantalla.fxml"));
            Parent dialogoEditar = (Parent) loader.load();
            Stage dialogoStage = new Stage();
            dialogoStage.setTitle("Editar Producto");
            dialogoStage.initModality(Modality.WINDOW_MODAL);
            dialogoStage.initOwner(stagePrincipal);
            Scene scene = new Scene(dialogoEditar);
            dialogoStage.setScene(scene);

            EditarProductoPantallaController controlador = loader.getController();
            controlador.setStageDialog(dialogoStage);
            this.setControlador(controlador);
            controlador.setProducto(producto);

            dialogoStage.showAndWait();

            return controlador.getEsEdicion();
        } catch (IOException e) {
            String errorMessage = "El tiempo de espera se ha agotado o se perdío la conexión\n" +"con la Base Datos.";
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, No hay conexión con la Base de Datos");
            alert.setHeaderText(" ¡Por favor! intentelo nuevamente");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
    
    @FXML
    void editarProducto(ActionEvent event) {
        Producto productoSeleccionado = (Producto) this.tablaProductos.getSelectionModel().getSelectedItem();
        if (productoSeleccionado != null) {
            boolean esEdicion = this.mostrarDialogoEditar(productoSeleccionado);
            if (esEdicion) {
                this.mostrarProducto(productoSeleccionado);
                int ultimoSeleccionado = this.tablaProductos.getSelectionModel().getSelectedIndex();
                try {
                    this.producto_DAO.update(productoSeleccionado);
                } catch (Exception ex) {
                    String errorMessage = "El tiempo de espera se ha agotado o se perdío la conexión\n" +"con la Base Datos.";
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error, No hay conexión con la Base de Datos");
                    alert.setHeaderText(" ¡Por favor! intentelo nuevamente");
                    alert.setContentText(errorMessage);
                    alert.showAndWait();
                }
                this.colocarProductosTabla();
                this.tablaProductos.getSelectionModel().select(ultimoSeleccionado);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(this.stagePrincipal);
            alert.setTitle("Sin seleccionar");
            alert.setHeaderText("No hay producto Seleccionado");
            alert.setContentText("Seleccione un producto");
            alert.showAndWait();
        }
    }
    
    @FXML
    void eliminarProducto(ActionEvent event) {

        int selectedIndex = this.tablaProductos.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Producto producto = this.tablaProductos.getSelectionModel().getSelectedItem();
            this.tablaProductos.getSelectionModel().selectLast();           
            try {
                String a = "¿Estas seguro de eliminar  : \n"+producto.getMarcaProducto()+"";
                Stage stage = (Stage) this.btnEliminar.getScene().getWindow();
                AlertaFXML alerta = new AlertaFXML(stage);
                if (alerta.alertaEliminacion("Eliminar", a, "Pulsa Aceptar para eliminar", true)==true) {
                  this.producto_DAO.delete(producto);  
                }
                
            } catch (Exception ex) {
                String errorMessage = "El tiempo de espera se ha agotado o se perdío la conexión\n" +"con la Base Datos.";
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error, No hay conexión con la Base de Datos");
                alert.setHeaderText(" ¡Por favor! intentelo nuevamente");
                alert.setContentText(errorMessage);
                alert.showAndWait();
            }
            this.colocarProductosTabla();
            if(selectedIndex!=0){
                selectedIndex--;
                this.tablaProductos.getSelectionModel().select(selectedIndex);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(this.stagePrincipal);
            alert.setTitle("Ninguna fila seleccionada");
            alert.setHeaderText("No ha seleccionado ningun producto");
            alert.setContentText("Por favor selecciona una fila e intenta eliminar nuevamente.");
            alert.showAndWait();
        }        
        
        
    }
        
    @FXML
    void agregarProducto(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/Producto/CreateProductoPantalla.fxml"));
            Parent ventana = (Parent) fxmlLoader.load();
            Stage stage = new Stage();            
            stage.setScene(new Scene(ventana));
            stage.setTitle("Añadir Producto");
            stage.show();
            
        }catch(IOException e){
            String errorMessage = "El tiempo de espera se ha agotado o se perdío la conexión\n" +"con la Base Datos.";
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, No hay conexión con la Base de Datos");
            alert.setHeaderText(" ¡Por favor! intentelo nuevamente");
            alert.setContentText(errorMessage);
            alert.showAndWait();
        }        
    }
}
