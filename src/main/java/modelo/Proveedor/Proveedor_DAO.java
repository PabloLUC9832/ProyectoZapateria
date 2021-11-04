/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Proveedor;

import java.util.List;
import javafx.collections.ObservableList;
/**
 *
 * @author horus
 */
public interface Proveedor_DAO {
       
    public boolean create(Proveedor proveedor) throws Exception;
    public List <Proveedor> readAll() throws Exception;
    public Proveedor read(int nombreProveedor) throws Exception;
    public boolean update (Proveedor proveedor) throws Exception;
    public boolean delete (Proveedor proveedor) throws Exception; 
    public ObservableList<Proveedor> search(String busqueda) throws Exception;
}
