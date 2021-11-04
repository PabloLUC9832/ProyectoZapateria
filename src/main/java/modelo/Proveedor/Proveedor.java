
package modelo.Proveedor;

/**
 *
 * @author horus
 */
public class Proveedor {
   
   int ProveedorId;
   String proveedorNombre;
   String proveedorTelefono;
   String proveedorDireccion;
   String proveedorProducto;
   
   public Proveedor () {
       
       this.ProveedorId = 0;
       this.proveedorNombre = " ";
       this.proveedorTelefono = " ";
       this.proveedorDireccion = " ";
       this.proveedorProducto = " ";
       
   }
   
   public Proveedor (int id, String nombre, String telefono, String direccion, String producto) {
       
       this.ProveedorId = id;
       this.proveedorNombre = nombre;
       this.proveedorTelefono = telefono;
       this.proveedorDireccion = direccion;
       this.proveedorProducto = producto;
       
   }

   public int getProveedorId() {
        return ProveedorId;
    }

    public void setProveedorId(int proveedorId) {
        this.ProveedorId = proveedorId;
    }
   
    public String getProveedorNombre() {
        return proveedorNombre;
    }

    public void setProveedorNombre(String proveedorNombre) {
        this.proveedorNombre = proveedorNombre;
    }

    public String getProveedorTelefono() {
        return proveedorTelefono;
    }

    public void setProveedorTelefono(String proveedorTelefono) {
        this.proveedorTelefono = proveedorTelefono;
    }

    public String getProveedorDireccion() {
        return proveedorDireccion;
    }

    public void setProveedorDireccion(String proveedorDireccion) {
        this.proveedorDireccion = proveedorDireccion;
    }

    public String getProveedorProducto() {
        return proveedorProducto;
    }

    public void setProveedorProducto(String proveedorProducto) {
        this.proveedorProducto = proveedorProducto;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "ProveedorId=" + ProveedorId + ", proveedorNombre=" + proveedorNombre + ", proveedorTelefono=" + proveedorTelefono + ", proveedorDireccion=" + proveedorDireccion + ", proveedorProducto=" + proveedorProducto + '}';
    }
        
}
