/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Horario;

import java.util.List;

/**
 *
 * @author theiv
 */
public interface Horario_DAO {
    public boolean create(Horario horario) throws Exception;
    public List <Horario> readAll() throws Exception;
}
