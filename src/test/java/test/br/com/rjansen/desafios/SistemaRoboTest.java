/**
 * 
 */
package test.br.com.rjansen.desafios;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Test;

import br.com.rjansen.desafios.SistemaRobo;
import br.com.rjansen.desafios.SistemaRobo.ComandoInvalidoErro;
import br.com.rjansen.desafios.SistemaRobo.Coordenada;
import br.com.rjansen.desafios.SistemaRobo.CoordenadaInvalidaErro;
import br.com.rjansen.desafios.SistemaRobo.Direcao;
import br.com.rjansen.desafios.SistemaRobo.ExecucaoErro;
import br.com.rjansen.desafios.SistemaRobo.InterpretadorComandos;
import br.com.rjansen.desafios.SistemaRobo.Posicao;
import br.com.rjansen.desafios.SistemaRobo.ResultadoSistemaRobo;

/**
 * @author raphaeljansen
 * 
 * Classe repnsável por efetuar os testes da classe SistemaRobo.
 *
 */
public class SistemaRoboTest {

	private static String OS = System.getProperty("os.name").toLowerCase();
	
	/**
	 * Indetifica um recurso e retorna seu caminho fisico completo.
	 * 
	 * @param nomeRecurso
	 * @return
	 */
	private static String encontraCaminhaCompleto(String nomeRecurso) {
		final URL resource = SistemaRoboTest.class.getResource(nomeRecurso);
		final String caminhoCompleto;
		if (OS.indexOf("win") >= 0) {
			caminhoCompleto = resource.toExternalForm().replace(
				"file:/", "");
		} else {
			caminhoCompleto = resource.toExternalForm().replace(
				"file:", "");
		}
		//System.out.printf("AchouCaminho: URIRecurso=%s CaminhoCompleto=%s\n", resource, caminhoCompleto);
		return caminhoCompleto;
	}

	/**
	 * 
	 * Executa o tetes verificando como o sistema se comporta no caso de uma arquivo inexistente.
	 * 
	 */
	@Test(expected = ExecucaoErro.class)
	public void testArquivoInvalido() {
		final String caminhoInvalidoArquivoComandos = "/naoexiste/arquivo.nf";
		SistemaRobo sistemaRobo = new SistemaRobo(new InterpretadorComandos(
				caminhoInvalidoArquivoComandos));
		final ResultadoSistemaRobo resultadoSistemaRobo = sistemaRobo
				.executar();
		assertTrue("Resultado invalido", resultadoSistemaRobo != null);
	}

	/**
	 * 
	 * Executa o tetes verificando como o sistema se comporta no caso de uma arquivo com comandos invalidos.
	 * 
	 */
	@Test(expected = ComandoInvalidoErro.class)
	public void testComandoInvalido() {
		final String caminhoInvalidoArquivoComandos = encontraCaminhaCompleto("/comandos_invalido.sr");
		SistemaRobo sistemaRobo = new SistemaRobo(new InterpretadorComandos(
				caminhoInvalidoArquivoComandos));
		final ResultadoSistemaRobo resultadoSistemaRobo = sistemaRobo
				.executar();

		final Posicao posicaoEsperada = new Posicao(new Coordenada(2, 1),
				Direcao.OESTE);
		assertTrue("Resultado invalido", resultadoSistemaRobo.getPosicaoFinal()
				.equals(posicaoEsperada));
	}

	/**
	 * 
	 * Executa o tetes verificando como o sistema se comporta no caso de uma arquivo com comandos que geram coordenadas invalidas.
	 * 
	 */
	@Test(expected = CoordenadaInvalidaErro.class)
	public void testCoordenadaInvalida() {
		final String caminhoInvalidoArquivoComandos = encontraCaminhaCompleto("/comandos_xy_invalido.sr");
		SistemaRobo sistemaRobo = new SistemaRobo(new InterpretadorComandos(
				caminhoInvalidoArquivoComandos));
		final ResultadoSistemaRobo resultadoSistemaRobo = sistemaRobo
				.executar();

		final Posicao posicaoEsperada = new Posicao(new Coordenada(10, 1),
				Direcao.NORTE);
		assertTrue("Resultado invalido", resultadoSistemaRobo.getPosicaoFinal()
				.equals(posicaoEsperada));
	}

	/**
	 * Método para testar {@link br.com.rjansen.desafios.SistemaRobo#executar()}
	 * 
	 * Executa o tetes verificando como o sistema se comporta executando o sistema.
	 * 
	 * @throws IOException - No caso de problemas de leitura e escrita
	 * @throws URISyntaxException - No caso de problemas com a URI
	 * 
	 */
	@Test
	public void testExecutar() throws IOException, URISyntaxException {
		SistemaRobo sistemaRobo = new SistemaRobo(new InterpretadorComandos(
				encontraCaminhaCompleto("/comandos_desafio.sr")));
		final ResultadoSistemaRobo resultadoSistemaRobo = sistemaRobo
				.executar();
		
		final Posicao posicaoEsperada = new Posicao(new Coordenada(2, 1), Direcao.OESTE);
		assertTrue("Resultado invalido", resultadoSistemaRobo.getPosicaoFinal().equals(posicaoEsperada));
	}

	/**
	 * Método para testar
	 * {@link br.com.rjansen.desafios.SistemaRobo#main(java.lang.String[])}.
	 * 
	 * Executa o tetes verificando como o sistema se comporta executando o sistema.
	 * 
	 */
	@Test
	public void testMain() {
		final String[] args = new String[] { encontraCaminhaCompleto("/comandos_teste.sr") };
		SistemaRobo.main(args);
	}

	/**
	 * Método para testar
	 * {@link br.com.rjansen.desafios.SistemaRobo#main(java.lang.String[])}.
	 * 
	 * Executa o tetes verificando como o sistema se comporta se um arquivo inexistente for informado.
	 * 
	 */
	@Test(expected = ExecucaoErro.class)
	public void testMainArquivoInvalido() {
		final String[] args = new String[] { "/naoexiste/arquivo.nf" };
		SistemaRobo.main(args);
	}

}
