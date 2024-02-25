package uvg.edu.gt;

import java.util.ArrayList;

/**
 * Clase Lexer que se encarga de procesar instrucciones y crear un código en forma de cadena.
 */
public class Lexer {

    private ArrayList<String> lineas; // Lista de instrucciones

    /**
     * Constructor de la clase Lexer.
     * @param instrucciones Lista de instrucciones.
     */
    public Lexer(ArrayList<String> instrucciones) {
        this.lineas = instrucciones;
    }

    /**
     * Constructor de la clase Lexer sin parámetros.
     * Crea una lista de instrucciones vacía.
     */
    public Lexer() {
        this.lineas = new ArrayList<String>();
    }

    /**
     * Agrega una instrucción a la lista de instrucciones.
     * @param inst La instrucción a agregar.
     */
    public void addInstruccion(String inst) {
        this.lineas.add(inst);
    }

    /**
     * Obtiene la lista de instrucciones.
     * @return La lista de instrucciones.
     */
    public ArrayList<String> getLineas() {
        return this.lineas;
    }

    /**
     * Crea una cadena concatenando todas las instrucciones.
     * @return El código en forma de cadena.
     */
    public String stringCreator() {
        String codigo = "";
        for (String linea: lineas){
            codigo += linea;
        }
        return codigo;
    }
}