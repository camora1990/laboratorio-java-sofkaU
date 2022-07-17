package org.sofka.utilities;

import java.util.Scanner;

/**
 * Clase utilidad para capturar las entradas del usuario por consola
 * @author Camilo Morales S - Camilo Castañeda
 */
public class GetInputScanner {


    // Instancia del metodo scanner para captura de informacion por consola
    private static  final Scanner SCANNER = new Scanner(System.in);

    /**
     * Constructor privado para prevenir instancia de la clase ya que es una clase tipo utility
     * @constructor
     * @throws IllegalStateException retorna excepción en el momento de crear una instanci
     *
     */
    private GetInputScanner(){
        throw new IllegalStateException("Utility class");
    }


    /**
     * Obtiene las entradas de tipo integer ingresadas por el usuario
     * @return retona el parseo de la entrada tipo numerica o -99 cuando en un dato que no es numerico
     */
    public static Integer getInteger (){
        try {
            return Integer.parseInt(SCANNER.nextLine());
        }catch (Exception exception){
            return -99;
        }
    }

    /**
     * Obtiene las entradas de tipo string ingresadas por el usuario
     * @return retona el parseo de la entrada tipo string o null cuando en un dato que no es valida
     */
    public static String getString (){
        try {
            return String.valueOf(SCANNER.nextLine()).trim();
        }catch (Exception exception){
            return null;
        }
    }
}
