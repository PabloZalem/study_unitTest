import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CalculadoraTest {
    Calculadora calculadora;

    @Before
    public void setup() {
        calculadora = new Calculadora();
    }

    @Test
    public void deveSomarDoisValores() {
        // Cenario
        int a = 5;
        int b = 3;

        // Acao
        int resultado = calculadora.somar(a, b);

        // Verificacao
        assertEquals(8, resultado);
    }

    @Test
    public void deveSubtrairDoisValores() {
        // Cenario
        int a = 8;
        int b = 5;

        // Acao
        int resultado = calculadora.subtracao(a, b);

        // Verificacao
        assertEquals(3, resultado);
    }

    @Test
    public void deveDividirDoisValores() {
        // Cenario
        int a = 10;
        int b = 5;

        // Acao
        int resultado = calculadora.divisao(a, b);

        // Verificacao
        assertEquals(2, resultado);
    }

    @Test
    public void deveLancarUmaExcecaoPorDivisaoPorZero() {
        // Cenario
        int a = 2;
        int b = 0;

        assertThrows(Calculadora.DividePerZeroException.class,
                () -> calculadora.divisao(a, b));
    }

    @Test
    public void deveMultiplicarDoisValores() {
        // Cenario
        int a = 2;
        int b = 10;

        int resultado = calculadora.multiplicacao(a, b);

        assertEquals(20, resultado);
    }
}
