/**
 * 
 */
package test.br.com.rjansen.desafios;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import br.com.rjansen.desafios.Collatz;

/**
 * @author raphaeljansen
 *
 * Classe responsavel por efetuar os testes na classe Collatz.
 *
 */
public class CollatzTest {

	/**
	 * Método para testar {@link br.com.rjansen.desafios.Collatz#executa(java.lang.Long)}.
	 * 
	 * Testa a execução do metodo qu executa a conjectura. Validando se a implementação ao menos gera o valor esperado.
	 * 
	 */
	@Test
	public void testExecuta() {
		final Long valorInicial = 13L;
		final Collatz collatz = new Collatz();
		collatz.executa(valorInicial);
		final List<Long> sequencia = collatz.getSequenciaGerada();
		
		//13 40 20 10 5 16 8 4 2 1
		final List<Long> resultadoEsperado = Arrays.asList(13L, 40L, 20L, 10L, 5L, 16L, 8L, 4L, 2L, 1L);
		assertTrue("Resultado esperado invalido", resultadoEsperado.equals(sequencia));
	}
	
	/**
	 * Método para testar {@link br.com.rjansen.desafios.Collatz#executa(java.lang.Long)}.
	 * 
	 * Testa a execução do metodo qu executa a conjectura. Validando se a implementação executou no tempo imposto pelo desafio.
	 * 
	 */
	@Test
	public void testExecutaAnalisaVelocidade() {
		final long startTime = System.currentTimeMillis();
		final Long valorInicial = 1000000L;
		final Collatz collatz = new Collatz();
		for (long k = valorInicial; k > 0; k--) {
			collatz.executa(k);
			collatz.limpaSequenciaGerada();
		}
		final long endTime = System.currentTimeMillis();
		final long tempoExecucao = endTime - startTime;
		if (tempoExecucao > 5000)
			fail(String.format("O tempo limite de 5000ms de execucao foi excedido. Tempo execucao: %sms", tempoExecucao));
	}

	/**
	 * Método para testar {@link br.com.rjansen.desafios.Collatz#main(java.lang.String[])}.
	 * 
	 * Testa a execução do metodo qu executa a conjectura. Validando se a implementação executou corretamente.
	 * 
	 */
	@Test
	public void testMain() {
		final String[] args = new String[]{"13"};
		Collatz.main(args);
	}

	/**
	 * Método para testar {@link br.com.rjansen.desafios.Collatz#main(java.lang.String[])}.
	 * 
	 * Testa o comportamento do sistema caso ele receba um parametro invalido.
	 * 
	 */
	@Test
	public void testMainParametroInvalido() {
		final String[] args = new String[]{"aaaA"};
		Collatz.main(args);
	}

}
