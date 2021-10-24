package modelo.Proveedor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import modelo.ConexionDB;

/**
 *
 * @author horus
 */
public class Proveedor_DAO_Imp {
    public boolean create(Proveedor proveedor) throws Exception {

        boolean created = false;
        Statement stm = null;
        String sql = "INSERT INTO proveedor values('"+proveedor.getProveedorNombre()+ "','" +proveedor.getProveedorTelefono()+ "','"+proveedor.getProveedorDireccion()
                    + "','"+proveedor.getProveedorProducto()
                    +"')";
        ConexionDB cc = new ConexionDB();
        
        try(Connection con = cc.getConnection();){
            stm = con.createStatement();
            stm.execute(sql);
            created=true;
            stm.close();
        }catch(SQLException e){
            throw new Exception("\nERROR AL CREAR"+e.getMessage());
        }catch(Exception ex){
            throw new Exception("\nERROR  AL CREAR"+ex.getMessage());
        } 
        
        return created;
    }

    public List<Proveedor> readAll() throws Exception {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    public boolean update(Proveedor proveedor) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean delete(Proveedor proveedor) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
