package servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exception.FilmeExcecaoException;
import br.ce.wcaquino.exception.LocadoraException;
import br.ce.wcaquino.servicos.LocacaoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class CalculoValorLocacaoTes {

    @Parameterized.Parameter(value = 0)
    public List<Filme> filmes;

    @Parameterized.Parameter(value = 1)
    public Double valorLocacao;
    private LocacaoService service;

    @Before
    public void setUp() throws Exception {
        service = new LocacaoService();
    }

    private static Filme filme3 = new Filme("Filme 3", 2, 4.0);
    private static Filme filme4 = new Filme("Filme 4", 2, 4.0);
    private static Filme filme2 = new Filme("Filme 2", 2, 4.0);
    private static Filme filme5 = new Filme("Filme 5", 2, 4.0);
    private static Filme filme6 = new Filme("Filme 6", 2, 4.0);
    private static Filme filme1 = new Filme("Filme 1", 2, 4.0);

    @Parameterized.Parameters()
    public static Collection<Object[]> getParametros() {
        return Arrays.asList(new Object[][]{
                {Arrays.asList(filme1, filme2, filme3), 11.0},
                {Arrays.asList(filme1, filme2, filme3, filme4), 13.0},
                {Arrays.asList(filme1, filme2, filme3, filme4, filme5), 14.0},
                {Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6), 14.0},
        });
    }

    @Test
    public void devePagar0PorcentoNoSextoFilme() throws FilmeExcecaoException, LocadoraException {
        // Cenario
        Usuario usuario = new Usuario("Usuario1");

        // Acao
        Locacao locacao = service.alugarFilme(usuario, filmes);

        // Verificacao
        assertThat(locacao.getValor(), is(valorLocacao));
    }

    @Test
    public void print() {
        System.out.println(valorLocacao);
    }
}
