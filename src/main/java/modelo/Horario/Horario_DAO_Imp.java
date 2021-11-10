/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Horario;

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
public class Horario_DAO_Imp implements Horario_DAO {
    
    @Override
    public boolean create(Horario horario) throws Exception {
        
        boolean createdHorario = false;
        Statement stmHorario = null;
        String sql = "INSERT INTO horario values ('"+horario.getNombreHorario()+"','"+horario.getHoraHorario()+"','"+horario.getFechaHorario()+"','"+horario.getEmpleadoHorario()+"')";
        
        ConexionDB cc = new ConexionDB();
        
        try(Connection con = cc.getConnection();) {
            stmHorario = con.createStatement();
            stmHorario.execute(sql);
            createdHorario = true;
            stmHorario.close();
        }catch(SQLException e) {
            throw new Exception("Error al crear"+e.getMessage());
        }catch(Exception ex) {
            throw new Exception("Error al crear"+ex.getMessage());
        }
        return createdHorario;
    }
    
    @Override
    public List<Horario> readAll() throws Exception {
        
        Statement stm;
        ResultSet rs;
        String sql = "SELECT * FROM horario;";
        
        List<Horario> listaHorarios = new ArrayList<Horario>();
        ConexionDB cc = new ConexionDB();
        
        try(Connection con = cc.getConnection();) {
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            while (rs.next()) {
                Horario horari = new Horario (
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4)
                        );
                listaHorarios.add(horari);
            }
            stm.close();
            rs.close();
            con.close();
        }catch (SQLException e) {
            throw new Exception("Error en ReadAll SQLException " + e.getMessage());
        }catch (Exception e) {
            throw new Exception("Error en ReadAll Exception " + e.getMessage());
        }
        return listaHorarios;
    }
}
