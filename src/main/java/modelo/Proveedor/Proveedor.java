
package modelo.Proveedor;

/**
 *
 * @author horus
 */
public class Proveedor {
   
   String proveedorNombre;
   String proveedorTelefono;
   String proveedorDireccion;
   String proveedorProducto;
   
   public Proveedor () {
       
       this.proveedorNombre = " ";
       this.proveedorTelefono = " ";
       this.proveedorDireccion = " ";
       this.proveedorProducto = " ";
       
   }
   
   public Proveedor (String nombre, String telefono, String direccion, String producto) {
       
       this.proveedorNombre = nombre;
       this.proveedorTelefono = telefono;
       this.proveedorDireccion = direccion;
       this.proveedorProducto = producto;
       
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
        return "Proveedor{" + "proveedorNombre=" + proveedorNombre + ", proveedorTelefono=" + proveedorTelefono + ", proveedorDireccion=" + proveedorDireccion + ", proveedorProducto=" + proveedorProducto + '}';
    }
    
    
   
   
           
}
