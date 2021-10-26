package controlador.Empleado;

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
import modelo.Empleado.Empleado;
import modelo.Empleado.Empleado_DAO_Imp;
/**
 * FXML Controller class
 *
 * @author Pablo
 */
public class GeneralEmpleadoPantallaController implements Initializable {

    @FXML
    private TextField txtBusqueda;

    @FXML
    public TableView<Empleado> tablaEmpleados;

    @FXML
    private TableColumn<Empleado, Integer> columnaID;

    @FXML
    private TableColumn<Empleado, String> columnaNombre;

    @FXML
    private TableColumn<Empleado, String> columnaUsuario;

    @FXML
    private TableColumn<Empleado, String> columnaContraseña;

    @FXML
    private TableColumn<Empleado, String> columnaPuesto;

   @FXML
    private Label textoId;

    @FXML
    private Label textoNombreEmpleado;

    @FXML
    private Label textoUsuario;

    @FXML
    private Label textoPass;

    @FXML
    private Label textoPuesto;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnEliminar;    
    
    private Empleado_DAO_Imp empleado_DAO;
    public ObservableList<Empleado> listaEmpleado;    
    private Stage stagePrincipal;
    private EditarEmpleadoPantallaController controlador;

    private void setStagePrincipal(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
    }
    
    public void setControlador(EditarEmpleadoPantallaController controlador) {
        this.controlador = controlador;
    }    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        empleado_DAO = new Empleado_DAO_Imp();
        listaEmpleado = FXCollections.observableArrayList();
        this.colocarEmpleadosTabla();
        try {
            buscarProducto();
        } catch (Exception ex) {
            Logger.getLogger(GeneralEmpleadoPantallaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.tablaEmpleados.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> this.mostrarEmpleado(newValue));
                   
    }

    public void obtenerEmpleados(){
        List listaConsulta= null;
        
        try{
            listaConsulta = empleado_DAO.readAll();
        }catch (Exception ex) {
            //AlertaFXML alerta = new AlertaFXML(this.stagePrincipal);
            //alerta.alertaError("Error", "Ocurrió un error al realizar una operación", ex.getMessage());
            System.out.println("ERROR READ ALL "+ex);
        }
        Iterator it = listaConsulta.iterator();
        listaEmpleado.clear();
        while (it.hasNext()) {
            listaEmpleado.add((Empleado) it.next());
        }
        
    }
    
    public void colocarEmpleadosTabla() {
        this.obtenerEmpleados();
        this.columnaID.setCellValueFactory(new PropertyValueFactory<>("Nempleado"));
        this.columnaNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        this.columnaUsuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        this.columnaContraseña.setCellValueFactory(new PropertyValueFactory<>("pass"));
        this.columnaPuesto.setCellValueFactory(new PropertyValueFactory<>("puesto"));
        this.tablaEmpleados.setItems(this.listaEmpleado);
    }  
    
    private void buscarProducto() throws Exception{
                                
        txtBusqueda.setOnKeyReleased((e) -> {
            if(txtBusqueda.getText().equals("")){
                try {
                    colocarEmpleadosTabla();
                } catch (Exception ex) {
                    Logger.getLogger(GeneralEmpleadoPantallaController.class.getName()).log(Level.SEVERE, null, ex);                    
                }
            }else{
                listaEmpleado.clear();                
                try {
                    listaEmpleado = empleado_DAO.search(txtBusqueda.getText());
                    tablaEmpleados.setItems(listaEmpleado);
                } catch (Exception ex) {
                    Logger.getLogger(GeneralEmpleadoPantallaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
                  
            
        });
        
 
        
    }

    private void mostrarEmpleado(Empleado empleado) {
        if (empleado != null) {
            this.textoId.setText(Integer.toString(empleado.getNempleado()));
            this.textoNombreEmpleado.setText(empleado.getNombre());
            this.textoUsuario.setText(empleado.getUsuario());
            this.textoPass.setText(empleado.getPass());
            this.textoPuesto.setText(empleado.getPuesto());
        } else {
            this.textoId.setText("");
            this.textoNombreEmpleado.setText("");
            this.textoUsuario.setText("");
            this.textoPass.setText("");
            this.textoPuesto.setText("");
        }
    }

     public boolean mostrarDialogoEditar(Empleado empleado) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vista/Empleado/EditarEmpleadoPantalla.fxml"));
            Parent dialogoEditar = (Parent) loader.load();
            Stage dialogoStage = new Stage();
            dialogoStage.setTitle("Editar Personal");
            dialogoStage.initModality(Modality.WINDOW_MODAL);
            dialogoStage.initOwner(stagePrincipal);
            Scene scene = new Scene(dialogoEditar);
            dialogoStage.setScene(scene);

            EditarEmpleadoPantallaController controlador = loader.getController();
            controlador.setStageDialog(dialogoStage);
            this.setControlador(controlador);
            controlador.setEmpleado(empleado);

            dialogoStage.showAndWait();

            return controlador.getEsEdicion();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }    
    
    @FXML
    void editarEmpleado(ActionEvent event) {
        
        Empleado empleadoSeleccionada = this.tablaEmpleados.getSelectionModel().getSelectedItem();
        if (empleadoSeleccionada != null) {
            boolean esEdicion = this.mostrarDialogoEditar(empleadoSeleccionada);
            if (esEdicion) {
                this.mostrarEmpleado(empleadoSeleccionada);
                int ultimoSeleccionado = this.tablaEmpleados.getSelectionModel().getSelectedIndex();
                try {
                    this.empleado_DAO.update(empleadoSeleccionada);
                } catch (Exception ex) {
                    //AlertaFXML alerta = new AlertaFXML(this.stagePrincipal);
                    //alerta.alertaError("Error", "Ocurrió un error al realizar una operación", ex.getMessage());
                    Logger.getLogger(MainPantallaController.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.colocarEmpleadosTabla();
                this.tablaEmpleados.getSelectionModel().select(ultimoSeleccionado);
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
    void eliminarEmpleado(ActionEvent event) {

        int selectedIndex = this.tablaEmpleados.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Empleado empleado = this.tablaEmpleados.getSelectionModel().getSelectedItem();
            this.tablaEmpleados.getSelectionModel().selectLast();           
            try {
                String a = "¿Estas seguro de eliminar  : \n"+empleado.getNombre()+"";
                Stage stage = (Stage) this.btnEliminar.getScene().getWindow();
                AlertaFXML alerta = new AlertaFXML(stage);
                //alerta.alertaConfirmacion("Eliminar", a, "Pulsa Aceptar para eliminar");
                //this.promocion_DAO.delete(promocion);
                if (alerta.alertaConfirmacion("Eliminar", a, "Pulsa Aceptar para eliminar")==true) {
                  this.empleado_DAO.delete(empleado);  
                }
                
            } catch (Exception ex) {
                Logger.getLogger(GeneralEmpleadoPantallaController.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.colocarEmpleadosTabla();
            if(selectedIndex!=0){
                selectedIndex--;
                this.tablaEmpleados.getSelectionModel().select(selectedIndex);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(this.stagePrincipal);
            alert.setTitle("Ninguna fila seleccionada");
            alert.setHeaderText("No ha seleccionado ningún empleado");
            alert.setContentText("Por favor selecciona una fila e intenta eliminar nuevamente.");
            alert.showAndWait();
        }        
        
        
    }       
    
}
