/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Promocion;

/**
 *
 * @author jair1
 */
public class Promocion {
    int idPromocion;
    String nombreProducto;
    String mensaje;
    String descuento;
    float precioAnterior;
    float precioNuevo;
    String fechaInicio;
    String fechaCierre;
    int diasDuracion;

    public Promocion() {
        int idPromocion = 0;
        this.nombreProducto = "";
        this.mensaje = "";
        this.descuento = "";
        this.precioAnterior = 0;
        this.precioNuevo = 0; 
        this.fechaInicio = "";
        this.fechaCierre = "";
        this.diasDuracion = 0;
    }
    //CONSTRUCTOR PARA REGISTRO
    public Promocion(int idPromocion, String nombreProducto, String mensaje, String descuento, float precioAnterior, float precioNuevo,
            String fechaInicio, String fechaCierre, int diasDuracion) {
        this.idPromocion = idPromocion;
        this.nombreProducto = nombreProducto;
        this.mensaje = mensaje;
        this.descuento = descuento;
        this.precioAnterior = precioAnterior;
        this.precioNuevo = precioNuevo; 
        this.fechaInicio = fechaInicio;
        this.fechaCierre = fechaCierre;
        this.diasDuracion = diasDuracion;
    }

    public Promocion(int idPromocion){
        this.idPromocion =0;
        this.nombreProducto="";
        this.mensaje="";
        this.descuento = "";
        this.precioAnterior = 0;
        this.precioNuevo = 0;
        this.fechaInicio = "";
        this.fechaCierre = "";
        this.diasDuracion = 0;
    }
    
    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.nombreProducto = nombreProducto;
    }
    
    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public float getPrecioAnterior() {
        return precioAnterior;
    }

    public void setPrecioAnterior(float precioAnterior) {
        this.precioAnterior = precioAnterior;
    }
    
    public float getPrecioNuevo() {
        return precioNuevo;
    }

    public void setPrecioNuevo(float precioNuevo) {
        this.precioNuevo = precioNuevo;
    }
    
    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    public String getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(String fechaCierre) {
        this.fechaCierre = fechaCierre;
    }
    
    public int getDiasDuracion() {
        return diasDuracion;
    }

    public void setDiasDuracion(int diasDuracion) {
        this.diasDuracion = diasDuracion;
    }
    
    @Override
    public String toString() {
        return "Promocion{" + "id promocion=" + idPromocion +"nombre del producto=" + nombreProducto + ", mensaje=" + mensaje + 
                ", descuento=" + descuento + ", precio anterior=" + precioAnterior + ", precio nuevo=" + precioNuevo +  
                ", fecha de inicio=" + fechaInicio +  ", fecha de cierre=" + fechaCierre + ", dias de duracion=" + diasDuracion +'}';
    }
     
}
