
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.Inventario;

import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author jhair
 */
public interface ProductoInv_DAO {
    //public boolean create(ProductoInv ProductoInv) throws Exception;
    public List <ProductoInv> readAll() throws Exception;
    //public Ventas read(String pass) throws Exception;
    public boolean update (ProductoInv ProductoInv) throws Exception;
    public boolean delete (ProductoInv ProductoInv) throws Exception;
    
    //public void search(String busqueda) throws Exception;
    //public Empleado search(String busqueda) throws Exception;
    public ObservableList<ProductoInv> search(String busqueda) throws Exception;  
}
