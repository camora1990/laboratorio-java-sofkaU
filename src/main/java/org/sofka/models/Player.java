package org.sofka.models;

import com.mongodb.BasicDBObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;

/**
 * Clase que representa el jugador que empieza el juego
 * @author Camilo Morales S - Camilo Castañeda
 */
@ToString
public class Player {
    @Getter
    @Setter
    private ObjectId _id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private Long points;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private Integer level;

    /**
     * Constructor de clase que inicializa propiedades del jugador que inicia el juego
     * @param name recibe el nombre del jugador
     */
    public Player(String name, String email) {
        this.name = name;
        this.points=0L;
        this.email = email;
    }
}
