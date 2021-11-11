
package modelo.Producto;
/**
 *
 * @author jhair
 */
public class Producto {
    
    int claveProducto, stockProducto;
    float precioCProducto, precioVProducto;
    String marcaProducto, descripcionProducto, proveedorProducto;
    
    public Producto() {
        this.claveProducto = 0;
        this.precioCProducto = 0;
        this.precioVProducto = 0;
        this.stockProducto = 0;
        this.marcaProducto = "";
        this.descripcionProducto = "";
        this.proveedorProducto = "";
    }
    
    public Producto(int claveProducto, String marcaProducto, String descripcionProducto, String proveedorProducto, Float precioCProducto, Float precioVProducto, int stockProducto) {
        this.claveProducto = claveProducto;
        this.precioCProducto = precioCProducto;
        this.precioVProducto = precioVProducto;
        this.stockProducto = stockProducto;
        this.marcaProducto = marcaProducto;
        this.descripcionProducto = descripcionProducto;
        this.proveedorProducto = proveedorProducto;
    }

    public int getClaveProducto() {
        return claveProducto;
    }

    public void setClaveProducto(int claveProducto) {
        this.claveProducto = claveProducto;
    }

    public float getPrecioCProducto() {
        return precioCProducto;
    }

    public void setPrecioCProducto(float precioCProducto) {
        this.precioCProducto = precioCProducto;
    }

    public float getPrecioVProducto() {
        return precioVProducto;
    }

    public void setPrecioVProducto(float precioVProducto) {
        this.precioVProducto = precioVProducto;
    }

    public int getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(int stockProducto) {
        this.stockProducto = stockProducto;
    }

    public String getMarcaProducto() {
        return marcaProducto;
    }

    public void setMarcaProducto(String marcaProducto) {
        this.marcaProducto = marcaProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public String getProveedorProducto() {
        return proveedorProducto;
    }

    public void setProveedorProducto(String proveedorProducto) {
        this.proveedorProducto = proveedorProducto;
    }
    
    @Override
    public String toString() {
        return "Producto{" + "Clave: " + claveProducto + "Descripcion: " + descripcionProducto +
                "Marca: " + marcaProducto + "Precio de Compra: " + precioCProducto + "Precio de venta: "
                + precioVProducto + "Proveedor: " + proveedorProducto + "Stock: " + stockProducto;
    }
}
