package modelo.Horario;

import java.util.List;

/**
 *
 * @author Ivan Antonio Campos García
 */
public interface Horario_DAO {
    public boolean create(Horario horario) throws Exception;
    public List <Horario> readAll() throws Exception;
}
