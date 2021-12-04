package modelo.Proveedor;

import controlador.Proveedor.GeneralProveedorPantallaController;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.ConexionDB;

/**
 *
 * @author Horus Alejandro Hernandez
 */
public class Proveedor_DAO_Imp implements Proveedor_DAO {
    
    GeneralProveedorPantallaController gen;
    
    @Override
    public boolean create(Proveedor proveedor) throws Exception {

        boolean created = false;
        Statement stm = null;
        
        String sql = "INSERT INTO proveedor values(NULL,"+"'"+proveedor.getProveedorNombre()+ "','" +proveedor.getProveedorTelefono()+ "','"+proveedor.getProveedorDireccion()
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

    @Override
    public List<Proveedor> readAll() throws Exception {

        Statement stm;
        ResultSet rs;
        String sql = "SELECT * FROM proveedor;";
        
        List<Proveedor> listaProveedor = new ArrayList<Proveedor>();
        ConexionDB cc = new ConexionDB();        
        
        try(Connection con = cc.getConnection();){
            stm = con.createStatement();
            rs = stm.executeQuery(sql);            
            while (rs.next()) {
              Proveedor proveedor = new Proveedor(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5)
                            );
              listaProveedor.add(proveedor);
            }            
            stm.close();
            rs.close();
            con.close();
        }catch (SQLException e) {
            throw new Exception("Error en readAll SQLException " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Error en readAll Exception " + e.getMessage());
        }   
        
        return listaProveedor;
}

    @Override
    public ObservableList<Proveedor> search(String busqueda) throws Exception {
        
        Statement stm;
        ResultSet rs;
        String sql = "SELECT * FROM proveedor WHERE ProveedorId LIKE '%"+busqueda+"%'"+
                    " UNION SELECT *FROM proveedor WHERE ProveedorNombre LIKE '%"+busqueda+"%'"+
                    " UNION SELECT *FROM proveedor WHERE ProveedorTelefono LIKE '%"+busqueda+"%'"+
                    " UNION SELECT *FROM proveedor WHERE ProveedorDireccion LIKE '%"+busqueda+"%'"+
                    " UNION SELECT *FROM proveedor WHERE ProveedorProducto LIKE '%"+busqueda+"%'"                                                                                                   
                    ;
        Proveedor proveedor = new Proveedor();
        ConexionDB cc= new ConexionDB();
        
        ObservableList<Proveedor> listaProveedor = FXCollections.observableArrayList();        
        
        try(Connection con = cc.getConnection();){
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            
            while(rs.next()){
              Proveedor prov1 = new Proveedor(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5)
                            );
                listaProveedor.add(prov1);
            }
            System.out.println(proveedor);
            stm.close();
            rs.close();
            con.close();
        }catch(SQLException e){
            throw new Exception("ERROR EN SEARCH, SQLEXCEPTION "+e.getMessage());
        }catch(NullPointerException e){
            throw new Exception("ERROR EN SEARCH, NULLPointerEXCEPTION "+e.getMessage());
        }catch(Exception e){
            throw new Exception("ERROR EN SEARCH, EXCEPTION "+e.getMessage());
        }        
        return listaProveedor;

}
    
    @Override
    public boolean update(Proveedor proveedor) throws Exception {
        
        Statement stm;

        boolean actualizar = false;

        String sql = "UPDATE proveedor SET  ProveedorNombre = '"+proveedor.getProveedorNombre()+"',"
                +"ProveedorTelefono='"+proveedor.getProveedorTelefono()+"',"
                +"ProveedorDireccion='"+proveedor.getProveedorDireccion()+"',"
                +"ProveedorProducto='"+proveedor.getProveedorProducto()+"'"
                + "WHERE ProveedorId='"+proveedor.getProveedorId()+"'";

        ConexionDB cc = new ConexionDB();
        try (Connection connect = cc.getConnection();) {
          stm = connect.createStatement();
          actualizar = stm.execute(sql);
          connect.close();
        } catch (SQLException e) {
          throw new Exception("Error en update SQLException " + e.getMessage());
        } catch (Exception e) {
          throw new Exception("Error en update Exception " + e.getMessage());
        }
        return actualizar;

    }

    @Override
    public boolean delete(Proveedor proveedor) throws Exception {

        Statement stm;

        boolean eliminar = false;

        String sql = "DELETE FROM proveedor WHERE ProveedorId = " + proveedor.getProveedorId();
        ConexionDB cc = new ConexionDB();
        try (Connection connect = cc.getConnection();) {
          stm = connect.createStatement();
          eliminar = stm.execute(sql);
          connect.close();
        } catch (SQLException e) {
          throw new Exception("Error en delete SQLException " + e.getMessage());
        } catch (Exception e) {
          throw new Exception("Error en delete Exception " + e.getMessage());
        }
        return eliminar;        

    }

}
