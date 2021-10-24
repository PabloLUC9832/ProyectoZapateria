/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Promocion;

/**
 *
 * @author jair1
 */
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author jair1
 */
public interface Promocion_DAO {

    public boolean create(Promocion promocion) throws Exception;
    public List <Promocion> readAll() throws Exception;
    public Promocion read(int idPromocion) throws Exception;
    public boolean update (Promocion promocion) throws Exception;
    public boolean delete (Promocion promocion) throws Exception;
    public ObservableList<Promocion> search(String busqueda) throws Exception;
    
}
