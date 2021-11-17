package controlador.Inventario;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Inventario.ProductoInv;
import modelo.Inventario.ProductoInv_DAO_Imp;

/**
 * FXML Controller class
 *
 * @author ferna
 */
public class CreateInventarioPantallaController implements Initializable {

    @FXML
    private TableColumn<ProductoInv, Integer> columnaClave;

    @FXML
    private TableColumn<ProductoInv, String> columnaDescripcion;

    @FXML
    private TableColumn<ProductoInv, String> columnaMarca;

    @FXML
    private TableColumn<ProductoInv, Float> columnaPrecioC;

    @FXML
    private TableColumn<ProductoInv, String> columnaProveedor;

    @FXML
    private TableColumn<ProductoInv, Float> columnaStock;

    @FXML
    private TableColumn<ProductoInv, String> columnaFecha;
    
    @FXML
    private TableView<ProductoInv> tablaProductos;

    @FXML
    private TextField txtBusqueda;
    
    private ProductoInv_DAO_Imp Inventario_DAO;
    
    private ObservableList<ProductoInv> listaInventario;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("puta lawea ");
        Inventario_DAO = new ProductoInv_DAO_Imp();
        try{
            //System.out.println("puta lawea 2");
            listaInventario = FXCollections.observableArrayList();
        }catch(Exception e){
            //System.out.println("ERROR: "+e.getMessage());
        }
        
        this.colocarInventarioTabla();                
    }    

    public void obtenerInventario() {
        List listaConsulta = null;
        
        try {
            listaConsulta = Inventario_DAO.readAll();
        } catch(Exception ex) {
            System.out.println("Error Read All " + ex);
        }
        Iterator it = listaConsulta.iterator();
        listaInventario.clear();
        System.out.println(listaConsulta.size());
        while (it.hasNext()) {
            listaInventario.add((ProductoInv) it.next());
        }
        //System.out.println("WEA!"+listaInventario.get(9).toString());
    }
    public void colocarInventarioTabla() {
        this.obtenerInventario();
        this.columnaClave.setCellValueFactory(new PropertyValueFactory<>("claveProducto"));
        this.columnaMarca.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getMarcaProducto()));
        this.columnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcionProducto"));       
        this.columnaProveedor.setCellValueFactory(new PropertyValueFactory<>("proveedorProducto"));
        this.columnaPrecioC.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper(cellData.getValue().getPrecioCProducto()));        
        this.columnaStock.setCellValueFactory(new PropertyValueFactory<>("stockProducto"));
        this.columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        this.tablaProductos.setItems(this.listaInventario);
    }    
        @FXML
    public void GenerarPDFHistorialCP(ActionEvent event) throws FileNotFoundException, DocumentException{
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("Historial de compras al proveedor.pdf"));
        document.open();
        for(int i=0; i<tablaProductos.getItems().size(); i++){
            document.add(new Paragraph(tablaProductos.getItems().get(i).formatoPDF()));
        }
        document.close();
    }
}
