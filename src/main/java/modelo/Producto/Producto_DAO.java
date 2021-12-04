package modelo.Producto;

import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Jhair Garcia Ceballos 
 */
public interface Producto_DAO {
    public boolean create(Producto producto) throws Exception;
    public List <Producto> readAll() throws Exception; 
    public boolean update (Producto producto) throws Exception;
    public boolean delete (Producto producto) throws Exception; 
    public ObservableList<Producto> search(String busqueda) throws Exception;
}
