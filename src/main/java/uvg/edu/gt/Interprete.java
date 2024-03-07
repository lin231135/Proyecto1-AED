package uvg.edu.gt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    /**
     * Método para procesar el código Lisp y convertirlo en una estructura de datos.
     * @param codigo Lista de instrucciones del programa.
     * @return Estructura de datos representando el código Lisp procesado.
     */
    private Object procesarProcesarCodigo(Stack<Object> codigo) {
        // Verificar si el código está vacío
        if (codigo.empty()) {
            throw new IllegalArgumentException("Unexpected EOF while reading");
        }
        // Obtener el primer elemento del código
        Object sec = codigo.getVector().remove(0);

        // Si el primer elemento es '(', procesar una lista de instrucciones
        if (sec.equals("(")) {
            Stack<Object> inst = new Stack<>(codigo.size() - 1);

            // Iterar hasta encontrar el paréntesis de cierre ')'
            while (!codigo.getVector().get(0).equals(")")) {
                // Procesar recursivamente cada elemento del código
                inst.push(procesarProcesarCodigo(codigo));
            }
            // Eliminar el paréntesis de cierre
            codigo.getVector().remove(0);
            return inst;
        } else {
            // Si no es una lista, retornar el valor del elemento
            return parsearString((String) sec);
        }
    }

    /**
     * Método para procesar las instrucciones del código Lisp.
     * @param pr Estructura de datos que representa las instrucciones del programa.
     */
    private void procesarInstrucciones(Object pr) {
        if (pr instanceof Stack) {
            Stack codigo = (Stack) pr;
            for (int i = 0; i < codigo.size(); i++) {
                leerInstruccion(codigo.getVector().elementAt(i));
            }

        } else if (pr instanceof String) {
            leerInstruccion(pr);
        }
    }

    /**
     * Método para obtener el valor de una variable.
     * @param variable Nombre de la variable.
     * @return Valor de la variable.
     */
    public Object getVariable(String variable) {
        // Verifica si la variable está en el mapa de variables
        if (variables.containsKey(variable)) {
            // Obtiene el valor de la variable del mapa
            Object valorVariable = variables.get(variable);
            // Retorna el valor de la variable
            return valorVariable;
        } else {
            // Si la variable no está definida, retorna un valor predeterminado (en este caso, 0)
            return 0;
        }
    }

    /**
     * Método para imprimir una estructura StackVector.
     * @param pr Estructura StackVector a imprimir.
     */
    private void imprimirStackVector(Object pr) {

        if (pr instanceof Stack) {
            Stack programa = (Stack) pr;
            for (int i = 0; i < programa.size(); i++) {
                if (programa.getVector().elementAt(i) instanceof Stack) {
                    imprimirStackVector(programa.getVector().elementAt(i));
                } else {
                    System.out.print(programa.getVector().elementAt(i) + " ");
                }
            }
        } else {
            System.out.print("Problema");
        }

    }

    /**
    * Método para convertir una cadena de caracteres en un tipo de dato de Java
    * correspondiente (entero o flotante).
    * @param dato Cadena de caracteres a parsear.
    * @return Objeto con el tipo de dato correspondiente.
    */
    private Object parsearString(String dato) {
        try {
            // Intenta parsear la cadena como un entero
            return Integer.parseInt(dato);
        } catch (NumberFormatException exc) {
            try {
                // Si falla, intenta parsear la cadena como un flotante
                return Float.parseFloat(dato);
            } catch (NumberFormatException exc2) {
                // Si falla nuevamente, retorna la cadena original
                return dato;
            }
        }
    }

    /**
     * Método para ejecutar una función Lisp.
     * @param fun Función a ejecutar.
     * @return Resultado de la ejecución de la función.
     */
    private Object ejecutarFuncion(Object fun) {
        Stack funNuevo = new Stack();
        if (fun instanceof Stack) {
            Stack programa = (Stack) fun;
            for (int i = 0; i < programa.size(); i++) {
                if (programa.getVector().elementAt(i) instanceof Stack) {
                    ejecutarFuncion(programa.getVector().elementAt(i));
                } else {
                    funNuevo.push(programa.getVector().elementAt(i));
                }
            }
        } else {
            System.out.print("Problema");
        }
        return leerInstruccion(funNuevo);
    }

    private Boolean Atom(Object algo) {
        if (algo instanceof String) {
            return true;
        } else if (algo instanceof Integer) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para traducir un valor booleano a su representación en Lisp.
     * @param valor Valor booleano a traducir.
     * @return Representación en Lisp del valor booleano.
     */

    private String traducirBooleano(boolean valor) {
        return valor ? "T" : "NIL";
    }

    /**
     * Método para comparar si dos objetos son iguales en Lisp.
     * @param e1 Primer objeto a comparar.
     * @param e2 Segundo objeto a comparar.
     * @return Valor booleano que indica si los objetos son iguales.
     */
    private Boolean equal(Object e1, Object e2) {
        if (e1.equals(e2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método para comparar dos números en Lisp.
     * @param comp Operador de comparación (< o >).
     * @param e1 Primer número.
     * @param e2 Segundo número.
     * @return Valor booleano que indica el resultado de la comparación.
     */

    private Boolean compare(String comp, Integer e1, Integer e2) {
        if (comp.equals("<")) {
            return e1 < e2;
        } else if (comp.equals(">")){
            return e1 > e2;
        }

        return false;
    }

    /**
     * Método para reemplazar los parámetros de una función por sus valores correspondientes.
     * @param parametros Parámetros de la función.
     * @param ingresados Valores ingresados para los parámetros.
     * @param funcionNueva Cuerpo de la función con los parámetros reemplazados.
     * @return Cuerpo de la función con los parámetros reemplazados.
     */
    private Stack setearParametros(Stack parametros, Stack ingresados, Object funcionNueva) {
        Stack resultado = null;

        if (funcionNueva instanceof Stack) {
            Stack funcionOperable = (Stack) funcionNueva;
            for (int i = 0; i < funcionOperable.size(); i++) {
                if (funcionOperable.getVector().elementAt(i) instanceof Stack) {
                    setearParametros(parametros, ingresados, funcionOperable.getVector().elementAt(i));
                } else {

                    for (int j = 0; j < parametros.size(); j++) {
                        String parametro = (String) parametros.getVector().elementAt(j);
                        Object ingresado = ingresados.getVector().elementAt(j);

                        if (funcionOperable.getVector().elementAt(i) instanceof Stack) {
                            setearParametros(parametros, ingresados, (Stack) funcionOperable.getVector().elementAt(i));
                        } else {
                            if (funcionOperable.getVector().elementAt(i).equals(parametro)) {
                                funcionOperable.getVector().set(i, ingresado);
                            } else {
                                funcionOperable.getVector().set(i, funcionOperable.getVector().elementAt(i));
                            }
                        }
                    }
                }
            }

            resultado = funcionOperable;
        } else {
            System.out.print("Problema");
        }
        return resultado;
    }

    /**
     * Método para interpretar una instrucción del código Lisp.
     * @param intruccion Instrucción a interpretar.
     * @return Resultado de la instrucción.
     */
    public Object leerInstruccion(Object intruccion) {
        if (intruccion instanceof Stack) {
            Stack codigo = (Stack) intruccion;
            if (codigo.size() > 0) {
                Object sec = (codigo.getVector().firstElement());
                if (sec instanceof String) {
                    procesarInstrucciones(codigo);
                    switch (((String) sec).toUpperCase()) {
                        
                        case "+":
                            Object valor1 = leerInstruccion(codigo.getVector().elementAt(1));
                            Object valor2 = leerInstruccion(codigo.getVector().elementAt(2));
                            // Verificar si alguno de los argumentos es una variable
                            if (valor1 instanceof String) {
                                valor1 = getVariable((String) valor1);
                            }
                            if (valor2 instanceof String) {
                                valor2 = getVariable((String) valor2);
                            }
                            if (valor1 instanceof Integer && valor2 instanceof Integer) {
                                return (Integer) valor1 + (Integer) valor2;
                            } else {
                                throw new IllegalArgumentException("Operación inválida: " + valor1 + " + " + valor2);
                            }
        
                        case "-":
                            Object valor3 = leerInstruccion(codigo.getVector().elementAt(1));
                            Object valor4 = leerInstruccion(codigo.getVector().elementAt(2));
                            // Verificar si alguno de los argumentos es una variable
                            if (valor3 instanceof String) {
                                valor3 = getVariable((String) valor3);
                            }
                            if (valor4 instanceof String) {
                                valor4 = getVariable((String) valor4);
                            }
                            if (valor3 instanceof Integer && valor4 instanceof Integer) {
                                return (Integer) valor3 - (Integer) valor4;
                            } else {
                                throw new IllegalArgumentException("Operación inválida: " + valor3 + " + " + valor4);
                            }
                        case "*":
                            Object valor5 = leerInstruccion(codigo.getVector().elementAt(1));
                            Object valor6 = leerInstruccion(codigo.getVector().elementAt(2));
                            // Verificar si alguno de los argumentos es una variable
                            if (valor5 instanceof String) {
                                valor5 = getVariable((String) valor5);
                            }
                            if (valor6 instanceof String) {
                                valor6 = getVariable((String) valor6);
                            }
                            if (valor5 instanceof Integer && valor6 instanceof Integer) {
                                return (Integer) valor5 * (Integer) valor6;
                            } else {
                                throw new IllegalArgumentException("Operación inválida: " + valor5 + " * " + valor6);
                            }
    
                        case "/":
                            Object valor7 = leerInstruccion(codigo.getVector().elementAt(1));
                            Object valor8 = leerInstruccion(codigo.getVector().elementAt(2));
                            // Verificar si alguno de los argumentos es una variable
                            if (valor7 instanceof String) {
                                valor7 = getVariable((String) valor7);
                            }
                            if (valor8 instanceof String) {
                                valor8 = getVariable((String) valor8);
                            }
                            if (valor7 instanceof Integer && valor8 instanceof Integer) {
                                return (Integer) valor7 / (Integer) valor8;
                            } else {
                                throw new IllegalArgumentException("Operación inválida: " + valor7 + " / " + valor8);
                            }
  
                        case "'":
                            
                        case "QUOTE":

                        case "SETQ":
                        
                        case "DEFUN":
                            
                        case "ATOM":
    
                        case "EQUAL":

                        case "=":
                        case "EQ":
    
                        case "<":
    
                        case ">":
                            
                        case "COND":
    
                        case "PRINT":

                        default:
                            break;
                    }
                }
            }
        } 
        return intruccion;
    }

    public Object getResultado() {
    }
}