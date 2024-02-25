package uvg.edu.gt;

import java.util.Vector;

/**
 * Implementación de una pila utilizando la clase Vector de Java.
 * @param <E> Tipo de elementos que contendrá la pila.
 */
public class Stack<E> {

    private Vector<E> vector; // Vector que almacena los elementos de la pila

    /**
     * Constructor de una pila vacía.
     */
    public Stack() {
        vector = new Vector<>();
    }

    /**
     * Constructor de una pila con capacidad inicial especificada.
     * @param i Capacidad inicial de la pila.
     */
    public Stack(int i) {
        this.vector = new Vector<>(i);
    }

    /**
     * Obtiene el vector interno que representa la pila.
     * @return El vector que almacena los elementos de la pila.
     */
    public Vector<E> getVector() {
        return this.vector;
    }

    /**
     * Agrega un elemento a la parte superior de la pila.
     * @param item El elemento a agregar.
     */
    public void push(E item) {
        vector.add(item);
    }

    /**
     * Elimina y retorna el elemento en la parte superior de la pila.
     * @return El elemento eliminado de la parte superior de la pila.
     */
    public E pop() {
        return vector.remove(vector.size() - 1);
    }

    /**
     * Retorna el elemento en la parte superior de la pila sin eliminarlo.
     * @return El elemento en la parte superior de la pila.
     */
    public E peek() {
        return vector.get(vector.size() - 1);
    }

    /**
     * Verifica si la pila está vacía.
     * @return true si la pila está vacía, false de lo contrario.
     */
    public boolean empty() {
        return vector.isEmpty();
    }

    /**
     * Retorna la cantidad de elementos en la pila.
     * @return El tamaño de la pila.
     */
    public int size() {
        return vector.size();
    }

    /**
     * Obtiene el elemento en la posición especificada de la pila.
     * @param index Índice del elemento a obtener.
     * @return El elemento en la posición especificada.
     */
    public E get(int index) {
        return vector.get(index);
    }
}
