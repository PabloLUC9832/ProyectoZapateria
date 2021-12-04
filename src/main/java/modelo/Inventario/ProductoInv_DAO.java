package modelo.Inventario;

import java.util.List;

/**
 *
 * @author Jhair García Ceballos && luis fernando morales teutli
 */
public interface ProductoInv_DAO {
    public List <ProductoInv> readAll() throws Exception;
}
