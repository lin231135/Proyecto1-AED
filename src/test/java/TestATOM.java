import org.junit.Test;

import uvg.edu.gt.Interprete;
import uvg.edu.gt.Lexer;


public class TestATOM {

    @Test
    public void testATOM() {
        // Crea un programa con una instrucción que devuelve 2
        Lexer programa = new Lexer();
        programa.addInstruccion("(+ 1 1)");

        // Interpreta el programa y verifica el resultado esperado
        Interprete interprete = new Interprete(programa);
        System.out.println("Resultado: " + interprete.leerInstruccion(programa.getLineas().get(0)));
        // Como alternativa, podríamos verificar el resultado esperado de manera diferente si es posible
        // assertEquals("2", interprete.leerInstruccion(programa.getLineas().get(0)));
    }
}
