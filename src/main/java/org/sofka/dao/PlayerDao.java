package org.sofka.dao;


import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.sofka.models.Player;
import org.sofka.utilities.PrintLogger;

import static org.sofka.bootstrap.DataBaseConnection.getDatabase;

/**
 * PlayerDao DAO es la clase encargada de acceso a datos en la base de datos para los Players
 * esta clase contiene los diferentes metodos encargado de acceso a los datos de la coleccion player
 * @author Camilo Morales S - Camilo Castañeda
 */

public class PlayerDao implements IPlayerDao {

    private static final MongoCollection<Document> PLAYERS = getDatabase().getCollection("players");

    /**
     *
     * @param email -email del jugador a obtener
     * @return documento usuario de mongo
     */
    @Override
    public Document getPlayerByEmail(String email) {
        Document player = null;
        try {

            BasicDBObject query = new BasicDBObject();      // Crea objeto para construir la consulta
            query.put("email", email);                      // Agrega los parametros de busqueda
            player = PLAYERS.find(query).first();           // Ejecuta la actualizacion
        } catch (MongoException e) {
            PrintLogger.printMessage(e.getMessage());
        }

        return player;
    }

    /**
     * Función encargada de realizar la creacion del jugador en la base de datos
     * @param name nombre del jugador
     * @param email email del jugador
     * @return - documento de mongo con el usuario creado
     */
    @Override
    public Document createPlayer(String name, String email) {
        Document document = null;
        try {
            if (getPlayerByEmail(email) == null) {
                document = new Document();
                document.append("name", name);
                document.append("points", 0);
                document.append("email", email);
                document.append("level", 1);
                PLAYERS.insertOne(document);

            }
        } catch (MongoException e) {
            PrintLogger.printMessage(e.getMessage());
        }

        return document;
    }

    /**
     * Metodo para actualizar el jugador en la base de datos (Puntos y Nivel
     * @param player -jugador a actualizar los puntos y nivel en la base de datos
     */
    @Override
    public void updatePlayer(Player player) {
        BasicDBObject query = new BasicDBObject();           // Crea objeto para construir la consulta
        query.put("_id", player.get_id());                   // Agrega los parametros de busqueda
        Bson updates = Updates.combine(
                Updates.set("level", player.getLevel()),     // Crea objeto para actulizar campos especificos level y player
                Updates.set(
                        "points", player.getPoints()
                ));
        try {
            PLAYERS.updateOne(query, updates);               // Ejecuta la actualizacion
        } catch (MongoException e) {
            PrintLogger.printMessage(e.getMessage());
        }
    }


}
