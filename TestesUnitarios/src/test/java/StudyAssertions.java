import br.ce.wcaquino.entidades.Usuario;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudyAssertions {

    @Test
    public void test() {
        assertFalse(false);
        assertFalse(!true);
        assertTrue(true);
        assertTrue(!false);

        assertEquals(1, 1);
        assertEquals(0.51234, 0.512, 0.001);
        assertEquals(Math.PI, 3.14, 0.01);

        int i = 5;
        Integer i2 = 5;
        assertEquals(Integer.valueOf(i), i2);
        assertEquals(i, i2.intValue());

        assertEquals("bola", "bola");
        assertTrue("bola".equalsIgnoreCase("Bola"));
        assertTrue("bola".startsWith("bo"));

        Usuario u1 = new Usuario("User 1");
        Usuario u2 = new Usuario("User 1");
        Usuario u3 = null;

        assertEquals(u1, u2);

        assertSame(u1, u1);

        assertTrue(u3 == null);


        // Forma negativas
        assertNotEquals("casa", "bola");

        assertNotNull(u1);
    }
}
