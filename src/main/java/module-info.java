module org.openjfx.proyectozapateria {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires com.jfoenix;
    requires itextpdf;
    
    opens ProyectoZapateria                   to javafx.fxml;
    opens controlador                         to javafx.fxml;
    
    opens controlador.Empleado                to javafx.fxml;
    opens controlador.Proveedor               to javafx.fxml;
    opens controlador.Cliente                 to javafx.fxml;
    opens controlador.Promocion               to javafx.fxml;
    opens controlador.Horario                 to javafx.fxml;
    opens controlador.Inventario              to javafx.fxml;
    
    opens modelo.Cliente                      to javafx.fxml;
    opens modelo.Empleado                     to javafx.fxml;
    opens modelo.Promocion                    to javafx.fxml;
    opens modelo.Proveedor                    to javafx.fxml;
    opens controlador.Producto                to javafx.fxml;
    opens modelo.Producto                     to javafx.fxml;
    opens modelo.Horario                      to javafx.fxml;
    opens modelo.Inventario                   to javafx.fxml;
    
    exports ProyectoZapateria;
    exports modelo.Empleado;
    exports modelo.Inventario;    
    exports modelo.Cliente;
    exports modelo.Promocion;
    exports modelo.Proveedor;
    exports modelo.Producto;
    exports modelo.Horario;
}
