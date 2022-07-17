package org.sofka.dao;

import com.mongodb.client.FindIterable;
import org.bson.Document;


/**
 * Interface de implementación de acceso a datos para la categoría
 *  @author Camilo Morales S - Camilo Castañed
 */
public interface ICategoryDao {

    /**
     *
     * @param level - Nivel para traer las preguntas correspondientes a dicho nivel
     * @return Documentos de mongo con las coincidencias del nivel
     */
    FindIterable<Document> getCategoriesByLevel (Integer level);

}
