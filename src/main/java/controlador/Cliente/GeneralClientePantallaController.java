package controlador.Cliente;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.Cliente.Cliente;
import modelo.Cliente.Cliente_DAO_Imp;

/**
 * FXML Controller class
 *
 * @author Ivan
 */
public class GeneralClientePantallaController implements Initializable {

    @FXML
    private TableView<Cliente> tablaClientes;
    @FXML
    private TableColumn<Cliente, String> columnaNombre;

    @FXML
    private TableColumn<Cliente, String> columnaEmail;
    
    @FXML
    private JFXButton btnactualizarVentana;
    
    
    private Cliente_DAO_Imp cliente_DAO;
    private ObservableList<Cliente> listaCliente;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cliente_DAO = new Cliente_DAO_Imp();
        listaCliente = FXCollections.observableArrayList();
        this.colocarClientesTabla();
    }
    
    @FXML
    void actualizarVentana(ActionEvent event){
            colocarClientesTabla();
    }
    
    public void obtenerClientes() {
        List listaConsulta = null;
        
        try {
            listaConsulta = cliente_DAO.readAll();
        } catch(Exception ex) {
            String errorMessage = "El tiempo de espera se ha agotado o se perdío la conexión\n" +"con la Base Datos.";
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, No hay conexión con la Base de Datos");
            alert.setHeaderText(" ¡Por favor! intentelo nuevamente");
            alert.setContentText(errorMessage);
            alert.showAndWait();
        }
        Iterator it = listaConsulta.iterator();
        listaCliente.clear();
        while (it.hasNext()) {
            listaCliente.add((Cliente) it.next());
        }
    }
    
    public void colocarClientesTabla() {
        this.obtenerClientes();
        this.columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        this.columnaEmail.setCellValueFactory(new PropertyValueFactory<>("emailCliente"));
        this.tablaClientes.setItems(this.listaCliente);
    }
    
    @FXML
    void agregarCliente(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/Cliente/CreateClientePantalla.fxml"));
            Parent ventana = (Parent) fxmlLoader.load();
            Stage stage = new Stage();            
            stage.setScene(new Scene(ventana));
            stage.setTitle("Añadir Cliente");
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
