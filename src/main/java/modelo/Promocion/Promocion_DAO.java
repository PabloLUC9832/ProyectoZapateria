package modelo.Promocion;

/**
 *
 * @author Jair Vasquez Rendón
 */
import java.util.List;
import javafx.collections.ObservableList;

public interface Promocion_DAO {

    public boolean create(Promocion promocion) throws Exception;
    public List <Promocion> readAll() throws Exception;
    public boolean update (Promocion promocion) throws Exception;
    public boolean delete (Promocion promocion) throws Exception;
    public ObservableList<Promocion> search(String busqueda) throws Exception;
    
}
