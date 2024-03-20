import org.junit.Test;

import uvg.edu.gt.Interprete;
import uvg.edu.gt.Lexer;

import static org.junit.Assert.assertEquals;

public class TestAritmetica {

    @Test
    public void testSuma() {
        // Crea un programa con una suma
        Lexer programa = new Lexer();
        programa.addInstruccion("(+ 5 3)");

        // Interpreta el programa y verifica el resultado esperado
        Interprete interprete = new Interprete(programa);
        assertEquals("(+ 5 3)", interprete.leerInstruccion(programa.getLineas().get(0)));
    }
}

