package uvg.edu.gt;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Clase principal que contiene el punto de entrada del programa.
 * Este programa interpreta y ejecuta código Lisp contenido en un archivo de texto.
 *
 * Autores: 
 * Javier Javier Alexander Linares Chang - 231135
 * Dulce Rebeca Ambrosio Jiménez - 231143
 */

public class Main {

    /**
     * Método principal que ejecuta el intérprete de un archivo Lisp.
     * @param args Argumentos de línea de comandos (no utilizados en este caso).
     */
    public static void main(String[] args) {
        // Ruta del archivo Lisp
        Path filePath = Paths.get("src/main/resources/codigo.lisp");
        
        // Verificar si el archivo existe
        if (Files.exists(filePath)) {
            System.out.println("El archivo existe en la ruta: " + filePath);
        } else {
            System.out.println("El archivo no existe en la ruta especificada: " + filePath);
            return;
        }

        // Leer el contenido del archivo y almacenarlo en una lista
        ArrayList<String> archivo = new ArrayList<>();
        try (Stream<String> lines = Files.lines(filePath, StandardCharsets.UTF_8)) {
            lines.forEach(archivo::add);
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al leer el archivo: " + e.getMessage());
            return;
        }

        // Crear un objeto Lexer utilizando el contenido del archivo
        Lexer programa = new Lexer(archivo);
        // Crear un objeto Interprete y pasarle el programa como argumento
        new Interprete(programa);
    }
}
