/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.Cliente;

/**
 *
 * @author theiv
 */
public class Cliente {
    String nombreCliente;
    String emailCliente;
    
    public Cliente() {
        this.nombreCliente = "";
        this.emailCliente = "";
    }
    //Constructor
    public Cliente (String nombreCliente, String emailCliente) {
        this.nombreCliente = nombreCliente;
        this.emailCliente = emailCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nombreCliente=" + nombreCliente + ", emailCliente=" + emailCliente + '}';
    }
    
}
