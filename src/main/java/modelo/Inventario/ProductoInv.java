package modelo.Inventario;

/**
 *
 * @author ferna
 */
public class ProductoInv {
    
    int claveProducto;
    float precioCProducto, stockProducto;
    String marcaProducto, descripcionProducto, proveedorProducto, fecha;
    
    public ProductoInv() {
        this.claveProducto = 0;
        this.precioCProducto = 0;
        this.stockProducto = 0;
        this.marcaProducto = "";
        this.descripcionProducto = "";
        this.proveedorProducto = "";
        this.fecha = "";
    }
    
    public ProductoInv(int claveProducto, String marcaProducto, String descripcionProducto, String proveedorProducto, Float precioCProducto, Float stockProducto, String fecha) {
        this.claveProducto = claveProducto;
        this.precioCProducto = precioCProducto;
        this.stockProducto = stockProducto;
        this.marcaProducto = marcaProducto;
        this.descripcionProducto = descripcionProducto;
        this.proveedorProducto = proveedorProducto;
        this.fecha = fecha;
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

    public float getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(float stockProducto) {
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }  
    
    @Override
    public String toString() {
        return "Inventario" + " ID: " + claveProducto + " Descripcion: " + descripcionProducto +
                " Marca: " + marcaProducto + " Precio de Compra: " + precioCProducto + 
                " Proveedor: " + proveedorProducto + " Stock: " + stockProducto + " fecha: " + fecha;
    }
     public String formatoPDF(){
        return " ID: " + claveProducto + " Descripcion: " + descripcionProducto +
                " Marca: " + marcaProducto + " Precio de Compra: " + precioCProducto + 
                " Proveedor: " + proveedorProducto + " Stock: " + stockProducto + " fecha: " + fecha;
    }
}
