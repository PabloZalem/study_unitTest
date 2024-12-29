import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import servicos.CalculoValorLocacaoTes;
import servicos.LocacaoServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CalculadoraTest.class,
        OrdemTest.class,
        StudyAssertions.class,
        CalculoValorLocacaoTes.class,
        LocacaoServiceTest.class
})
public class SuitExecution {

    @BeforeClass
    public static void before() {
        System.out.println("INICIO DE UMA SUIT");
    }

    @AfterClass
    public static void after() {
        System.out.println("TERMINO DA SUIT");
    }
}
