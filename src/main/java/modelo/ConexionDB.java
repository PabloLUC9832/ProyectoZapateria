package modelo;

/**
 *
 * @author Pablo
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    
    // Librería de MySQL
    public String driver = "com.mysql.cj.jdbc.Driver";
    // Nombre de la base de datos
    public String database = "zapateria";
    // Host
    public String hostname = "localhost";
    // Puerto
    public String port = "3306";
    //Rura a nuestra base de datos
    public String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";
    // Nombre de usuario
    public String username = "root";

    // Clave de usuario
    public String password = "";
       
    public ConexionDB() {
        this.hostname = "localhost";
        this.port = "3306";
        this.database = "zapateria";
        this.username = "root";
        this.password = "";

        this.driver = "com.mysql.cj.jdbc.Driver";
        this.url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";

    }
    
    public Connection getConnection() throws Exception{
       
        try {
            Class.forName(driver);
            System.out.println("CONECTADO");
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            throw new Exception("Error en ConexionDB. La causa es: " + ex.getCause().toString());
        }
        
    }    
}
