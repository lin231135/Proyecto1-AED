package uvg.edu.gt;

import java.util.HashMap;

/**
 * Clase que representa el intérprete para ejecutar código Lisp.
 */

public class Interprete {

    private Lexer codigo;
    private HashMap<String, Stack> funciones = new HashMap<>();
    private HashMap<String, Object> variables = new HashMap<>();

    public Interprete(Lexer codigoListoParaleer) {
        this.codigo = codigoListoParaleer;
        interpretar();
    }

    private void interpretar() {
    }

    private Stack<Object> quitarComentarios() {
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