package modelo.Cliente;

import java.util.List;

/**
 *
 * @author Ivan Antonio Campos Garcia
 */
public interface Cliente_DAO {
    public boolean create(Cliente cliente) throws Exception;
    public List <Cliente> readAll() throws Exception;
}
