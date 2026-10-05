package reports;

import com.umg.api.db.Conexion;
import java.sql.Connection;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class ProductReport {
    public static void generarReporte() throws Exception {
        String ruta = "src/main/java/reports/ProductoReporte.jrxml";

        JasperReport reporte =
                JasperCompileManager.compileReport(ruta);

        Connection conexion = Conexion.getConexion();

        try {
            JasperPrint impresion =
                    JasperFillManager.fillReport(
                            reporte,
                            new HashMap<>(),
                            conexion
                    );

            JasperViewer.viewReport(impresion, false);

        } finally {
            Conexion.cerrar(conexion);
        }
    }
}
