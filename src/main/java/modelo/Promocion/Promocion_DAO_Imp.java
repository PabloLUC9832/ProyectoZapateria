/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Promocion;

import controlador.Promocion.GeneralPromocionPantallaController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.ConexionDB;
import modelo.Empleado.Empleado;

/**
 *
 * @author jair1
 */
public class Promocion_DAO_Imp implements Promocion_DAO {
    
    GeneralPromocionPantallaController gen;
    
    @Override
    public boolean create(Promocion promocion) throws Exception {
        boolean created = false;
        Statement stm = null;
        
        String sql = "INSERT INTO promocion values(NULL,"+"'"+promocion.getNombreProducto()+"','"
                +promocion.getMensaje()+"','"+promocion.getDescuento()+"','"
                    +promocion.getPrecioAnterior()+"','"+promocion.getPrecioNuevo()
                    +"')";
 
        ConexionDB cc = new ConexionDB();
        
        try(Connection con = cc.getConnection();){
            stm = con.createStatement();
            stm.execute(sql);
            created=true;
            stm.close();
        }catch(SQLException e){
            throw new Exception("\nSQL ERROR AL CREAR "+e.getMessage());
        }catch(Exception ex){
            throw new Exception("\nEX ERROR  AL CREAR "+ex.getMessage());
        } 
        
        return created;
    }
    
    @Override
    public List<Promocion> readAll() throws Exception {

        Statement stm;
        ResultSet rs;
        String sql = "SELECT * FROM promocion;";
        
        List<Promocion> listaPromocion = new ArrayList<Promocion>();
        ConexionDB cc = new ConexionDB();        
        
        try(Connection con = cc.getConnection();){
            stm = con.createStatement();
            rs = stm.executeQuery(sql);            
            while (rs.next()) {
              Promocion promocion = new Promocion(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getFloat(5),
                            rs.getFloat(6)
                            );
              //System.out.println(rs.getInt(1));
              listaPromocion.add(promocion);
            }            
            stm.close();
            rs.close();
            con.close();
        }catch (SQLException e) {
            throw new Exception("Error en readAll SQLException " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Error en readAll Exception " + e.getMessage());
        }   
        
        return listaPromocion;
    }
    
    public ObservableList<Promocion> search(String busqueda) throws Exception{        
        Statement stm;
        ResultSet rs;
        String sql = "SELECT * FROM promocion WHERE idPromocion LIKE '%"+busqueda+"%'"+
                    " UNION SELECT *FROM promocion WHERE nombreProducto LIKE '%"+busqueda+"%'"+
                    " UNION SELECT *FROM promocion WHERE mensaje LIKE '%"+busqueda+"%'"+
                    " UNION SELECT *FROM promocion WHERE descuento LIKE '%"+busqueda+"%'"+
                    " UNION SELECT *FROM promocion WHERE precioAnterior LIKE '%"+busqueda+"%'"+
                    " UNION SELECT *FROM promocion WHERE precioNuevo LIKE '%"+busqueda+"%'"                                                                                                   
                    ;
        Promocion promocion = new Promocion();
        ConexionDB cc= new ConexionDB();
        
        ObservableList<Promocion> listaPromocion = FXCollections.observableArrayList();        
        
        try(Connection con = cc.getConnection();){
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            
            while(rs.next()){
                /*empleado.setNempleado(rs.getInt(1));
                empleado.setNombre(rs.getString(2));
                empleado.setUsuario(rs.getString(3));
                empleado.setPass(rs.getString(4));                
                empleado.setPuesto(rs.getString(5));*/
              Promocion prom1 = new Promocion(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getFloat(5),
                            rs.getFloat(6)                                                   
                            );
                //empl
                listaPromocion.add(prom1);
            }
            //listaEmpleado.add(empleado);
            System.out.println(promocion);
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
        return listaPromocion;
    }

    @Override
  public boolean update(Promocion promocion) throws Exception {
    Statement stm;

    boolean actualizar = false;

    /*AQUÍ VALE MADRES*/
    /*String sql = "update promocion set nombreProducto='" + promocion.getNombreProducto() + "' mensaje='" + promocion.getMensaje()
            + "' descuento='" + promocion.getDescuento() + "' precioAnterior='" + promocion.getPrecioAnterior() + "' precioNuevo='" + promocion.getPrecioNuevo()
            + "' where idPromocion =" + promocion.getIdPromocion()+"'";*/
    
    String sql = "UPDATE promocion SET  nombreProducto = '"+promocion.getNombreProducto()+"',"
            +"mensaje='"+promocion.getMensaje()+"',"
            +"descuento='"+promocion.getDescuento()+"',"
            +"precioAnterior='"+promocion.getPrecioAnterior()+"',"
            +"precioNuevo='"+promocion.getPrecioNuevo()+"'"
            + "WHERE idPromocion='"+promocion.getIdPromocion()+"'";
    
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
    public boolean delete(Promocion promocion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Promocion read(int idPromocion) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
