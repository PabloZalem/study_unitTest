package servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exception.FilmeExcecaoException;
import br.ce.wcaquino.exception.LocadoraException;
import br.ce.wcaquino.servicos.LocacaoService;
import br.ce.wcaquino.utils.DataUtils;
import builder.FilmeBuilder;
import builder.UsuarioBuilder;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

public class LocacaoServiceTest {
    private static LocacaoService service;

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        service = new LocacaoService();
    }

    @Test
    public void deveAlugarFilmeComSucesso() throws Exception {
        // Cenario - Inicializar
        Usuario usuario = UsuarioBuilder.usuarioBuilder().agora();
        List<Filme> filmes = asList(new Filme("testefilme", 2, 5.0));

        // Acao - Alugar filme
        Locacao locacao = service.alugarFilme(usuario, filmes);

        // Verificação - Assert
        assertEquals(5.0, locacao.getValor(), 0.01);
        assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
        assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));

        // AsserThat
        error.checkThat(locacao.getValor(), is(equalTo(5.0)));
    }


    @Test
    public void deveAlugarFilmeComSucesso_2() throws Exception {
        // Cenario - Inicializar
        Usuario usuario = UsuarioBuilder.usuarioBuilder().agora();
        List<Filme> filmes = asList(new Filme("testefilme", 2, 5.0));

        // Acao - Alugar filme
        Locacao locacao = service.alugarFilme(usuario, filmes);

        // Verificação - Assert
        assertEquals(5.0, locacao.getValor(), 0.01);
        assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
        assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));

        // AsserThat
        assertThat(locacao.getValor(), is(equalTo(5.0)));
        assertThat(locacao.getValor(), is(not(6.0)));
    }

    @Test
    public void deveAlugarFilmeComSucesso3() throws Exception {
        // Cenario - Inicializar
        Usuario usuario = UsuarioBuilder.usuarioBuilder().agora();
        List<Filme> filmes = asList(new Filme("testefilme", 2, 5.0));

        // Acao - Alugar filme
        Locacao locacao = service.alugarFilme(usuario, filmes);

        // Verificação - Assert
        assertEquals(5.0, locacao.getValor(), 0.01);
        assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
        assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));

        // AsserThat
        assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
    }

    @Test
    public void deveAlugarFilmeComSucesso4() throws Exception {
        // Cenario - Inicializar
        Usuario usuario = UsuarioBuilder.usuarioBuilder().agora();
        List<Filme> filmes = asList(new Filme("testefilme", 2, 5.0));

        // Acao - Alugar filme
        Locacao locacao = service.alugarFilme(usuario, filmes);

        // Verificação - Assert
        assertEquals(5.0, locacao.getValor(), 0.01);
        assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
        assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));

        // AsserThat
        assertThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));
    }

    @Test
    public void deveAlugarFilmeComSucesso5() throws Exception {
        // Cenario - Inicializar
        Usuario usuario = UsuarioBuilder.usuarioBuilder().agora();
        List<Filme> filmes = asList(new Filme("testefilme", 2, 5.0));

        // Acao - Alugar filme
        Locacao locacao = service.alugarFilme(usuario, filmes);

        // Verificação - Assert
        assertEquals(5.0, locacao.getValor(), 0.01);
    }

    @Test
    public void deveAlugarFilmeComSucesso6() throws Exception {
        // Cenario - Inicializar
        Usuario usuario = UsuarioBuilder.usuarioBuilder().agora();
        List<Filme> filmes = asList(new Filme("testefilme", 2, 5.0));

        // Acao - Alugar filme
        Locacao locacao = service.alugarFilme(usuario, filmes);

        // Verificação - Assert
        assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
    }

    @Test
    public void deveAlugarFilmeComSucesso7() throws Exception {
        // Cenario - Inicializar
        Usuario usuario = UsuarioBuilder.usuarioBuilder().agora();
        List<Filme> filmes = asList(new Filme("testefilme", 2, 5.0));

        // Acao - Alugar filme
        Locacao locacao = service.alugarFilme(usuario, filmes);

        // Verificação - Assert
        assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
    }

    // Forma elegante -> Garantindo o porque a excecao esta vindo, nao e normal
    @Test(expected = Exception.class)
    public void deveLancarExcecaoAoAlugarFilmeSemEstoque() throws Exception {
        // Cenario - Inicializar
        Usuario usuario = UsuarioBuilder.usuarioBuilder().agora();
        List<Filme> filmes = asList(new Filme("testefilme", 0, 5.0));

        // Acao - Alugar filme
        service.alugarFilme(usuario, filmes);
    }

    @Test // Forma robusta
    public void naoDeveAlugarFilmeSemEstoque() throws LocadoraException {
        // Cenario - Inicializar
        Usuario usuario = UsuarioBuilder.usuarioBuilder().agora();
        List<Filme> filmes = Arrays.asList(FilmeBuilder.filmeBuilder().semEstoque().agora());

        // Acao - Alugar filme
        try {
            Locacao locacao = service.alugarFilme(usuario, filmes);
            fail("Deveria ter lancado uma excecao");
        } catch (LocadoraException e) {
            assertThat(e.getMessage(), is("Filme sem estoque"));
        }
    }

    @Test // Forma nova
    public void naoPodeSerPossivelAlugarFilmeSemEstoque() throws Exception {
        // Cenario - Inicializar
        Usuario usuario = UsuarioBuilder.usuarioBuilder().agora();
        List<Filme> filmes = asList(FilmeBuilder.umFilmeSemEstoque().agora());

        // Complemento do cenário
        exception.expect(Exception.class);
        exception.expectMessage("Filme sem estoque");

        // Acao - Alugar filme
        Locacao locacao = service.alugarFilme(usuario, filmes);
    }

    @Test
    public void naoDeveAlugarFilmeSemUsuario() throws FilmeExcecaoException {
        // Cenario
        List<Filme> filmes = asList(new Filme("Filme 2", 1, 4.0));
        Usuario usuario = new Usuario("teste123");

        // Acao
        try {
            service.alugarFilme(null, filmes);
            fail();
        } catch (LocadoraException e) {
            assertThat(e.getMessage(), is("Usuario vazio"));
        }
    }

    @Test
    public void naoDeveAlugarFilmeSemFilme() throws LocadoraException, FilmeExcecaoException {
        // Cenario
        Usuario usuario = UsuarioBuilder.usuarioBuilder().agora();

        exception.expect(LocadoraException.class);

        // Acao
        service.alugarFilme(usuario, null);
    }

    @Test
    public void devePagar75PorcentoNoTerceiroFilme() throws FilmeExcecaoException, LocadoraException{
        // Cenario
        Usuario usuario = UsuarioBuilder.usuarioBuilder().agora();
        List<Filme> filmes = asList(new Filme("Filme 1", 2, 4.0),
                new Filme("Filme 2", 2, 4.0),
                new Filme("Filme 3", 2, 4.0));

        // Acao
        Locacao locacao = service.alugarFilme(usuario, filmes);

        // Verificacao
        assertThat(locacao.getValor(), is(11.0));
    }

    @Test
    public void devePagar50PorcentoNoQuartoFilme() throws FilmeExcecaoException, LocadoraException{
        // Cenario
        Usuario usuario = UsuarioBuilder.usuarioBuilder().agora();
        List<Filme> filmes = asList(new Filme("Filme 1", 2, 4.0),
                new Filme("Filme 2", 2, 4.0),
                new Filme("Filme 3", 2, 4.0),
                new Filme("Filme 4", 2, 4.0));

        // Acao
        Locacao locacao = service.alugarFilme(usuario, filmes);

        // Verificacao
        assertThat(locacao.getValor(), is(13.0));
    }

    @Test
    public void devePagar25PorcentoNoQuintoFilme() throws FilmeExcecaoException, LocadoraException{
        // Cenario
        Usuario usuario = UsuarioBuilder.usuarioBuilder().agora();
        List<Filme> filmes = asList(new Filme("Filme 1", 2, 4.0),
                new Filme("Filme 2", 2, 4.0),
                new Filme("Filme 3", 2, 4.0),
                new Filme("Filme 4", 2, 4.0),
                new Filme("Filme 4", 2, 4.0));

        // Acao
        Locacao locacao = service.alugarFilme(usuario, filmes);

        // Verificacao
        assertThat(locacao.getValor(), is(14.0));
    }

    @Test
    public void devePagar0PorcentoNoSextoFilme() throws FilmeExcecaoException, LocadoraException{
        // Cenario
        Usuario usuario = UsuarioBuilder.usuarioBuilder().agora();
        List<Filme> filmes = asList(new Filme("Filme 1", 2, 4.0),
                new Filme("Filme 2", 2, 4.0),
                new Filme("Filme 3", 2, 4.0),
                new Filme("Filme 4", 2, 4.0),
                new Filme("Filme 5", 2, 4.0),
                new Filme("Filme 6", 2, 4.0));

        // Acao
        Locacao locacao = service.alugarFilme(usuario, filmes);

        // Verificacao
        assertThat(locacao.getValor(), is(14.0));
    }


    @Test
    @Ignore
    public void naoDeveDevolverFilmeNoDomingo() throws LocadoraException {
        Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

        // cenario
        Usuario usuario = UsuarioBuilder.usuarioBuilder().agora();
        List<Filme> filmes = asList(new Filme("Filme 1", 1, 5.0));

        // Acao
        Locacao locacao = service.alugarFilme(usuario, filmes);

        // Verificacao
        assertThat(locacao.getDataRetorno(), new DiaSemanaMatcher(Calendar.MONDAY));
        assertThat(locacao.getDataRetorno(), MatchersProrios.caiEm(Calendar.MONDAY));
        assertThat(locacao.getDataRetorno(), MatchersProrios.caiNumaSegunda());
    }

    @Test
    public void deveAlugarFilme() throws LocadoraException {
        Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

        // Cenario
        Usuario usuario = UsuarioBuilder.usuarioBuilder().agora();
        List<Filme> filmes = Arrays.asList(FilmeBuilder.filmeBuilder().comValor(5.0).agora());

        // Acao
        Locacao locacao = service.alugarFilme(usuario, filmes);

        // Verificacao
        error.checkThat(locacao.getValor(), is(equalTo(5.0)));
        error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
        error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));
    }
}
