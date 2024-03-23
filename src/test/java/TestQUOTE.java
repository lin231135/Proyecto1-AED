import org.junit.Test;

import uvg.edu.gt.Interprete;
import uvg.edu.gt.Lexer;

import static org.junit.Assert.assertEquals;

public class TestQUOTE {

    @Test
    public void testQuote() {
        // Crea un programa con una instrucción QUOTE
        Lexer programa = new Lexer();
        programa.addInstruccion("(print (quote (+ 1 2)))");

        // Interpreta el programa y verifica el resultado esperado
        Interprete interprete = new Interprete(programa);
        assertEquals("(print (quote (+ 1 2)))", interprete.leerInstruccion(programa.getLineas().get(0)));
    }
}