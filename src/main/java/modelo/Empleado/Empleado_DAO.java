package modelo.Empleado;

import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Pablo
 */
public interface Empleado_DAO {

    public boolean create(Empleado empleado) throws Exception;
    public List <Empleado> readAll() throws Exception;
    public Empleado read(String pass) throws Exception;
    public boolean update (Empleado empleado) throws Exception;
    public boolean delete (Empleado empleado) throws Exception;
    public ObservableList<Empleado> search(String busqueda) throws Exception;
}
