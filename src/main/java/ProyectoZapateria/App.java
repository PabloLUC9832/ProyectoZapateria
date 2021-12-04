
package ProyectoZapateria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.stage.StageStyle;
import modelo.ConexionDB;

/**
 * JavaFX App
 */
public class App extends Application {

    double x,y;

    @Override
    public void start(Stage primaryStage) throws IOException {
        
        //String ruta="/vista/Cliente/GeneralClientePantalla.fxml";
        //String ruta="/vista/LoginPantalla.fxml";

        //String ruta="/vista/Cliente/GeneralClientePantalla.fxml";
        //String ruta="/vista/Promocion/EditarPromocionPantalla.fxml";
        String ruta="/vista/MainPantalla.fxml";
        //String ruta="/vista/Promocion/CreatePromocionPantalla.fxml";

        Parent root = FXMLLoader.load(getClass().getResource(ruta));
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        
        root.setOnMouseDragged(event->{
            primaryStage.setX(event.getScreenX()-x);
            primaryStage.setY(event.getScreenY()-y);        
        });
        
        Scene scene = new Scene(root);                               
        primaryStage.setScene(scene);
        primaryStage.show();          
        
        
        
    }


    public static void main(String[] args) throws Exception {
        ConexionDB a = new ConexionDB();
        a.getConnection();
        launch();
    }
}


