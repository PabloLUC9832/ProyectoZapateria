package controlador.Proveedor;

import com.jfoenix.controls.JFXButton;
import controlador.MainPantallaController;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import modelo.Proveedor.Proveedor;
import modelo.Proveedor.Proveedor_DAO_Imp;

public class GeneralProveedorPantallaController implements Initializable {
    
    @FXML
    private TextField txtBusqueda;

    @FXML
    private TableView<Proveedor> tablaProveedor;

    @FXML
    private TableColumn<Proveedor, String> columnaNombreProveedor;

    @FXML
    private TableColumn<Proveedor, String> columnaTelefono;

    @FXML
    private TableColumn<Proveedor, String> columnaDireccion;

    @FXML
    private TableColumn<Proveedor, String> columnaProducto;
    
    @FXML
    private TableColumn<Proveedor, Integer> columnaId;

    @FXML
    private Label textoNombreProveedor;

    @FXML
    private Label textoTelefono;

    @FXML
    private Label textoDireccion;

    @FXML
    private Label textoProducto;

    @FXML
    private JFXButton btnAgregar;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnEliminar;
    
    @FXML
    private JFXButton btnactualizarVentana;
    
    @FXML
    private Label textoId;
        
    private Proveedor_DAO_Imp proveedor_DAO;
    public ObservableList<Proveedor> listaProveedor;  
    
    //Agregar elemento Stage;
    private Stage stagePrincipal;

    //Agregando para editar
    private void setStagePrincipal(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
    }

    //Agregar
    private EditarProveedorPantallaController controlador;

    public void setControlador(EditarProveedorPantallaController controlador) {
        this.controlador = controlador;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        proveedor_DAO = new Proveedor_DAO_Imp();
        listaProveedor = FXCollections.observableArrayList();
        this.colocarProveedorTabla();
        try {
            buscarProveedor();
        } catch (Exception ex) {
            String errorMessage = "El tiempo de espera se ha agotado o se perdío la conexión\n" +"con la Base Datos.";
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, No hay conexión con la Base de Datos");
            alert.setHeaderText(" ¡Por favor! intentelo nuevamente");
            alert.setContentText(errorMessage);
            alert.showAndWait();
        }
        this.tablaProveedor.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> this.mostrarProveedor((Proveedor) newValue));
    }
    
    @FXML
    void actualizarVentana(ActionEvent event){
            colocarProveedorTabla();
    }

    public void obtenerProveedor(){
        List listaConsulta= null;
        
        try{
            listaConsulta = proveedor_DAO.readAll();
        }catch (Exception ex) {
            String errorMessage = "El tiempo de espera se ha agotado o se perdío la conexión\n" +"con la Base Datos.";
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, No hay conexión con la Base de Datos");
            alert.setHeaderText(" ¡Por favor! intentelo nuevamente");
            alert.setContentText(errorMessage);
            alert.showAndWait();
        }
        Iterator it = listaConsulta.iterator();
        listaProveedor.clear();
        while (it.hasNext()) {
            listaProveedor.add((Proveedor) it.next());
        }
        
    }
    
    public void colocarProveedorTabla() {
        this.obtenerProveedor();
        this.columnaId.setCellValueFactory(new PropertyValueFactory<>("ProveedorId"));
        this.columnaNombreProveedor.setCellValueFactory(new PropertyValueFactory<>("ProveedorNombre"));
        this.columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("ProveedorTelefono"));
        this.columnaDireccion.setCellValueFactory(new PropertyValueFactory<>("ProveedorDireccion"));
        this.columnaProducto.setCellValueFactory(new PropertyValueFactory<>("ProveedorProducto"));
        this.tablaProveedor.setItems(this.listaProveedor);
    }  
    
    private void buscarProveedor() throws Exception{
                                
        txtBusqueda.setOnKeyReleased((e) -> {
            if(txtBusqueda.getText().equals("")){
                try {
                    colocarProveedorTabla();
                } catch (Exception ex) {
                    String errorMessage = "El tiempo de espera se ha agotado o se perdío la conexión\n" +"con la Base Datos.";
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error, No hay conexión con la Base de Datos");
                    alert.setHeaderText(" ¡Por favor! intentelo nuevamente");
                    alert.setContentText(errorMessage);
                    alert.showAndWait();
                }
            }else{
                listaProveedor.clear();                
                try {                 
                    listaProveedor = proveedor_DAO.search(txtBusqueda.getText());
                    tablaProveedor.setItems(listaProveedor);
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
    
    private void mostrarProveedor(Proveedor proveedor) {
        if (proveedor != null) {
            this.textoId.setText(Integer.toString(proveedor.getProveedorId()));
            this.textoNombreProveedor.setText(proveedor.getProveedorNombre());
            this.textoTelefono.setText(proveedor.getProveedorTelefono());
            this.textoDireccion.setText(proveedor.getProveedorDireccion());
            this.textoProducto.setText(proveedor.getProveedorProducto());
        }else {
            this.textoId.setText("");
            this.textoNombreProveedor.setText("");
            this.textoTelefono.setText("");
            this.textoDireccion.setText("");
            this.textoProducto.setText("");
        
        }
    }
    
     public boolean mostrarDialogoEditar(Proveedor proveedor) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vista/Proveedor/EditarProveedorPantalla.fxml"));
            Parent dialogoEditar = (Parent) loader.load();
            Stage dialogoStage = new Stage();
            dialogoStage.setTitle("Editar Proveedor");
            dialogoStage.initModality(Modality.WINDOW_MODAL);
            dialogoStage.initOwner(stagePrincipal);
            Scene scene = new Scene(dialogoEditar);
            dialogoStage.setScene(scene);

            EditarProveedorPantallaController controlador = loader.getController();
            controlador.setStageDialog(dialogoStage);
            this.setControlador(controlador);
            controlador.setProveedor(proveedor);

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
    private void editarProveedor(ActionEvent event) {
        Proveedor proveedorSeleccionado = (Proveedor) this.tablaProveedor.getSelectionModel().getSelectedItem();
        if (proveedorSeleccionado != null) {
            boolean esEdicion = this.mostrarDialogoEditar(proveedorSeleccionado);
            if (esEdicion) {
                this.mostrarProveedor(proveedorSeleccionado);
                int ultimoSeleccionado = this.tablaProveedor.getSelectionModel().getSelectedIndex();
                try {
                    this.proveedor_DAO.update(proveedorSeleccionado);
                } catch (Exception ex) {
                    String errorMessage = "Intento agregar un proveedor existente en la Base Datos.";
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error, No hay conexión con la Base de Datos");
                    alert.setHeaderText(" ¡Por favor! intentelo nuevamente");
                    alert.setContentText(errorMessage);
                    alert.showAndWait();
                }
                this.colocarProveedorTabla();
                this.tablaProveedor.getSelectionModel().select(ultimoSeleccionado);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(this.stagePrincipal);
            alert.setTitle("Sin seleccionar");
            alert.setHeaderText("No hay Proveedor Seleccionado");
            alert.setContentText("Seleccione una promocion");
            alert.showAndWait();
        }
    }
    
    @FXML
    void eliminarProveedor(ActionEvent event) {

        int selectedIndex = this.tablaProveedor.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Proveedor proveedor = (Proveedor) this.tablaProveedor.getSelectionModel().getSelectedItem();
            this.tablaProveedor.getSelectionModel().selectLast();           
            try {
                String a = "¿Estas seguro de eliminar  : \n"+proveedor.getProveedorNombre()+"";
                Stage stage = (Stage) this.btnEliminar.getScene().getWindow();
                AlertaFXML alerta = new AlertaFXML(stage);
                if (alerta.alertaConfirmacion("Eliminar", a, "Pulsa Aceptar para eliminar")==true) {
                  this.proveedor_DAO.delete(proveedor);  
                }
                
            } catch (Exception ex) {
                String errorMessage = "El tiempo de espera se ha agotado o se perdío la conexión\n" +"con la Base Datos.";
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error, No hay conexión con la Base de Datos");
                alert.setHeaderText(" ¡Por favor! intentelo nuevamente");
                alert.setContentText(errorMessage);
                alert.showAndWait();
            }
            this.colocarProveedorTabla();
            if(selectedIndex!=0){
                selectedIndex--;
                this.tablaProveedor.getSelectionModel().select(selectedIndex);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(this.stagePrincipal);
            alert.setTitle("Ninguna fila seleccionada");
            alert.setHeaderText("No ha seleccionado ningun proveedor");
            alert.setContentText("Por favor selecciona una fila e intenta eliminar nuevamente.");
            alert.showAndWait();
        }        
        
        
    }    
    
    @FXML
    void agregarProveedor(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/Proveedor/CreateProveedorPantalla.fxml"));
            Parent ventana = (Parent) fxmlLoader.load();
            Stage stage = new Stage();            
            stage.setScene(new Scene(ventana));
            stage.setTitle("Añadir Proveedor");
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
