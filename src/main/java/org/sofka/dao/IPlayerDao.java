package org.sofka.dao;


import org.bson.Document;
import org.sofka.models.Player;


public interface IPlayerDao {
     Document getPlayerByEmail(String email);

     Document createPlayer(String name, String email);

     void updatePlayer(Player player);

}
