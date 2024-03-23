import org.junit.Test;

import uvg.edu.gt.Interprete;
import uvg.edu.gt.Lexer;

import static org.junit.Assert.assertEquals;

public class TestSETQ {

    @Test
    public void testSETQ() {
        // Crea un programa con una instrucción SETQ
        Lexer programa = new Lexer();
        programa.addInstruccion("(setq x 7)");

        // Interpreta el programa y verifica que la variable se haya definido correctamente
        Interprete interprete = new Interprete(programa);
        assertEquals(7, interprete.getVariable("x"));
    }

    // Agrega más pruebas para otros casos de SETQ
}