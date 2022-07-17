package org.sofka.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.sofka.dao.CategoryDao;
import org.sofka.dao.ICategoryDao;
import org.sofka.models.Category;
import org.sofka.models.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Clase que se encarga de servir los metodos de la consulta a la base de datos de las categorias
 *
 * @author Camilo Morales S - Camilo Castañeda
 */


public class CategoryService {

    /**
     * Constructor privado para prevenir instancia de la clase ya que es un patron singleton
     *
     * @throws IllegalStateException retorna excepción en el momento de crear una instancia
     * @constructor
     */
    private CategoryService() {
        throw new IllegalStateException("Utility class");
    }


    // Inyecta la instancia de la clase dato la cual se encarga de las consultas en la base de datos
    protected static ICategoryDao categoryDao = new CategoryDao();


    /**
     *
     * @param level - nivel para consultar las preguntas del nivel en el que se encuentra el jugador
     * @return categoria con su pregunta puntos y opciones
     */
    public static Category randomQuestions(Integer level) {

        Random random = new Random();  // crea instancia para generar un numero aleatoria entre 0 y 5

        Category category = getCategory(level);  // llama al método getCategory para obtener las preguntas correspondientes a la categoría

        Integer value = random.nextInt(
                (category.getQuestions().size() - 1)); // Genera un numero aleatorio entre 0 y el total de preguntas de la categoria

        List<Question> question = new ArrayList<>();  // Crea una lista de tipo question

        question.add(category.getQuestions().get(value)); // obtiene la pregunta aleatoria de las preguntas de la categoría
        category.setQuestions(question);
        return category;

    }

    /**
     *
     * @param level - nivel para consultar las preguntas del nivel en el que se encuentra el jugador
     * @return categoria correspondiente al nivel pasado por parametros
     */
    private static Category getCategory(Integer level) {
        FindIterable<Document> documents = categoryDao.getCategoriesByLevel(level);  // llama la capa DAO para consultar las preguntas de el nivel pasado en los parametros
        return serializeCategory(documents);                                         // llama metodo para serializar la respuesta de la base de datos
    }


    /**
     *
     * @param documents Documentos obtenidos en la consulta a la base de datos
     * @return El objeto serializado de tipo categoria
     */
    private static Category serializeCategory(FindIterable<Document> documents) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(Objects.requireNonNull(documents.first()).toJson(), Category.class);
    }


}
