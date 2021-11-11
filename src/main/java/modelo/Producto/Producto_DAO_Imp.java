package modelo.Producto;

import controlador.Producto.GeneralProductoPantallaController;
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
 * @author jhair
 */
public class Producto_DAO_Imp implements Producto_DAO {
    
    GeneralProductoPantallaController gc;
    
    @Override
    public boolean create(Producto producto) throws Exception {
        
        boolean created = false;
        Statement stm = null;
        
        String sql = "INSERT INTO tablaProductos values (NULL,"+"'"+
                producto.getMarcaProducto()+"','"+producto.getDescripcionProducto()+"','"+producto.getProveedorProducto()
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
                            rs.getInt(7)
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
    
    @Override
    public ObservableList<Producto> search(String busqueda) throws Exception {
        
        Statement stm;
        ResultSet rs;
        String sql = "SELECT * FROM tablaproductos WHERE claveProducto LIKE '%"+busqueda+"%'"+
                    " UNION SELECT *FROM tablaproductos WHERE marcaProducto LIKE '%"+busqueda+"%'"+
                    " UNION SELECT *FROM tablaproductos WHERE descripcionProducto LIKE '%"+busqueda+"%'"+
                    " UNION SELECT *FROM tablaproductos WHERE proveedorProducto LIKE '%"+busqueda+"%'"+
                    " UNION SELECT *FROM tablaproductos WHERE precioCProducto LIKE '%"+busqueda+"%'"+ 
                    " UNION SELECT *FROM tablaproductos WHERE precioVProducto LIKE '%"+busqueda+"%'"+                                                                                                  
                    " UNION SELECT *FROM tablaproductos WHERE stockProducto LIKE '%"+busqueda+"%'"                                                                                                   

                    ;
        Producto producto = new Producto();
        ConexionDB cc= new ConexionDB();
        
        ObservableList<Producto> listaProducto = FXCollections.observableArrayList();        
        
        try(Connection con = cc.getConnection();){
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            
            while(rs.next()){
              Producto prov1 = new Producto(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getFloat(5),
                            rs.getFloat(6),
                            rs.getInt(7)
                            );
                listaProducto.add(prov1);
            }
            System.out.println(producto);
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
        return listaProducto;

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean update(Producto producto) throws Exception {
        
        Statement stm;

    boolean actualizar = false;
 
    String sql = "UPDATE tablaproductos SET marcaProducto = '"+producto.getMarcaProducto()+"',"
            +"proveedorProducto='"+producto.getProveedorProducto()+"',"
            +"precioCProducto='"+producto.getPrecioCProducto()+"',"
            +"precioVProducto='"+producto.getPrecioVProducto()+"'"
            +"WHERE claveProducto='"+producto.getClaveProducto()+"'";
    
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
    public boolean delete(Producto producto) throws Exception {

        Statement stm;

        boolean eliminar = false;

        String sql = "DELETE FROM tablaproductos WHERE ClaveProducto = " + producto.getClaveProducto();
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
    
    @Override
    public Producto read(int MarcaProducto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

