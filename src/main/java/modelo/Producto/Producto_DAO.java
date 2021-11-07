
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.Producto;

import java.util.List;

/**
 *
 * @author jhair
 */
public interface Producto_DAO {
    public boolean create(Producto producto) throws Exception;
    public List <Producto> readAll() throws Exception;   
}
