package controlador.Horario;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.DateTimeStringConverter;
import modelo.AlertaFXML;
import modelo.Horario.Horario;
import modelo.Horario.Horario_DAO_Imp;

/**
 * FXML Controller class
 *
 * @author theiv
 */
public class CreateHorarioPantallaController implements Initializable {

    //@FXML
    //private TextField txtNombre;

    @FXML
    private TextField txtHora;

    @FXML
    private JFXButton btnRegistrar;

    @FXML
    private TextField txtFecha;

    @FXML
    private TextField txtEmpleado;
    
    @FXML
    private ComboBox<?> txtAccion;

    private Horario_DAO_Imp horario_DAO;
    private Stage stageDialogoEdicion;
    private Horario horario;
    
    public String accion;
    public String fechaHora;
    
    GeneralHorarioPantallaController gc;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        horario_DAO = new Horario_DAO_Imp();
        ObservableList list = FXCollections.observableArrayList("Apertura", "Cierre");
        txtAccion.setItems(list);
        String fechaYHora = obtenerFechaYHoraActual();
        txtHora.setText(fechaYHora);
        String fechaYHoraa = obtenerFechaSolamente();
        txtFecha.setText(fechaYHoraa);
        txtFecha.setDisable(true);
        txtHora.setDisable(true);
        // TODO
    }

    @FXML
    void registrarHora(ActionEvent event) {
        Horario horario = new Horario();
        try{
            if(campoTextoValidoNombre() && campoNuloHora() && campoTextoValidoHora() ) {
                //String nombreHorario = this.txtNombre.getText();
                //String nombreHorario = this.txtAccion.toString();
                String horaHorario = this.txtHora.getText();
                String fechaHorario = this.txtFecha.getText();
                String empleadoHorario = this.txtEmpleado.getText();
                accion = this.txtAccion.getValue().toString();
                horario = new Horario(accion, horaHorario, fechaHorario, empleadoHorario);
                
                if(this.horario_DAO.create(horario)==true) {
                    Stage stage = (Stage) this.btnRegistrar.getScene().getWindow();
                    Alert alert = new Alert(Alert.AlertType.NONE, "Se ha añadido con exito", ButtonType.OK);
                    alert.setTitle("Operación exitosa");
                    alert.showAndWait();
                    cerrarVentana();
                }else{
                    Stage stage = (Stage) this.btnRegistrar.getScene().getWindow();
                    String errorMessage = "El tiempo de espera se ha agotado o se perdío la conexión\n" +"con la Base Datos.";
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error, No hay conexión con la Base de Datos");
                    alert.setHeaderText(" ¡Por favor! intentelo nuevamente");
                    alert.setContentText(errorMessage);
                    alert.initOwner(stageDialogoEdicion);
                    alert.showAndWait();
                }
            }
        }catch(Exception ex) {
            Stage stage = (Stage) this.btnRegistrar.getScene().getWindow();
            String errorMessage = "El tiempo de espera se ha agotado o se perdío la conexión\n" +"con la Base Datos.";
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error, No hay conexión con la Base de Datos");
                    alert.setHeaderText(" ¡Por favor! intentelo nuevamente");
                    alert.setContentText(errorMessage);
                    alert.initOwner(stageDialogoEdicion);
                    alert.showAndWait();
        }
    }
    private boolean campoTextoValidoNombre() {
        
        String errorMessage = "";
        
        if(this.txtEmpleado.getText() == null || this.txtEmpleado.getText().length() == 0) {
            errorMessage += "Verifica el campo! \n";
        }
        if(errorMessage.length() == 0) {
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("El campo Nombre está vacío");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
            return false;
        }
    }
    
    
    private boolean campoTextoHora() {
        //String fechaYHora = obtenerFechaYHoraActual();
        //txtHora.setText(fechaYHora);
        //System.out.println("Hora: " + fechaYHora);
        String errorMessage = "";
        //txtHora.setText(fechaYHora);
        boolean simbolo = false;
        String hora = this.txtHora.getText();
        for(int i = 0; i<hora.length();i++) {
            if(hora.charAt(i) == ':'){
                simbolo = true;
                break;
            }
        }
        return simbolo;
    }
    
    //private boolean campoTextoHoraAuto() {
    //    String fechaYHora = obtenerFechaYHoraActual();
    //    txtHora.setText(fechaYHora);
    //    return false;
        
    //}
    
    private boolean campoNuloHora() {
        
        String errorMessage = "";
        if(this.txtHora.getText() == null || this.txtHora.getText().length() == 0) {
            errorMessage += "Verifica el campo! \n";
        }
        
        if(errorMessage.length() == 0){
            return true;
        }else{
            Alert alert = new Alert (AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("El campo Hora está vacío");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
            return false;
        }
    }
    
    private boolean campoTextoValidoHora() {
        String errorMessage = "";
        if(this.campoTextoHora() == false) {
            errorMessage += "Veririca el campo! \n";
        }
        
        if(errorMessage.length() == 0) {
            return true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error, campo no válido");
            alert.setHeaderText("Ingresa una hora válida");
            alert.setContentText(errorMessage);
            alert.initOwner(stageDialogoEdicion);
            alert.showAndWait();
            return false;
        }
    }
    
    //Original    public static String obtenerFechaYHoraActual() {
	//	String formato = "yyyy-MM-dd HH:mm:ss";
	//	DateTimeFormatter formateador = DateTimeFormatter.ofPattern(formato);
	//	LocalDateTime ahora = LocalDateTime.now();
	//	return formateador.format(ahora);
	//}
    
    public static String obtenerFechaYHoraActual() {
		String formato = "HH:mm:ss";
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern(formato);
		LocalDateTime ahora = LocalDateTime.now();
		return formateador.format(ahora);
	}
    
    public String obtenerFechaSolamente() {
        String formato = "yyyy-MM-dd";
        DateTimeFormatter formater = DateTimeFormatter.ofPattern(formato);
        LocalDateTime ahoraa = LocalDateTime.now();
        return formater.format(ahoraa);
        
    }
    
    /*public LocalTime hora() {
        LocalTime horaActual = LocalTime.now();
        //System.out.println("Hora: " + horaActual);
        return horaActual;
    }*/
    
   /* public String fecha() {
        Date fecha = new Date();
        DateTimeStringConverter formatoFecha = new DateTimeStringConverter("dd/MM/YYYY");
        return formatoFecha.toString(fecha);
    }*/
    public void cerrarVentana() {
        Stage stage = (Stage) this.btnRegistrar.getScene().getWindow();
        stage.close();
    }
}