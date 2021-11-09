/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.Horario;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.Horario.Horario;
import modelo.Horario.Horario_DAO_Imp;

/**
 * FXML Controller class
 *
 * @author theiv
 */
public class GeneralHorarioPantallaController implements Initializable {

    @FXML
    private TableView<Horario> tablaHorario;

    @FXML
    private TableColumn<Horario, String> columnaNombre;

    @FXML
    private TableColumn<Horario, String> columnaHora;

    @FXML
    private TableColumn<Horario, String> columnaFecha;

    @FXML
    private TableColumn<Horario, String> columnaEmpleado;

    @FXML
    private Label titulo;
    
    private Horario_DAO_Imp horario_DAO;
    private ObservableList<Horario> listaHorario;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        horario_DAO = new Horario_DAO_Imp();
        listaHorario = FXCollections.observableArrayList();
        this.colocarHorariosTabla();
        // TODO
    }
    public void obtenerHorarios() {
        List listaConsulta = null;
        
        try {
            listaConsulta = horario_DAO.readAll();
        } catch(Exception ex) {
            System.out.println("Error Read All " + ex);
        }
        Iterator it = listaConsulta.iterator();
        listaHorario.clear();
        while (it.hasNext()) {
            listaHorario.add((Horario) it.next());
            
        }
        System.out.println(listaConsulta);
    }
    public void colocarHorariosTabla() {
        this.obtenerHorarios();
        this.columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreHorario"));
        this.columnaHora.setCellValueFactory(new PropertyValueFactory<>("horaHorario"));
        this.columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fechaHorario"));
        this.columnaEmpleado.setCellValueFactory(new PropertyValueFactory<>("empleadoHorario"));
        this.tablaHorario.setItems(this.listaHorario);
    }
    
    @FXML
    void agregarHorario(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/Horario/CreateHorarioPantalla.fxml"));
            Parent ventana = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(ventana));
            stage.setTitle("Añadir Horario");
            stage.show();
        }catch(IOException e) {
            System.out.println("ERROR AL MOSTRAR LA VENTANA: "+e);
        }
    }
}
