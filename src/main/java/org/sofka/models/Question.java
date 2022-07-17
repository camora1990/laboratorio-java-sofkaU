package org.sofka.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * Clase que crea las preguntas correspondientes al juego
 * @author Camilo Morales S - Camilo Castañeda
 */

@lombok.Data
public class Question {
    @Getter
    @Setter
    private String question;       // Pregunta correspondiente a un nivel
    @Getter
    @Setter
    private List<Option> options;  // Opciones correspondientes a la pregunta


    /**
     * Metodo encargado de retornar el string para mostrar en pantalla las preguntas con sus opciones
     * @return retorna la preguntas a imrimr por consola con sus respectivas opciones
     */
    @Override
    public String toString() {

        final String[] message = { "[*] "+ question+"\n"};    // Array que concatena los mensajes a mostrar por consola

        for (int i = 0; i < options.size() ; i++) {
            message[0] = message[0] + "["+(i+1)+".] "+ options.get(i).getItem() + "\n";
        }

        return message[0];
    }
}