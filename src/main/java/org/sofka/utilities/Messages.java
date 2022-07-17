package org.sofka.utilities;

import org.sofka.models.Player;

import static org.sofka.utilities.PrintLogger.printMessage;

/**
 * Clase utilidad para mostrar los mensajes de la aplicacion
 * @author Camilo Morales S - Camilo Castañeda
 */
public class Messages {


    /**
     * Constructor privado para prevenir instancia de la clase ya que es una clase tipo utility
     * @constructor
     * @throws IllegalStateException retorna excepción en el momento de crear una instanci
     *
     */
    private Messages() {
        throw new IllegalStateException("Utility class");
    }

    public static void welcomeMessage() {
        printMessage("Bienvenido al juego de preguntas y respuestas");
    }

    public static void customMessage(String message) {
        printMessage(message);
    }

    public static void instructions() {
        printMessage("Instrucciones del juego\n[1.] El juego tiene 5 rondas, cada ronda tiene asignada una categoría y cada categoría tiene unas preguntas, el sistema\n" +
                "escoge una pregunta aleatoriamente.\n" + "[2.] El jugador selecciona una opción de las 4 disponibles, si la respuesta es incorrecta el juego termina, si acierta pasa\n" +
                "de ronda y aumenta la complejidad.\n" +
                "[3.] Cada ronda tiene una puntuación que se irá acumulando a medida que pase a la siguiente.\n" +
                "[4.] Se gana si se responden las 5 categorías.\n\nPara iniciar presione cualquier tecla o [0] para finalizar");
    }

    public static void winnerMessage(Player player){
        printMessage("Felicitaciones "+player.getName()+" eres el ganador con un acumulado de "+ player.getPoints()+"\n\n"+"[1.] jugar de nuevo\n"+"[0. ] Salir");
    }

    public static void gameOverMessage(Player player){
        printMessage("Lo sentimos mucho "+player.getName()+" esta vez perdiste pero sigue practicado \n\n"+"[1.] Jugar de nuevo\n"+"[0. ] Salir");
    }

    public static void correctAnswer(Player player){
        printMessage("Felicidades "+player.getName()+ " pasate al siguiente nivel\n"+"Puntos acumulados [" +player.getPoints()+"] Nivel ["+player.getLevel()+"]\n\nPresione enter para continuar");
    }

    public static void informationLevelPoint(Player player){
        printMessage("Bienvenido "+player.getName()+ "\n"+"tus puntos acumulados son [" +player.getPoints()+"] y tu Nivel es el ["+player.getLevel()+"]\n\nPresione enter para continuar");
    }
}
