/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Cliente;

import java.util.List;

/**
 *
 * @author theiv
 */
public interface Cliente_DAO {
    public boolean create(Cliente cliente) throws Exception;
    public List <Cliente> readAll() throws Exception;
}
