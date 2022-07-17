package org.sofka.ui;

import org.sofka.domain.Game;
import org.sofka.models.Player;
import org.sofka.services.PlayerService;

import java.util.Objects;

import static org.sofka.utilities.GetInputScanner.getInteger;
import static org.sofka.utilities.GetInputScanner.getString;
import static org.sofka.utilities.Messages.*;

/**
 * Clase utilizada creada para la vista de juego con sus menu y mensajes
 *  @author Camilo Morales S - Camilo Castañeda
 */

public class ViewGame {

    // Propiedad tipo juego para guardar informacion de la partida
    private static Game game = null;

    private ViewGame() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Metodo estatico para visualizar el menu inicial con las instrucciones del juego
     */
    public static void menuGame(){
        Integer input;
        Player player = null;
        welcomeMessage();
        instructions();

        if (!Objects.requireNonNull(getString()).equalsIgnoreCase("0")) {

            do {
                customMessage("[1.] Inicio de sesión\n[2.] Registrarme");
                input = getInteger();
            } while (input < 1 || input > 2);

            if (input == 1) {
                player = login();
            } else {
                player = singUp();
            }
            initGame(player);
        }

        customMessage("Gracias por participar");
    }

    /**
     * Metodo para inicio de juego con
     * @param player - jugador que inicia la partida
     */
    private static void initGame(Player player) {

        informationLevelPoint(player);                   // Informacion del juegador con sus puntos y nivel en el que se encuentra
        getString();

        Integer input;
        game = new Game(player.getLevel(), player);     // Instacia del juego
        Boolean next;
        game.initGame();                                // Llama la funcion inicair juego del objeto game
        do {

            do {
                customMessage(game.getCategory().toString());  // Muestra las opciones y pregunta correspondiente a la categoria
                customMessage("Selecciona una opción entre [1-4] o [0] para salir");
                input = getInteger();
            } while (input < 0 || input > 4);
            if (input == 0) {
                return;
            }
            next = game.verifyOption(input - 1);               // Verifica si la opcion seleccionada es la correcta
            if (game.getLevel() < 6 && next) {
                correctAnswer(player);                        // Invoca metodo respuesta correcta
            } else {
                next = continueGame(next, player);
            }


        } while (next);
    }

    /**
     *
     * @param value - si la respuesta es correcta o no
     * @param player - Jugador que selcciona la opcion
     * @return - retorna si el juegador quiere continuarl el juego
     */
    private static Boolean continueGame(Boolean value, Player player) {
        Integer response;

        if (value) {
            winnerMessage(player);                  // Mensaje que muestra al jugador ganador
        } else {
            gameOverMessage(player);                // Mensaje que muestra al jugador perdedor
        }

        /*
         * Valida si quiere seguir jugando o salir del juego
         */
        do {
            response = getInteger();
            if (response < 0 || response > 1) {
                customMessage("Selecciona una opción entre [0-1]");
            }
        } while (response < 0 || response > 1);
        game.restarGame();
        return response != 0;
    }

    /**
     * Metodo encargado de iniciar seseion al jugador
     * @return - Retorna e jugador
     */
    private static Player login() {
        String input;
        Player player = null;

        do {
            input = verifyEmail();
            player = PlayerService.getPlayer(input);               // obtiene la sesion del jugador
            if (player == null) {
                customMessage("jugadorinvalido");
            }

        } while (player == null);
        return player;
    }


    /**
     * Metodo encargado de hacer registro nuevo jugdor
     * @return retorna el jugador creado
     */
    private static Player singUp() {
        Player player = null;
        String inputName;
        String inputEmail;

        inputEmail = verifyEmail();

        customMessage("Ingrese nombre completo");
        inputName = getString();
        player = PlayerService.createPlayer(inputName, inputEmail);
        while (player == null) {
            customMessage("El email ya se encuentra registrado en la base de datos");
            inputEmail = verifyEmail();
            player = PlayerService.createPlayer(inputName, inputEmail);     //Crea el jugador nuevo
        }
        return player;
    }


    /**
     * Funcion que valida si el email es correcto
     * @return - El correo valido
     */
    private static String verifyEmail() {
        String input;
        do {
            customMessage("Ingrese un email valido [example@exaple.com]");
            input = getString();
        } while (!Objects.requireNonNull(input).matches("^[A-Za-z0-9+_.-]+@(.+)$"));
        return input.trim().toLowerCase();
    }
}
