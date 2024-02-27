package uvg.edu.gt;

/**
 * Clase que representa una función en el lenguaje de programación Lips.
 */
public class Funcion {
    private String nombre; // Nombre de la función
    private Stack<String> parametros; // Parámetros de la función
    private Stack<Object> cuerpo; // Cuerpo de la función

    /**
     * Constructor de la clase Funcion.
     * @param nombre El nombre de la función.
     * @param parametros Los parámetros de la función.
     * @param cuerpo El cuerpo de la función.
     */
    public Funcion(String nombre, Stack<String> parametros, Stack<Object> cuerpo) {
        this.nombre = nombre;
        this.parametros = parametros;
        this.cuerpo = cuerpo;
    }

    /**
     * Obtiene el nombre de la función.
     * @return El nombre de la función.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene los parámetros de la función.
     * @return Los parámetros de la función.
     */
    public Stack<String> getParametros() {
        return parametros;
    }

    /**
     * Obtiene el cuerpo de la función.
     * @return El cuerpo de la función.
     */
    public Stack<Object> getCuerpo() {
        return cuerpo;
    }
}
