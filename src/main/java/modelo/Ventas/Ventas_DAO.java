package modelo.Ventas;

import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Pablo
 */
public interface Ventas_DAO {

    //public boolean create(Ventas empleado) throws Exception;
    public List <Ventas> readAll() throws Exception;
    //public Ventas read(String pass) throws Exception;
    //public boolean update (Ventas empleado) throws Exception;
    //public boolean delete (Ventas empleado) throws Exception;
    
    //public void search(String busqueda) throws Exception;
    //public Empleado search(String busqueda) throws Exception;
    public ObservableList<Ventas> search(String busqueda) throws Exception;
}
