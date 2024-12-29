public class Calculadora {
    public int somar(int a, int b) {
        return a + b;
    }

    public int subtracao(int a, int b) {
        return a - b;
    }

    public int divisao(int a, int b) {
        if (a == 0 || b == 0) throw new DividePerZeroException();
        return a / b;
    }

    public int multiplicacao(int a, int b) {
        return a * b;
    }

    public class DividePerZeroException extends ArithmeticException{
    }
}
