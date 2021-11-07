/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.Producto;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
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

    
    private Producto_DAO_Imp producto_DAO;
    //private Stage stageDialogoEdicion;
    private Producto producto;
    //private ArrayList<Object> lstFile;
    private ObservableList<Producto> listaProducto;
    
    @Override 
    public void initialize(URL url, ResourceBundle rb) {
        producto_DAO = new Producto_DAO_Imp();
        listaProducto = FXCollections.observableArrayList();
        this.colocarProductosTabla();
        
        this.tablaProductos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> this.mostrarProducto((Producto) newValue));

    }
    
    public void obtenerProductos() {
        List listaConsulta = null;
        
        try{
            listaConsulta = producto_DAO.readAll();
        }catch (Exception ex) {
            System.out.println("ERROR EN READ ALL" +ex);
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
    
    void agregarProducto(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/Producto/CreateProductoPantalla.fxml"));
            Parent ventana = (Parent) fxmlLoader.load();
            Stage stage = new Stage();            
            stage.setScene(new Scene(ventana));
            stage.setTitle("Añadir Producto");
            stage.show();
        }catch(IOException e) {
            System.out.println("ERROR AL MOSTRAR LA VENTANA: "+e);
        }
    }
    
     private void mostrarProducto(Producto producto) {
        if (producto != null) {
            this.textoClave.setText(Integer.toString(producto.getClaveProducto()));
            this.textoMarca.setText(producto.getMarcaProducto());
            this.textoDescripcion.setText(producto.getDescripcionProducto());
            this.textoProveedor.setText(producto.getProveedorProducto());
            this.textoPrecioC.setText(Float.toString(producto.getPrecioCProducto()));
            this.textoPrecioV.setText(Float.toString(producto.getPrecioVProducto()));
            this.textoStock.setText(Float.toString(producto.getStockProducto()));

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
    
}
