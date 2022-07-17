package org.sofka.models;


import com.mongodb.BasicDBObject;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * Clase utilizada para la serialización de las peticiones de las categorías y creacion de categorias
 *  @author Camilo Morales S - Camilo Castañeda
 */
public class Category {

    @Getter
    @Setter
    private BasicDBObject _id;  //Propiedad de tipo Objecto mongo para almacenar el id de la base de datos
    @Getter
    @Setter
    private Integer level;      // Nivel de la categoria
    @Getter
    @Setter
    private String categoryName; // Nombre de la categoria
    @Getter
    @Setter
    private long points;         // puntos de la categoria
    @Getter
    @Setter
    private List<Question> questions;  // Preguntas de la categoria


    /**
     * Método para mostrar por consola la categoría con sus respectivas preguntas y opciones
     * @return retorna el menja a mostrar por consola de la categoria
     */
    @Override
    public String toString() {
        return "Nivel: " + level + "\n" +
                "Categoria : " + categoryName + "\n" +
                "Por : " + points + " puntos\n" + questions.get(0).toString();
    }
}