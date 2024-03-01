package uvg.edu.gt;

import java.util.HashMap;

/**
 * Clase que representa el intérprete para ejecutar código Lisp.
 */

public class Interprete {

    private Lexer codigo;
    private HashMap<String, Stack> funciones = new HashMap<>();
    private HashMap<String, Object> variables = new HashMap<>();

    /**
     * Constructor de la clase Interprete.
     * @param codigoListoParaleer Lexer que contiene el código Lisp listo para interpretar.
     */
    public Interprete(Lexer codigoListoParaleer) {
        this.codigo = codigoListoParaleer;
        interpretar();
    }

    /**
     * Método principal para interpretar el código Lisp.
     */
    private void interpretar() {
        procesarInstrucciones(procesarProcesarCodigo(quitarComentarios()));
    }

    /**
     * Método para quitar comentarios del programa y convertirlo en una lista de instrucciones.
     * @return Lista de instrucciones del programa.
     */
    private Stack<Object> quitarComentarios() {
        // Inicializar el stack
        Stack<Object> stack = new Stack<>();
        // Crear un nuevo Lexer para manipular el código
        Lexer codigo = new Lexer();
        String programaClean;

        // Añadir paréntesis para el inicio y fin del programa
        codigo.addInstruccion("(");
        // Iterar sobre cada línea del código original
        for (String linea : this.codigo.getLineas()) {
            // Eliminar comentarios de las líneas
            if (linea.length() >= 1 && linea.charAt(0) != ';') {
                String nuevalinea = "";
                for (int i = 0; i < linea.length(); i++) {
                    if (linea.charAt(i) != ';') {
                        nuevalinea += linea.charAt(i);
                    } else {
                        break;
                    }
                }
                // Añadir la línea limpia al Lexer
                codigo.addInstruccion(nuevalinea);
            }
        }
        // Añadir paréntesis para el final del programa
        codigo.addInstruccion(")");
        // Crear una cadena de caracteres con el código limpio
        programaClean = codigo.stringCreator().replaceAll("\\("," ( ").replaceAll("\\)", " ) ");

        // Dividir la cadena en palabras y añadirlas al stack
        for (String linea : programaClean.trim().split("\\s+")) {
            stack.push(linea);
        }
        return stack;
    }

    private Object procesarProcesarCodigo(Stack<Object> codigo) {
    }

    private void procesarInstrucciones(Object pr) {
    }

    public Object getVariable(String variable) {
    }

    private void imprimirStackVector(Object pr) {
    }

    private Object parsearString(String dato) {
    }

    private Object ejecutarFuncion(Object fun) {
    }

    private Boolean Atom(Object algo) {
    }

    private String traducirBooleano(boolean valor) {
    }

    private Boolean equal(Object e1, Object e2) {
    }


    private Boolean compare(String comp, Integer e1, Integer e2) {
    }

    private Stack setearParametros(Stack parametros, Stack ingresados, Object funcionNueva) {
    }

    public Object leerInstruccion(Object intruccion) {
    }

    public Object getResultado() {
    }
}