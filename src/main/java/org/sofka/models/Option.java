package org.sofka.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * Clase para la creacion de las opciones en las preguntas del juego
 * @author Camilo Morales S - Camilo Castañeda
 */

@ToString
public class Option {
    @Getter
    @Setter
    private String item;        // Posible respuesta
    @Getter
    @Setter
    private boolean isCorrect;  // Marca para validar si la opcion es correcta o incorrecta
}