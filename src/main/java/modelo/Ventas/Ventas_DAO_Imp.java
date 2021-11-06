package modelo.Ventas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import modelo.ConexionDB;

/**
 *
 * @author ferna
 */
public class Ventas_DAO_Imp implements Ventas_DAO{

    @Override
    public List<Ventas> readAll() throws Exception {
        Statement stm;
        ResultSet rs;
        String sql = "SELECT * FROM ventas;";
        
        List<Ventas> listaVentas = new ArrayList<Ventas>();
        ConexionDB cc = new ConexionDB();        
        
        try(Connection con = cc.getConnection();){
            stm = con.createStatement();
            rs = stm.executeQuery(sql);            
            while (rs.next()) {
                Ventas empl = new Ventas(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getInt(3),
                            rs.getInt(4),
                            rs.getInt(5),
                            rs.getString(6)
                            );
              //System.out.println(rs.getInt(1));
              listaVentas.add(empl);
            }            
            stm.close();
            rs.close();
            con.close();
        }catch (SQLException e) {
            throw new Exception("Error en readAll SQLException " + e.getMessage());
        } catch (Exception e) {
            throw new Exception("Error en readAll Exception " + e.getMessage());
        }   
        
        return listaVentas;
    }

    @Override
    public ObservableList<Ventas> search(String busqueda) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

