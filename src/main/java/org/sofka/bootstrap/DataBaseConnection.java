package org.sofka.bootstrap;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.sofka.utilities.PrintLogger;

import static org.sofka.config.Configuration.configurationVariables;


/**
 * Clase implementada para servir la conexion a la base de datos en mogo
 * @author Camilo Morales S - Camilo Castañeda
 */

public class DataBaseConnection {


    /**
     * Constructor privado para prevenir instancia de la clase
     * @constructor
     *
     */
    private DataBaseConnection() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Metodo estatico qu se encarga de realizar lo canexion a la base de datos
     * @return - La conexión a la base de datos para ser utilizada en las consultas
     */
    public static MongoDatabase getDatabase() {

        MongoDatabase database = null;
        try  {

            // Crea un cliente o conexion a la base de datos capturando el string de conexion del aplication.properties
            MongoClient client = MongoClients.create(configurationVariables().getProperty("string_connection"));

            // Retorna la base de datos en la cual se van a realizar la persistyencia de los datos
            database = client.getDatabase(configurationVariables().getProperty("name_database"));

        } catch (Exception e) {
            PrintLogger.printMessage(e.getMessage());

        }
        return database;

    }
}


