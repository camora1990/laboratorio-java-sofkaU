package org.sofka.ui;

import org.sofka.domain.Game;
import org.sofka.models.Player;
import org.sofka.services.PlayerService;

import java.util.Objects;

import static org.sofka.utilities.GetInputScanner.getInteger;
import static org.sofka.utilities.GetInputScanner.getString;
import static org.sofka.utilities.Messages.*;
import static org.sofka.utilities.Messages.customMessage;

public class ViewGame {
    private static Game game = null;
    public static void menuGame(){
        Integer input;
        Player player = null;
        welcomeMessage();
        instructions();

        if (!Objects.requireNonNull(getString()).toUpperCase().equalsIgnoreCase("0")) {

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

    private static void initGame(Player player) {
        informationLevelPoint(player);
        getString();

        Integer input;
        game = new Game(player.getLevel(), player);
        Boolean next;
        game.initGame();
        do {

            do {
                customMessage(game.getCategory().toString());
                customMessage("Selecciona una opción entre [1-4]");
                input = getInteger();
            } while (input < 1 || input > 4);
            next = game.verifyOption(input - 1);
            if (game.getLevel() < 6 && next) {
                correctAnswer(player);
            } else {
                next = continueGame(next, player);
            }


        } while (next);
    }

    private static Boolean continueGame(Boolean value, Player player) {
        Integer response;
        if (value) {
            winnerMessage(player);
        } else {
            gameOverMessage(player);
        }
        do {
            response = getInteger();
            if (response < 0 || response > 1) {
                customMessage("Selecciona una opción entre [0-1]");
            }
        } while (response < 0 || response > 1);
        game.restarGame();
        return response != 0;
    }

    private static Player login() {
        String input;
        Player player = null;

        do {
            input = verifyEmail();
            player = PlayerService.getPlayer(input);
            if (player == null) {
                customMessage("Usuario invalido");
            }

        } while (player == null);
        return player;
    }

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
            player = PlayerService.createPlayer(inputName, inputEmail);
        }
        return player;
    }

    private static String verifyEmail() {
        String input;
        do {
            customMessage("Ingrese un email valido [example@exaple.com]");
            input = getString();
        } while (!Objects.requireNonNull(input).matches("^[A-Za-z0-9+_.-]+@(.+)$"));
        return input.trim().toLowerCase();
    }
}
