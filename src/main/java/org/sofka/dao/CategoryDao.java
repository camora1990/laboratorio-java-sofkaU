package org.sofka.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.sofka.utilities.PrintLogger;

import static org.sofka.bootstrap.DataBaseConnection.getDatabase;


/**
 * Category DAO es la clase encargada de acceso a datos en la base de datos para las categorías
 * esta clase contiene los diferentes metodos encargado de acceso a los datos de la coleccion category
 * @author Camilo Morales S - Camilo Castañeda
 */
public class CategoryDao implements ICategoryDao {

    // Obtiene la colección en las que se van a ejecutar las consultas
    private static final MongoCollection<Document> CATEGORY = getDatabase().getCollection("categories");

    /**
     * Función encargada de realizar la consulta en la base de datos y obtener las categorías por nivel
     * @param level - Nivel para realizar el query a la base de datos
     * @return - Retorna {@code FindIterable<Document>} un iterable de tipo documentos con las coincidencias de la consulta
     */
    @Override
    public FindIterable<Document> getCategoriesByLevel(Integer level) {

        FindIterable<Document> documents = null;
        BasicDBObject query = new BasicDBObject();  // crea objeto para agregar la consulta a la base de datos
        query.put("level", level);                  // Agrega parametro nivel a la consulta (key, value)

        try {
            documents = CATEGORY.find(query);       // Busca la categoria de ese nivel
        }catch (MongoException e){
            PrintLogger.printMessage(e.getMessage());
        }
        return documents;

    }
}
