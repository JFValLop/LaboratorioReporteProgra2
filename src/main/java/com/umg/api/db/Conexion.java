package com.umg.api.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Conexion {
    private static final String ARCHIVO = "/db.properties";

    private static Properties leerPropiedades() throws Exception {
        Properties datos = new Properties();

        try (InputStream entrada = Conexion.class.getResourceAsStream(ARCHIVO)) {
            if (entrada == null) {
                throw new Exception("No se encontro el archivo db.properties");
            }

            datos.load(entrada);
        }

        return datos;
    }

    public static Connection getConexion() throws Exception {
        Properties datos = leerPropiedades();

        String driver = datos.getProperty("db.driver");
        String url = datos.getProperty("db.url");
        String usuario = datos.getProperty("db.user");
        String clave = datos.getProperty("db.password");

        if (url == null || url.trim().isEmpty()) {
            throw new Exception("La propiedad db.url no esta configurada");
        }

        Class.forName(driver);

        return DriverManager.getConnection(url, usuario, clave);
    }

    public static void cerrar(Connection conexion) {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (Exception e) {
            System.out.println("Error al cerrar la conexion: " + e.getMessage());
        }
    }
}
