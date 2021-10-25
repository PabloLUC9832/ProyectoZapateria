package controlador.Promocion;

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
import modelo.Promocion.Promocion;
import modelo.Promocion.Promocion_DAO_Imp;

public class GeneralPromocionPantallaController implements Initializable {
    
    @FXML
    private TextField txtBusqueda;

    @FXML
    private TableView<Promocion> tablaPromociones;

    @FXML
    private TableColumn<Promocion, Integer> columnaID;

    @FXML
    private TableColumn<Promocion, String> columnaNombreProducto;

    @FXML
    private TableColumn<Promocion, String> columnaMensaje;

    @FXML
    private TableColumn<Promocion, String> columnaDescuento;

    @FXML
    private TableColumn<Promocion, Float> columnaPrecioAnterior;

    @FXML
    private TableColumn<Promocion, Float> columnaPrecioNuevo;
    
    @FXML
    public Label textoId;

    @FXML
    public Label textoNombreProducto;

    @FXML
    public Label textoMensaje;

    @FXML
    public Label textoDescuento;

    @FXML
    public Label textoPrecioAnterior;

    @FXML
    public Label textoPrecioNuevo;
    
    @FXML
    private JFXButton btnEditar;
    
    @FXML
    private JFXButton btnEliminar;
        
    private Promocion_DAO_Imp promocion_DAO;
    public ObservableList<Promocion> listaPromocion;  
    
    //Agregar elemento Stage;
    private Stage stagePrincipal;

    //Agregando para editar
    private void setStagePrincipal(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
    }

    //Agregar
    private EditarPromocionPantallaController controlador;

    public void setControlador(EditarPromocionPantallaController controlador) {
        this.controlador = controlador;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        promocion_DAO = new Promocion_DAO_Imp();
        listaPromocion = FXCollections.observableArrayList();
        this.colocarPromocionesTabla();
        try {
            buscarProducto();
        } catch (Exception ex) {
            Logger.getLogger(GeneralPromocionPantallaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.tablaPromociones.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> this.mostrarPromocion(newValue));
    }

    public void obtenerPromociones(){
        List listaConsulta= null;
        
        try{
            listaConsulta = promocion_DAO.readAll();
        }catch (Exception ex) {
            //AlertaFXML alerta = new AlertaFXML(this.stagePrincipal);
            //alerta.alertaError("Error", "Ocurrió un error al realizar una operación", ex.getMessage());
            System.out.println("ERROR READ ALL "+ex);
        }
        Iterator it = listaConsulta.iterator();
        listaPromocion.clear();
        while (it.hasNext()) {
            listaPromocion.add((Promocion) it.next());
        }
        
    }
    
    public void colocarPromocionesTabla() {
        this.obtenerPromociones();
        this.columnaID.setCellValueFactory(new PropertyValueFactory<>("idPromocion"));
        this.columnaNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        this.columnaMensaje.setCellValueFactory(new PropertyValueFactory<>("mensaje"));
        this.columnaDescuento.setCellValueFactory(new PropertyValueFactory<>("descuento"));
        this.columnaPrecioAnterior.setCellValueFactory(new PropertyValueFactory<>("precioAnterior"));
        this.columnaPrecioNuevo.setCellValueFactory(new PropertyValueFactory<>("precioNuevo"));
        this.tablaPromociones.setItems(this.listaPromocion);
    }  
    
    private void buscarProducto() throws Exception{
                       
        //String busqueda = this.txtBusqueda.getText();
         
        txtBusqueda.setOnKeyReleased((e) -> {
            if(txtBusqueda.getText().equals("")){
            //if(busqueda.equals("")){
                try {
                    colocarPromocionesTabla();
                } catch (Exception ex) {
                    Logger.getLogger(GeneralPromocionPantallaController.class.getName()).log(Level.SEVERE, null, ex);                    
                }
            }else{
                listaPromocion.clear();                
                try {
                    //listaEmpleado.clear();                    
                    listaPromocion = promocion_DAO.search(txtBusqueda.getText());
                    //listaEmpleado = empleado_DAO.search(busqueda);
                    tablaPromociones.setItems(listaPromocion);
                } catch (Exception ex) {
                    Logger.getLogger(GeneralPromocionPantallaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
                  
            
        });
        
 
        
    }
    
    private void mostrarPromocion(Promocion promocion) {
        if (promocion != null) {
            this.textoId.setText(Integer.toString(promocion.getIdPromocion()));
            this.textoNombreProducto.setText(promocion.getNombreProducto());
            this.textoMensaje.setText(promocion.getMensaje());
            this.textoDescuento.setText(promocion.getDescuento());
            this.textoPrecioAnterior.setText(Float.toString(promocion.getPrecioAnterior()));
            this.textoPrecioNuevo.setText(Float.toString(promocion.getPrecioNuevo()));
        } else {
            this.textoId.setText("");
            this.textoNombreProducto.setText("");
            this.textoMensaje.setText("");
            this.textoDescuento.setText("");
            this.textoPrecioAnterior.setText("");
            this.textoPrecioNuevo.setText("");
        }
    }
    
     public boolean mostrarDialogoEditar(Promocion promocion) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vista/Promocion/EditarPromocionPantalla.fxml"));
            Parent dialogoEditar = (Parent) loader.load();
            Stage dialogoStage = new Stage();
            dialogoStage.setTitle("Editar Promocion");
            dialogoStage.initModality(Modality.WINDOW_MODAL);
            dialogoStage.initOwner(stagePrincipal);
            Scene scene = new Scene(dialogoEditar);
            dialogoStage.setScene(scene);

            EditarPromocionPantallaController controlador = loader.getController();
            controlador.setStageDialog(dialogoStage);
            this.setControlador(controlador);
            controlador.setPromocion(promocion);

            dialogoStage.showAndWait();

            return controlador.getEsEdicion();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @FXML
    private void editarPromocion(ActionEvent event) {
        Promocion promocionSeleccionada = this.tablaPromociones.getSelectionModel().getSelectedItem();
        if (promocionSeleccionada != null) {
            boolean esEdicion = this.mostrarDialogoEditar(promocionSeleccionada);
            if (esEdicion) {
                this.mostrarPromocion(promocionSeleccionada);
                int ultimoSeleccionado = this.tablaPromociones.getSelectionModel().getSelectedIndex();
                try {
                    this.promocion_DAO.update(promocionSeleccionada);
                } catch (Exception ex) {
                    //AlertaFXML alerta = new AlertaFXML(this.stagePrincipal);
                    //alerta.alertaError("Error", "Ocurrió un error al realizar una operación", ex.getMessage());
                    Logger.getLogger(MainPantallaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.colocarPromocionesTabla();
                this.tablaPromociones.getSelectionModel().select(ultimoSeleccionado);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(this.stagePrincipal);
            alert.setTitle("Sin seleccionar");
            alert.setHeaderText("No hay promocion Seleccionada");
            alert.setContentText("Seleccione una promocion");
            alert.showAndWait();
        }
    }
    
    @FXML
    void eliminarPromocion(ActionEvent event) {

        int selectedIndex = this.tablaPromociones.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Promocion promocion = this.tablaPromociones.getSelectionModel().getSelectedItem();
            this.tablaPromociones.getSelectionModel().selectLast();           
            try {
                this.promocion_DAO.delete(promocion);
            } catch (Exception ex) {
                Logger.getLogger(GeneralPromocionPantallaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.colocarPromocionesTabla();
            if(selectedIndex!=0){
                selectedIndex--;
                this.tablaPromociones.getSelectionModel().select(selectedIndex);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(this.stagePrincipal);
            alert.setTitle("Ninguna fila seleccionada");
            alert.setHeaderText("No ha seleccionado ninguna promoción");
            alert.setContentText("Por favor selecciona una fila e intenta eliminar nuevamente.");
            alert.showAndWait();
        }        
        
        
    }    
    
}
