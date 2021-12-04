package modelo.Inventario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.ConexionDB;

/**
 *
 * @author JhAir García Ceballos
 */
public class ProductoInv_DAO_Imp implements ProductoInv_DAO {    

    @Override
    public List<ProductoInv> readAll() throws Exception {
     
        Statement stm;
        ResultSet rs;
        String sql = "SELECT * FROM tablaproductos";
        
        List<ProductoInv> listaProductos = new ArrayList<ProductoInv>();
        ConexionDB cc = new ConexionDB();
        
        try(Connection con = cc.getConnection();) {
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                ProductoInv producto = new ProductoInv(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getFloat(5),
                            rs.getFloat(6),
                            rs.getString(7)
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

