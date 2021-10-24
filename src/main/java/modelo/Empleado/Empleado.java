package modelo.Empleado;

/**
 * @author ´Pablo
 */
public class Empleado {
    
    String nombre;
    int Nempleado;
    String usuario;
    String pass;
    String puesto;

    public Empleado() {
        this.nombre = "";
        this.Nempleado = 0;
        this.usuario = "";
        this.pass = "";
        this.puesto = "";        
    }
    //CONSTRUCTOR PARA REGISTRO
    public Empleado(int Nempleado,String nombre, String usuario, String pass, String puesto) {        
        this.Nempleado = Nempleado;
        this.nombre = nombre;
        this.usuario = usuario;
        this.pass = pass;
        this.puesto = puesto;
    }

    public Empleado(String usuario){
        this.usuario="";
        this.pass="";        
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNempleado() {
        return Nempleado;
    }

    public void setNempleado(int Nempleado) {
        this.Nempleado = Nempleado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    @Override
    public String toString() {
        return "Empleado{" + "nombre=" + nombre + ", Nempleado=" + Nempleado + ", usuario=" + usuario + ", pass=" + pass + ", puesto=" + puesto + '}';
    }


    
    
    
    
    
}
