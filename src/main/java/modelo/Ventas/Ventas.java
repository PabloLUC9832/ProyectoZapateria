package modelo.Ventas;

//import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Set;


/**
 *
 * @author ferna
 */
public class Ventas {
    int IdVenta;
    String NameV;
    int PriceV;
    int QuantityV;
    int Total;
    String Date;
    //Date Date;
    //LocalDate LDate;
    //LocalDate Datenow;
    
    public Ventas(){
        this.IdVenta = 0;
        this.NameV = "";
        this.PriceV = 0;
        this.QuantityV = 0;
        this.Total = 0;
        this.Date = "";
        
        //Date Date = Date();
        //LocalDateTime DateV = LocalDateTime.now();             
        //LocalDate LDate = LocalDate.now();
    }
    
    //constructor para registro de venta   
    public Ventas(int IdVenta, String NameV, int PriceV, int  QuantityV, int  Total, String Date/*,Date Date, LocalDate LDate*/) {        
        this.IdVenta = IdVenta;
        this.NameV = NameV;
        this.PriceV = PriceV;
        this.QuantityV = QuantityV;
        this.Total = Total;
        //this.Date = obtenerFechaYHoraActual();        
        this.Date = Date;
        //this.LDate = LDate;
        //this.Date = Date;
        //this.Datenow = Datenow;
    }   
    public int getIdVenta() {
        return IdVenta;
    }

    public void setIdVenta(int IdVenta) {
        this.IdVenta = IdVenta;
    }

    public String getNameV() {
        return NameV;
    }

    public void setNameV(String NameV) {
        this.NameV = NameV;
    }

    public int getPriceV() {
        return PriceV;
    }

    public void setPriceV(int PriceV) {
        this.PriceV = PriceV;
    }

    public int getQuantityV() {
        return QuantityV;
    }

    public void setQuantityV(int QuantityV) {
        this.QuantityV = QuantityV;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }           

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }
    
    public static String obtenerFechaYHoraActual() {
	String formato = "yyyy-MM-dd HH:mm:ss";
	DateTimeFormatter formateador = DateTimeFormatter.ofPattern(formato);
	LocalDateTime ahora = LocalDateTime.now();
	return formateador.format(ahora);
    }
}    
   


