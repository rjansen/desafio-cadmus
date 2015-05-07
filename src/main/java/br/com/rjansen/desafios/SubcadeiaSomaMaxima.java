package br.com.rjansen.desafios;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author raphaeljansen
 *
 * Classe reponsável por resolver o desafio sobre a Subcadeia de soma maxima.
 * Desafio proposto:
 * 
 *  Subcadeia de soma máxima
 *  
 *  Dado um conjunto de números, descobrir o subconjunto em que a soma dos elementos são de máxima soma.
 *   
 *  Exemplo: dado o conjunto de elementos 
 *  [2, -4, 6, 8, -10, 100, -6, 5]
 *  O subconjunto de soma máxima é:
 *  [2, -4 [6, 8, -10, 100] -6, 5]
 *  Assim, o programa deve retornar a posição do primeiro e do último elemento da subcadeia. Neste exemplo, as posições 2 e 5, considerando a primeira posição com índice 0.
 */
public class SubcadeiaSomaMaxima {

	/**
	 * @author raphaeljansen
	 *
	 * Classe de dados que representa o resultado da analise de uma cadeia:
	 *  valorSoma: O maior valor de soma obtido
	 *  subCadeia: Sequencia de numeros contentdo os elementos da cadeia que somados prouziram o maior valor de soma
	 *  posicaoInicialNaCadeia: Posicao inicial, indice, em que a subcadeia pode ser encontrada
	 *  posicaoFinalNaCadeia: Posicao final, indice, em que a subcadeia pode ser encontrada
	 */
	public static class ResultadoSubcadeiaMaxima {
		private final Integer valorSoma;
		private final List<Integer> subCadeia;
		private final Integer posicaoInicialNaCadeia;
		private final Integer posicaoFinalNaCadeia;

		public ResultadoSubcadeiaMaxima(Integer valorSoma, 
				Integer posicaoInicialNaCadeia, Integer posicaoFinalNaCadeia,
				List<Integer> subCadeia) {
			super();
			this.valorSoma = valorSoma;
			this.posicaoInicialNaCadeia = posicaoInicialNaCadeia;
			this.posicaoFinalNaCadeia = posicaoFinalNaCadeia;
			this.subCadeia = subCadeia;
		}

		public Integer getValorSoma() {
			return valorSoma;
		}

		public List<Integer> getSubCadeia() {
			return new ArrayList<>(subCadeia);
		}

		public Integer getPosicaoInicialNaCadeia() {
			return posicaoInicialNaCadeia;
		}

		public Integer getPosicaoFinalNaCadeia() {
			return posicaoFinalNaCadeia;
		}

		@Override
		public String toString() {
			return String
					.format("ResultadoSubcadeiaMaxima [valorSoma=%s, subCadeia=%s, posicaoInicialNaCadeia=%s, posicaoFinalNaCadeia=%s]",
							valorSoma, subCadeia, posicaoInicialNaCadeia,
							posicaoFinalNaCadeia);
		}

	}

	/**
	 * Calcula qual é a subcadeia que tem maior valor na soma de seus items
	 * 
	 * @param cadeia - Sequencia de numero inteiros de onde se deseja extrair a subcadeia
	 * @return ResultadoSubcadeiaMaxima - Retorna o valor da soma e a subcadeia
	 */
	public static ResultadoSubcadeiaMaxima calcula(List<Integer> cadeia) {
		Integer maiorSoma = 0, inicioSubCadeia = 0, fimSubCadeia = 0;

		for (int primeiro = 0; primeiro < cadeia.size(); primeiro++) {
			for (int ultimo = 0; ultimo < cadeia.subList(primeiro,
					cadeia.size()).size(); ultimo++) {
				Integer soma = 0;
				for (int k = primeiro; k < ultimo + 1; k++) {
					soma += cadeia.get(k);
				}
				if (soma > maiorSoma) {
					maiorSoma = soma;
					inicioSubCadeia = primeiro;
					fimSubCadeia = ultimo;
				}
			}
		}
		return new ResultadoSubcadeiaMaxima(maiorSoma, inicioSubCadeia, fimSubCadeia, fimSubCadeia == 0 ? new ArrayList<>() : cadeia.subList(
				inicioSubCadeia, fimSubCadeia + 1));
	}

	/**
	 * Imprime um exemplo de chamada da classe
	 */
	private static void printUsage() {
		out.println("Use: java br.com.rjansen.desafios.SubcadeiaSomaMaxima <cadeia>\n<cadeia>=1,20,-3,4000,n\nn precisa ser um numero inteiro valido");
	}

	public static void main(String[] args) {
		final long startTime = currentTimeMillis();
		final List<Integer> cadeia;
		if (args.length < 1 || args[0] == null) {
			printUsage();
			return;
		} else {
			cadeia = new ArrayList<>();
			Arrays.stream(args[0].replaceAll("[ ]+", "").split(",")).mapToInt(Integer::valueOf)
					.forEachOrdered(itemCadeia -> cadeia.add(itemCadeia));
		}
		out.println("****** Subcadeia de Soma Maxima - Analisa e demonstra a subsequencia que gera a maior soma ******");
		out.println("****** Aguarde ... Calculando os valores ******");
		final ResultadoSubcadeiaMaxima resultadoSomaMaxima = calcula(cadeia);
		out.printf("Subcadeia com Maior Soma: \n\tCadeia=%s \n\tMaior Soma=%s \n\tPosicao Inicial na Cadeia=%s \n\tPosicao final na cadeia=%s \n\tSubcadeia=%s\n",
										cadeia, resultadoSomaMaxima.getValorSoma(),
										resultadoSomaMaxima.getPosicaoInicialNaCadeia(),
										resultadoSomaMaxima.getPosicaoFinalNaCadeia(),
										resultadoSomaMaxima.getSubCadeia());
		final long endTime = currentTimeMillis();
		out.printf("****** Tempo Execucao Subcadeia Soma Maxima: %dms ******\n",
				endTime - startTime);

	}

}
