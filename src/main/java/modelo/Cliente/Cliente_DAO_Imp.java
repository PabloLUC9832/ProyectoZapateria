package modelo.Cliente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.ConexionDB;

/**
 *
 * @author theiv
 */
public class Cliente_DAO_Imp implements Cliente_DAO {
    
    @Override
    public boolean create(Cliente cliente) throws Exception {
        
        boolean createdCliente = false;
        Statement stmCliente = null;
        String sql = "INSERT INTO cliente values('"+cliente.getNombreCliente()+"','"+cliente.getEmailCliente()+"')";
        
        ConexionDB cc = new ConexionDB();
        
        try(Connection con = cc.getConnection();) {
            stmCliente = con.createStatement();
            stmCliente.execute(sql);
            createdCliente = true;
            stmCliente.close();
        }catch(SQLException e) {
            throw new Exception("Error al crear"+e.getMessage());
        }catch(Exception ex) {
            throw new Exception("Error al crear"+ex.getMessage());
        }
        return createdCliente;
    }
    
    @Override
    public List<Cliente> readAll() throws Exception {
        
        Statement stm;
        ResultSet rs;
        String sql = "SELECT * FROM cliente;";
        
        List<Cliente> listaClientes = new ArrayList<Cliente>();
        ConexionDB cc = new ConexionDB();
        
        try(Connection con = cc.getConnection();) {
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Cliente cli1 = new Cliente (
                    rs.getString(1),
                    rs.getString(2)
                            );
                listaClientes.add(cli1);
            }
            stm.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            throw new Exception("Error en ReadAll SQLException " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Error en ReadAll Exception " + e.getMessage());
        }
        return listaClientes;
    }
    
   
}
