/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.Producto;

import controlador.Producto.GeneralProductoPantallaController;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.ConexionDB;

/**
 *
 * @author jhair
 */
public class Producto_DAO_Imp implements Producto_DAO {
    
    //GeneralProductoPantallaController gc;
    
    @Override
    public boolean create(Producto producto) throws Exception {
        
        boolean created = false;
        Statement stm = null;
        
        String sql = "INSERT INTO tablaProductos values (NULL,"+"'"+
                producto.getMarcaProducto()+"','"+producto.getProveedorProducto()+"','"+producto.getDescripcionProducto()
                +"','"+producto.getPrecioCProducto()+"','"+producto.getPrecioVProducto()+"','"
                +producto.getStockProducto()+"')";
        
        ConexionDB cc = new ConexionDB();
        
        try(Connection con = cc.getConnection();) {
            stm = con.createStatement();
            stm.execute(sql);
            created=true;
            stm.close();
        }catch(SQLException e) {
            throw new Exception("\nSQL ERROR AL CREAR "+e.getMessage());
        }catch(Exception ex) {
            throw new Exception("\nEx ERROR AL CREAR "+ex.getMessage());
        }
        return created;
    }

    @Override
    public List<Producto> readAll() throws Exception {
     
        Statement stm;
        ResultSet rs;
        String sql = "SELECT * FROM tablaproductos";
        
        List<Producto> listaProductos = new ArrayList<Producto>();
        ConexionDB cc = new ConexionDB();
        
        try(Connection con = cc.getConnection();) {
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Producto producto = new Producto(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getFloat(5),
                            rs.getFloat(6),
                            rs.getFloat(7)
                            );
                listaProductos.add(producto);
            }
            stm.close();
            rs.close();
            con.close();
        }catch (SQLException e) {
            throw new Exception("Error en readAll SQLException "+ e.getMessage());
        }catch (Exception e) {
            throw new Exception("Error en readAll Exception "+ e.getMessage());
        }
        return listaProductos;
    }
    
}

