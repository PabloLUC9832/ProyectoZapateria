/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.Ventas;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
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
import modelo.Ventas.Ventas;
import modelo.Ventas.Ventas_DAO;
import modelo.Ventas.Ventas_DAO_Imp;

/**
 * FXML Controller class
 *
 * @author ferna
 */
public class GeneralVentasPantallaController implements Initializable {

   
    @FXML
    private TableColumn<Ventas, String> columnaArticulos;

    @FXML
    private TableColumn<Ventas, Integer> columnaDisponibles;

    @FXML
    private TableColumn<Ventas, String> columnaFecha;

    @FXML
    private TableColumn<Ventas, Integer> columnaNameV;

    @FXML
    private TableColumn<Ventas, Integer> columnaPricio;

    @FXML
    private TableColumn<Ventas, Integer> columnaTotal;

    @FXML
    private TableView<Ventas> tablaVentas;
    
    private Ventas_DAO_Imp ventas_DAO;
    
    private ObservableList<Ventas> listaVentas;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ventas_DAO = new Ventas_DAO_Imp();
        listaVentas = FXCollections.observableArrayList();
        this.colocarVentasTabla();                
    }
    public void obtenerVentas() {
        List listaConsulta = null;
        
        try {
            listaConsulta = ventas_DAO.readAll();
        } catch(Exception ex) {
            System.out.println("Error Read All " + ex);
        }
        Iterator it = listaConsulta.iterator();
        listaVentas.clear();
        while (it.hasNext()) {
            listaVentas.add((Ventas) it.next());
        }
    }
    public void colocarVentasTabla() {
        this.obtenerVentas();
        this.columnaNameV.setCellValueFactory(new PropertyValueFactory<>("IdVenta"));
        this.columnaArticulos.setCellValueFactory(new PropertyValueFactory<>("NameV"));
        this.columnaPricio.setCellValueFactory(new PropertyValueFactory<>("PriceV"));
        this.columnaDisponibles.setCellValueFactory(new PropertyValueFactory<>("QuantityV"));
        this.columnaTotal.setCellValueFactory(new PropertyValueFactory<>("Total"));
        this.columnaFecha.setCellValueFactory(new PropertyValueFactory<>("Date"));
        this.tablaVentas.setItems(this.listaVentas);
    }        
    
}
