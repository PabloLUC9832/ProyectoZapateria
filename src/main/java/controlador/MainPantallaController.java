package controlador;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainPantallaController implements Initializable {

    @FXML
    private ImageView btnExit;

    @FXML
    private Label btnMenu1;

    @FXML
    private Label btnMenu2;

    @FXML
    private AnchorPane slider;    
    
    @FXML
    private StackPane contentArea;
         
    @FXML
    private JFXButton btnHorario;

    @FXML
    private JFXButton btnProducto;

    @FXML
    private JFXButton btnPersonal;

    @FXML
    private JFXButton btnProveedor;

    @FXML
    private JFXButton btnCliente;

    @FXML
    private JFXButton btnPromocion;
    @FXML
    private JFXButton btnCerrarSesion;    
    
    public  String puestoM;
    @FXML
    private Label etiquetaPuesto;    
        
    public void getDatos(String puesto){
        etiquetaPuesto.setText(puesto);
        puestoM = puesto;
    } 

    @Override
    public void initialize(URL url, ResourceBundle rb) {               

        etiquetaPuesto.setVisible(false);
        btnExit.setOnMouseClicked(event ->{
            System.exit(0);
        });
        
        slider.setTranslateX(-176);
        
        btnMenu2.setOnMouseClicked(event->{
            TranslateTransition slide = new TranslateTransition();
            slide.setDelay(Duration.seconds(0.4));
            slide.setNode(slider);
            slide.setToX(0);
            slide.play();
            slider.setTranslateX(-176);
            
            slide.setOnFinished((ActionEvent e) -> {
                btnMenu2.setVisible(false);
                btnMenu1.setVisible(true);
            });
            
        });
        
        btnMenu1.setOnMouseClicked(event->{
            TranslateTransition slide = new TranslateTransition();
            slide.setDelay(Duration.seconds(0.2));
            slide.setNode(slider);
            slide.setToX(-176);
            slide.play();
            slider.setTranslateX(0);
            
            slide.setOnFinished((ActionEvent e) -> {
                btnMenu2.setVisible(true);
                btnMenu1.setVisible(false);
            });
            
        }); 

    }    
    
    void bloqueo(String puesto,String ruta) throws IOException{
        if(puesto.equals("Empleado")){
            btnPersonal.setDisable(true);
            btnProveedor.setDisable(true);
        }else{
            Parent fxml = FXMLLoader.load(getClass().getResource(ruta));      
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);            
        }
    }
    public void pantallaPersonal(javafx.event.ActionEvent actionEvent) throws IOException{
        bloqueo(etiquetaPuesto.getText(),"/vista/Empleado/GeneralEmpleadoPantalla.fxml");
    }
    

    public void pantallaPromocion(javafx.event.ActionEvent actionEvent) throws IOException{        

        Parent fxml = FXMLLoader.load(getClass().getResource("/vista/Promocion/GeneralPromocionPantalla.fxml"));      
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);             
    }
    
    public void pantallaHorario(javafx.event.ActionEvent actionEvent) throws IOException{        

        Parent fxml = FXMLLoader.load(getClass().getResource("/vista/Horario/GeneralHorarioPantalla.fxml"));      
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);             
    }
  
    public void pantallaProveedor(javafx.event.ActionEvent actionEvent) throws IOException{
        bloqueo(etiquetaPuesto.getText(),"/vista/Proveedor/GeneralProveedorPantalla.fxml");
    }    
    
    public void pantallaCliente(javafx.event.ActionEvent actionEvent) throws IOException{
        
        Parent fxml = FXMLLoader.load(getClass().getResource("/vista/Cliente/GeneralClientePantalla.fxml"));      
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);             
    }
    
    public void pantallaProducto(javafx.event.ActionEvent actionEvent) throws IOException{
        
        Parent fxml = FXMLLoader.load(getClass().getResource("/vista/Producto/GeneralProductoPantalla.fxml"));      
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);             
    }    
    
    
    public void mostrarVentana(String ruta,String tituloVentana){
        try{
            //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/PrincipalFXML.fxml"));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ruta));
            Parent ventana = (Parent) fxmlLoader.load();
            Stage stage = new Stage();            
            stage.setScene(new Scene(ventana));
            stage.setTitle(tituloVentana);
            stage.show();
            
        }catch(IOException e){
            String errorMessage = "El tiempo de espera se ha agotado o se perdío la conexión\n" +"con la Base Datos.";
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al mostrar la ventana");
            alert.setHeaderText(" ¡Por favor! intentelo nuevamente");
            alert.setContentText(errorMessage);
            alert.showAndWait();
        }
    }   
    
    
    @FXML
    void cerrarSesion(ActionEvent event) {
        Stage stage = (Stage) this.btnCerrarSesion.getScene().getWindow();
        stage.close();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/LoginPantalla.fxml"));
            Parent ventanaPrincipal = (Parent) fxmlLoader.load();
            //Stage
            stage = new Stage();
            stage.setScene(new Scene(ventanaPrincipal));
            stage.show();
            
        }catch(IOException e){
            String errorMessage = "El tiempo de espera se ha agotado o se perdío la conexión\n" +"con la Base Datos.";
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al mostrar la ventana principal");
            alert.setHeaderText(" ¡Por favor! intentelo nuevamente");
            alert.setContentText(errorMessage);
            alert.showAndWait();
        }      
    }   
    
   
}
