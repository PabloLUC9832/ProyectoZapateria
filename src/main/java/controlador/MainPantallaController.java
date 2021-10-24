package controlador;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainPantallaController implements Initializable {

    @FXML
    private JFXButton btn1;

    @FXML
    private JFXButton btn2;

    @FXML
    private JFXButton btn3;

    @FXML
    private JFXButton btn4;

    @FXML
    private JFXButton btn5;

    @FXML
    private JFXButton btn6;

    @FXML
    private JFXButton btn7;

    @FXML
    private JFXButton btn8;
    
    @FXML
    private ImageView img1;    
    
    @FXML
    private ImageView img2;       
    
    @FXML
    private ImageView img3;       
    
    @FXML
    private ImageView img4;       
    
    @FXML
    private ImageView img5;       
    
    @FXML
    private ImageView img6;       
    
    @FXML
    private ImageView img7;       
    
    @FXML
    private ImageView img8;               
    
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
    private JFXButton btnPersonal;

    @FXML
    private JFXButton btnProveedor;

    @FXML
    private JFXButton btnCliente;

    @FXML
    private JFXButton btnPromocion;

    @FXML
    private JFXButton btnCerrarSesion;    
    //LoginPantallaController lgc;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {               

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
        /*
        try{
            Parent fxml = FXMLLoader.load(getClass().getResource("/vista/MenuVentas/MenuVentasPantalla.fxml"));
            contentArea.getChildren().removeAll();
            contentArea.getChildren().setAll(fxml);           
        }catch(IOException ex){
            Logger.getLogger(ModuleLayer.Controller.class.getName()).log(Level.SEVERE,null,ex);            
        }
        */
        //DESABILITAR BOTONES PARA EL EMPLEADO
        /*
        lgc = new LoginPantallaController();
        String puesto = lgc.puesto;
        
        if (puesto.equals("Empleado")) {
            btnProveedores.setDisable(false);
            btnPersonal.setDisable(false);
                    
        }else if(puesto.equals("Gerente")){
            btnProveedores.setDisable(true);
            btnPersonal.setDisable(true);            
        }
        */
    }    
    /*
    public void pantalla1(javafx.event.ActionEvent actionEvent) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("/vista/venta/VentaPantalla.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);     
        
    }  */  

    public void pantallaPersonal(javafx.event.ActionEvent actionEvent) throws IOException{
        
        Parent fxml = FXMLLoader.load(getClass().getResource("/vista/Empleado/GeneralEmpleadoPantalla.fxml"));      
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);     
        btn1.setText("Añadir empleado");
        btn1.setAccessibleText("Añadir empleado");
        Image image = new Image("/imgs/addEmpleado_64px.png");
        img1.setImage(image);  
        
        btn1.setOnMouseClicked((MouseEvent mouseEvent) -> {
            mostrarVentana("/vista/Empleado/CreateEmpleadoPantalla.fxml","Registro de Personal");
        });
        
        
    }
    
    public void pantallaPromocion(javafx.event.ActionEvent actionEvent) throws IOException{
        
        Parent fxml = FXMLLoader.load(getClass().getResource("/vista/Promocion/GeneralPromocionPantalla.fxml"));      
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);     
        btn1.setText("Añadir Promocion");
        btn1.setAccessibleText("Añadir Promocion");
        Image image = new Image("/imgs/addPromocion_64px.png");
        img1.setImage(image);  
        
        btn1.setOnMouseClicked((MouseEvent mouseEvent) -> {
            mostrarVentana("/vista/Promocion/CreatePromocionPantalla.fxml","Registro de  Promociones");
        });
        
    }
  
    public void pantallaProveedor(javafx.event.ActionEvent actionEvent) throws IOException{
        
        /*Parent fxml = FXMLLoader.load(getClass().getResource("/vista/Proveedor/GeneralPromocionPantalla.fxml"));      
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);     */
        btn1.setText("Añadir Proveedor");
        btn1.setAccessibleText("Añadir Proveedor");
        Image image = new Image("/imgs/addEmpleado_64px.png");
        img1.setImage(image); 
        
        btn1.setOnMouseClicked((MouseEvent mouseEvent) -> {
            mostrarVentana("/vista/Proveedor/CreateProveedorPantalla.fxml","Registro de Proveedor");
        });
        
    }    
    
    public void pantallaCliente(javafx.event.ActionEvent actionEvent) throws IOException{
        
        Parent fxml = FXMLLoader.load(getClass().getResource("/vista/Cliente/GeneralClientePantalla.fxml"));      
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);     
        btn1.setText("Añadir Cliente");
        btn1.setAccessibleText("Añadir Cliente");
        Image image = new Image("/imgs/addEmpleado_64px.png");
        img1.setImage(image);
        
        btn1.setOnMouseClicked((MouseEvent mouseEvent) -> {
            mostrarVentana("/vista/Cliente/CreateClientePantalla.fxml","Registro de Proveedor");
        });
        
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
            System.out.println("ERROR AL MOSTRAR LA VENTANA: "+e);
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
            System.out.println("Error al abrir ventana principal"+e);
        }      
    }    
    
}
