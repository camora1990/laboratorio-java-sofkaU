package org.sofka.services;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bson.Document;
import org.sofka.dao.IPlayerDao;
import org.sofka.dao.PlayerDao;
import org.sofka.models.Player;

import java.util.Objects;

public class PlayerService {

    /**
     * Constructor privado para prevenir instancia de la clase ya que es un patron singleton
     *
     * @throws IllegalStateException retorna excepción en el momento de crear una instancia
     * @constructor
     */
    private PlayerService() {
        throw new IllegalStateException("Utility class");
    }


    // Inyecta la instancia de la clase dato la cual se encarga de las consultas en la base de datos
    protected static IPlayerDao playerDao = new PlayerDao();


    /**
     * @param email - email para consultar usuario registrado
     * @return jugador
     */
    public static Player getPlayer(String email) {
        Document document = playerDao.getPlayerByEmail(email);
        if (document == null) {
            return null;
        }// llama la capa DAO para consultar el jugador
        return serializePlayer(document);                                   // llama metodo para serializar el jugador
    }

    public static Player createPlayer(String name, String email) {
        Document document = playerDao.createPlayer(name, email);
        if (document == null) {
            return null;
        }
        return serializePlayer(document);
    }

    public static void updateUser(Player player) {

        playerDao.updatePlayer(player);
    }


    /**
     * @param document Documentos obtenidos en la consulta a la base de datos
     * @return El objeto serializado de tipo player
     */
    private static Player serializePlayer(Document document) {
        Player player = null;
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        if (document != null) {
            player = gson.fromJson(Objects.requireNonNull(document).toJson(), Player.class);
            player.set_id(document.getObjectId("_id"));
        }

        return player;
    }

}
