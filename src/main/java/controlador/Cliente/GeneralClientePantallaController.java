package controlador.Cliente;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    
    private Cliente_DAO_Imp cliente_DAO;
    private ObservableList<Cliente> listaCliente;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cliente_DAO = new Cliente_DAO_Imp();
        listaCliente = FXCollections.observableArrayList();
        this.colocarClientesTabla();
    }
    
    public void obtenerClientes() {
        List listaConsulta = null;
        
        try {
            listaConsulta = cliente_DAO.readAll();
        } catch(Exception ex) {
            System.out.println("Error Read All " + ex);
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
      
}
