package org.sofka.config;

import java.io.FileInputStream;
import java.util.Properties;

import static org.sofka.utilities.PrintLogger.printMessage;


/**
 * Clase utilidad de configuracion encargada de realizar la lectura y captura de las variables de entorno
 * en el archivo aplication.properties
 * @author Camilo Morales S - Camilo Castañeda
 */
public class Configuration {
    //Path del donde se encuentra almacenado el archivo aplicaction.properties
    private static final String PATH = "src/main/resources/application.properties";

    /**
     * Constructor privado para prevenir instancia de la clase ya que es un patron singleton
     * @constructor
     * @throws IllegalStateException retorna excepción en el momento de crear una instanci
     *
     */
    private Configuration() {
        throw new IllegalStateException("Utility class");
    }

    /**
     Método estático que se encarga de servir las variables de entorno disponibles en el archivo aplication.properties
     * @return Retorna las propiedades almacenadas en el aplicacion.properties
     */
    public static Properties configurationVariables() {
        // Creacion de instancia tipo properties para cargar los datos del archivo aplicacion.properties
        Properties properties = new Properties();

        //Lectura de archivo aplication.properties
        try (FileInputStream fileInputStream = new FileInputStream(PATH)) {
            // carga las variables alojadas en el aplication.properties
            properties.load(fileInputStream);
        } catch (Exception e) {
            printMessage(e.getMessage());
        }
        return properties;
    }
}
