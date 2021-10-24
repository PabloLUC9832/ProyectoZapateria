package controlador.Promocion;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    
    private Promocion_DAO_Imp promocion_DAO;
    public ObservableList<Promocion> listaPromocion;  
    
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
    
}

