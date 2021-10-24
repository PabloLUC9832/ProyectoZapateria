package controlador.Empleado;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import modelo.ConexionDB;
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
    
    private Empleado_DAO_Imp empleado_DAO;
    public ObservableList<Empleado> listaEmpleado;    
        
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
                       
        //String busqueda = this.txtBusqueda.getText();
         
        txtBusqueda.setOnKeyReleased((e) -> {
            if(txtBusqueda.getText().equals("")){
            //if(busqueda.equals("")){
                try {
                    colocarEmpleadosTabla();
                } catch (Exception ex) {
                    Logger.getLogger(GeneralEmpleadoPantallaController.class.getName()).log(Level.SEVERE, null, ex);                    
                }
            }else{
                listaEmpleado.clear();                
                try {
                    //listaEmpleado.clear();                    
                    listaEmpleado = empleado_DAO.search(txtBusqueda.getText());
                    //listaEmpleado = empleado_DAO.search(busqueda);
                    tablaEmpleados.setItems(listaEmpleado);
                } catch (Exception ex) {
                    Logger.getLogger(GeneralEmpleadoPantallaController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
                  
            
        });
        
 
        
    }
    
}
