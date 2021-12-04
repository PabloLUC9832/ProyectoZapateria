package modelo.Proveedor;

import java.util.List;
import javafx.collections.ObservableList;
/**
 *
 * @author Horus Alejandro
 */
public interface Proveedor_DAO {
       
    public boolean create(Proveedor proveedor) throws Exception;
    public List <Proveedor> readAll() throws Exception;
    public boolean update (Proveedor proveedor) throws Exception;
    public boolean delete (Proveedor proveedor) throws Exception; 
    public ObservableList<Proveedor> search(String busqueda) throws Exception;
}
