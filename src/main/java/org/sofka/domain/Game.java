package org.sofka.domain;

import lombok.Getter;
import lombok.Setter;
import org.sofka.models.Category;
import org.sofka.models.Player;
import org.sofka.services.CategoryService;
import org.sofka.services.PlayerService;

/**
 * Clase encarga de la lógica del juego como iniciar, pasar de nivel y verificar respuestas
 * @author Camilo Morales S - Camilo Castañeda
 */
public class Game {
    @Setter
    @Getter
    private Integer level;
    @Setter
    @Getter
    private Player player;
    @Setter
    @Getter
    private Category category;

    /**
     * @constructor
     * @param level -  Nivel en el que inicia el juga
     * @param player - Jugar que participa en el juego
     */
    public Game(Integer level, Player player) {
        this.level = level;
        this.player = player;
    }

    /**
     * Metodo encargado de iniciaqlizar el  el juego
     */
    public void initGame() {
        category = CategoryService.randomQuestions(level); // Invoca el servicio que genera las preguntas aleatorias
    }


    /**
     *
     * @param option opcion seleccionada por el usuario
     * @return si la opcion seleccionada es valida o no
     */
    public Boolean verifyOption(Integer option) {
        Boolean isCorrect = category
                .getQuestions()
                .get(0)
                .getOptions()
                .get(option).isCorrect();                           // Verifica si la respuesta selecciona es correcta

        if (isCorrect) {
            nextLevel();                                            // Pasa al siguiente nivel
        } else {
            restarGame();                                           // Reinicia el juego
        }

        return isCorrect;
    }

    /**
     * Metodo encargado de reiniciar el juego
     */
    public void restarGame() {
        this.level = 1;
        player.setLevel(level);
        category = CategoryService.randomQuestions(1);            // Selecciona a aleatoriamente la pregunta de la categoria o nivel
        PlayerService.updateUser(player);                              // Actualiza usuario en base de datos

    }

    /**
     * Metodo encargado de pasar al siguiente nivel
     */
    public void nextLevel() {
        level++;
        if (level <= 5) {
            category = CategoryService.randomQuestions(level);           // Selecciona a aleatoriamente la pregunta de la categoria o nivel
            player.setPoints(player.getPoints() + category.getPoints()); // Aumenta los puntos del jugador
            player.setLevel(level);                                      // Establece nivel al que pasa el jugador
            PlayerService.updateUser(player);                            // Actualiza usuario en base de datos
        }



    }


}
