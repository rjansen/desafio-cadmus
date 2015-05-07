/**
 * 
 */
package test.br.com.rjansen.desafios;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import br.com.rjansen.desafios.SubcadeiaSomaMaxima;
import br.com.rjansen.desafios.SubcadeiaSomaMaxima.CalculoErro;
import br.com.rjansen.desafios.SubcadeiaSomaMaxima.ResultadoSubcadeiaMaxima;

/**
 * @author raphaeljansen
 * 
 * Classes responsável por executar os testes na classes SubcadeiaSomaMaxima.
 *
 */
public class SubcadeiaSomaMaximaTest {

	/**
	 * Método para testar {@link br.com.rjansen.desafios.SubcadeiaSomaMaxima#calcula(java.util.List)}.
	 * 
	 * Valida a implementação do desafio, verificando um dos resultados conhecidos.
	 * 
	 */
	@Test
	public void testCalcula() {
		final List<Integer> cadeia = Arrays.asList(2, -4, 6, 8, -10, 100, -6, 5);
		final ResultadoSubcadeiaMaxima resultadoSomaMaxima = SubcadeiaSomaMaxima.calcula(cadeia);
		
		final List<Integer> resultadoEsperado = Arrays.asList(6, 8, -10, 100);
		assertTrue("Resultado esperado invalido", resultadoEsperado.equals(resultadoSomaMaxima.getSubCadeia()));
	}

	/**
	 * Método para testar {@link br.com.rjansen.desafios.SubcadeiaSomaMaxima#calcula(java.util.List)}.
	 * 
	 * Verifica o comportamento do sistema no caso de uma cadeia vazia ser enviada ao sistema.
	 * 
	 */
	@Test
	public void testCalculaCadeiaVazia() {
		final List<Integer> cadeia = new ArrayList<>();
		final ResultadoSubcadeiaMaxima resultadoSomaMaxima = SubcadeiaSomaMaxima.calcula(cadeia);
		
		//Em caso de uma cadeia vazia o resultado deve conter uma subcadeia tambem vazia
		assertTrue("Subcadeia nao esta vazia", resultadoSomaMaxima.getSubCadeia().isEmpty());
	}
	
	/**
	 * Método para testar {@link br.com.rjansen.desafios.SubcadeiaSomaMaxima#calcula(java.util.List)}.
	 * 
	 * Verifica o comportamento do sistema no caso de uma cadeia nula ser enviada ao sistema.
	 * 
	 */
	@Test(expected = CalculoErro.class)
	public void testCalculaCadeiaNula() {
		final List<Integer> cadeia = null;
		final ResultadoSubcadeiaMaxima resultadoSomaMaxima = SubcadeiaSomaMaxima.calcula(cadeia);
		
		//Em caso de uma cadeia vazia o resultado deve conter uma subcadeia tambem vazia
		assertTrue("Subcadeia nao esta vazia", resultadoSomaMaxima.getSubCadeia().isEmpty());
	}

	/**
	 * Método para testar {@link br.com.rjansen.desafios.SubcadeiaSomaMaxima#main(java.lang.String[])}.
	 * 
	 * Verifica a execucao do metodo principal.
	 * 
	 */
	@Test
	public void testExecucaoMain() {
		final String[] args = new String[]{"2, -4, 6, 8, -10, 100, -6, 5"};
		SubcadeiaSomaMaxima.main(args);
	}
	
	/**
	 * Método para testar {@link br.com.rjansen.desafios.SubcadeiaSomaMaxima#main(java.lang.String[])}.
	 * 
	 * Verifica o comportamento do sistema caso de um parametro invalido ter sido enviado ao sistema.
	 * 
	 */
	@Test(expected = NumberFormatException.class)
	public void testMainParametroInvalido() {
		final String[] args = new String[]{"abababab, uewbjdhjd, kdkdkdj"};
		SubcadeiaSomaMaxima.main(args);
	}

}
