/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Horario;

/**
 *
 * @author theiv
 */
public class Horario {
    String nombreHorario;
    String horaHorario;
    String fechaHorario;
    String empleadoHorario;
    
        public Horario() {
            this.nombreHorario = "";
            this.horaHorario = "";
            this.fechaHorario = "";
            this.empleadoHorario = "";
}
//Constructor
public Horario (String nombreHorario, String horaHorario, String fechaHorario, String empleadoHorario) {
    this.nombreHorario = nombreHorario;
    this.horaHorario = horaHorario;
    this.fechaHorario = fechaHorario;
    this.empleadoHorario = empleadoHorario;
}
public String getNombreHorario() {
    return nombreHorario;
}
public void setNombreHorario(String nombreHorario) {
    this.nombreHorario = nombreHorario;
}
public String getHoraHorario() {
    return horaHorario;
}
public void setHoraHorario(String horaHorario) {
    this.horaHorario = horaHorario;
}
public String getFechaHorario() {
    return fechaHorario;
}
public void setFechaHorario(String fechaHorario) {
    this.fechaHorario = fechaHorario;
}
public String getEmpleadoHorario() {
    return empleadoHorario;
}
public void setEmpleadoHorario(String empleadoHorario) {
    this.empleadoHorario = empleadoHorario;
}

    @Override
    public String toString() {
        return "Horario{" + "nombreHorario=" + nombreHorario + ", horaHorario=" + horaHorario + ", fechaHorario=" + fechaHorario + ", empleadoHorario=" + empleadoHorario + '}';
    }

}
