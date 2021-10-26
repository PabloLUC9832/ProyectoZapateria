package modelo.Empleado;

import controlador.Empleado.GeneralEmpleadoPantallaController;
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
 * @author Pablo
 */
public class Empleado_DAO_Imp implements Empleado_DAO {

    GeneralEmpleadoPantallaController gen;
    
    @Override
    public boolean create(Empleado empleado) throws Exception {

        boolean created = false;
        Statement stm = null;
        //INSERT INTO empleado values(NULL,'as','zx','qq','za','dddd')
        String sql = "INSERT INTO empleado values(NULL,"+"'"+empleado.getNombre()+"','"
                +empleado.getUsuario()+"','"+empleado.getPass()+"','"
                    +empleado.getPuesto()
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
    public List<Empleado> readAll() throws Exception {

        Statement stm;
        ResultSet rs;
        String sql = "SELECT * FROM empleado;";
        
        List<Empleado> listaEmpleados = new ArrayList<Empleado>();
        ConexionDB cc = new ConexionDB();        
        
        try(Connection con = cc.getConnection();){
            stm = con.createStatement();
            rs = stm.executeQuery(sql);            
            while (rs.next()) {
              Empleado empl = new Empleado(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5)                                                    
                            );
              //System.out.println(rs.getInt(1));
              listaEmpleados.add(empl);
            }            
            stm.close();
            rs.close();
            con.close();
        }catch (SQLException e) {
            throw new Exception("Error en readAll SQLException " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Error en readAll Exception " + e.getMessage());
        }   
        
        return listaEmpleados;
    }
    //USADO PARA ININCAR SESIÓN
    @Override
    public Empleado read(String pass) throws Exception {
        
        Statement stm;
        ResultSet rs;
        String sql = "SELECT * FROM empleado WHERE pass = '"+pass+"'";
        Empleado empleado = new Empleado();
        ConexionDB cc= new ConexionDB();
        
        try(Connection con = cc.getConnection();){
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while(rs.next()){
                empleado.setNempleado(rs.getInt(1));
                empleado.setNombre(rs.getString(2));                
                empleado.setUsuario(rs.getString(3));                
                empleado.setPass(rs.getString(4));                
                empleado.setPuesto(rs.getString(5));                
            }
            stm.close();
            rs.close();
            con.close();
        }catch(SQLException e){
            throw new Exception("ERROR EN READ, SQLEXCEPTION "+e.getMessage());
        }catch(NullPointerException e){
            throw new Exception("ERROR EN READ, NULLPointerEXCEPTION "+e.getMessage());
        }catch(Exception e){
            throw new Exception("ERROR EN READ, EXCEPTION "+e.getMessage());
        }
        return empleado;
    }

    @Override
    public boolean update(Empleado empleado) throws Exception {
    
        Statement stm;

        boolean actualizar = false;

        String sql = "UPDATE empleado SET Nombre='"+empleado.getNombre()+"',"
        +"usuario='"+empleado.getUsuario()+"',"
        +"pass='"+empleado.getPass()+"',"
        +"puesto='"+empleado.getPuesto()+"'"
        +"WHERE Nempleado='"+empleado.getNempleado()+"'";
        
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
    public boolean delete(Empleado empleado) throws Exception {

        Statement stm;

        boolean eliminar = false;

        String sql = "DELETE FROM empleado WHERE Nempleado = " + empleado.getNempleado();
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
    //public Empleado search(String busqueda) throws Exception {
    public ObservableList<Empleado> search(String busqueda) throws Exception{        
        Statement stm;
        ResultSet rs;
        String sql = "SELECT * FROM empleado WHERE Nempleado LIKE '%"+busqueda+"%'"+
                    " UNION SELECT *FROM empleado WHERE Nombre LIKE '%"+busqueda+"%'"+
                    " UNION SELECT *FROM empleado WHERE usuario LIKE '%"+busqueda+"%'"+
                    " UNION SELECT *FROM empleado WHERE pass LIKE '%"+busqueda+"%'"+
                    " UNION SELECT *FROM empleado WHERE puesto LIKE '%"+busqueda+"%'"                                                                                                   
                    ;
        Empleado empleado = new Empleado();
        ConexionDB cc= new ConexionDB();
        
        ObservableList<Empleado> listaEmpleado = FXCollections.observableArrayList();        
        
        try(Connection con = cc.getConnection();){
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            
            while(rs.next()){
                /*empleado.setNempleado(rs.getInt(1));
                empleado.setNombre(rs.getString(2));
                empleado.setUsuario(rs.getString(3));
                empleado.setPass(rs.getString(4));                
                empleado.setPuesto(rs.getString(5));*/
              Empleado empl = new Empleado(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5)                                                    
                            );
                //empl
                listaEmpleado.add(empl);
            }
            //listaEmpleado.add(empleado);
            System.out.println(empleado);
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
        return listaEmpleado;
    }
    
}
