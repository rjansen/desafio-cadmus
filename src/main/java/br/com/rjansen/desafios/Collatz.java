package br.com.rjansen.desafios;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;

import java.util.ArrayList;
import java.util.List;

/**
 * @author raphaeljansen
 *
 * Classe reponsável por resolver o desafio sobre a Conjectura de Collatz.
 * Desafio proposto:
 * 
 *  Conjectura de Collatz
 *  
 *  A seguinte sequência iterativa é definida pelo conjunto de inteiros positivos onde:
 *
 *  n -> n/2 (se n é par) 
 *  n -> 3n + 1 (se n é impar)
 *
 *  Usando as regras acima e começando pelo número 13, nós geraríamos a seguinte sequência:
 *
 *  13 40 20 10 5 16 8 4 2 1
 *
 *  O que pode ser observado dessa sequência (começando no 13 e terminando no 1) é que ela contém 10 itens. Embora ainda não esteja matematicamente provado, é esperando que, dado um numero inteiro positivo qualquer, a sequencia sempre chegará em 1.
 *
 *  Qual inteiro positivo abaixo de 1 milhão, produz a sequência com mais itens?
 *
 *  OBS: seu código precisa executar em menos de 5 segundos para o caso de 1 milhão.
 * 
 */
public class Collatz {
	
	private final List<Long> sequenciaGerada = new ArrayList<>();
	
	/**
	 * Retorna uma cópia da sequencia gerada.
	 * @return List<Long> - Lista de inteiros longos que representa a sequencia gerada
	 */
	public List<Long> getSequenciaGerada() {
		return new ArrayList<Long>(sequenciaGerada);
	}
	
	/**
	 * Limpa a lista que representa a última sequencia gerada.
	 */
	public void limpaSequenciaGerada() {
		sequenciaGerada.clear();
	}

	/**
	 * Ececuta a conjectura de Collatz no número informado
	 * @param numero - Numero aonde será aplicada a função
	 */
	public void executa(Long numeroInicial) {
		sequenciaGerada.clear();
		Long numero = numeroInicial;
		while(true) {
			sequenciaGerada.add(numero);
			if (numero == 1) {
				break;
			}
			if (numero % 2 == 0) {
				numero = numero / 2;
			} else {
				numero = 3 * numero + 1;
			}
		}
	}

	/**
	 * Tenta converter a string possivelNumero em um inteiro. Se conseguir retorna true caso contrário retorna false.
	 *  
	 * @param posivelNumero - Representação textual de um inteiro longo
	 * @return true se for um inteiro longo e false caso contrário
	 */
	public static boolean isInteiroLongo(String posivelNumero) {
		try {
			Long.parseLong(posivelNumero);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	/**
	 * Imprime um exemplo de chamada da classe
	 */
	public static void printUsage() {
		out.println("Use: java br.com.rjansen.desafios.Collatz <numero_inicial_collatz>\n<numero_inicial_collatz>=n\nn precisa ser um numero inteiro longo valido");
	}
	
	public static void main(String[] args) {
		final long startTime = currentTimeMillis();
		final Long valorInicialCollatz;
		if (args.length < 1 || args[0] == null || !isInteiroLongo(args[0])) {
			printUsage();
			return;
		} else {
			valorInicialCollatz = Long.parseLong(args[0]);
		}
		out.println("****** Conjectura de Collatz - Analise do numero com a maior sequencia ******");
		out.println("****** Aguarde ... Calculando as sequencias ******");
		final Collatz collatz = new Collatz();
		Long valorInicialMaiorSequencia = 0L, tamanhoMaiorSequencia = 0L;
		List<Long> maiorSequencia = new ArrayList<>();
		for (long k = valorInicialCollatz; k > 0; k--) {
			collatz.executa(k);
			if (tamanhoMaiorSequencia < collatz.getSequenciaGerada().size()) {
				tamanhoMaiorSequencia = new Long(collatz.getSequenciaGerada().size());
				valorInicialMaiorSequencia = k;
				maiorSequencia = collatz.getSequenciaGerada();
			}
			collatz.limpaSequenciaGerada();
		}
		out.printf("Numero com Maior Sequencia Gerada: \n\tNumero Inicial=%s \n\tNumero com Maior Sequencia=%s \n\tTamanho da Sequencia=%s \n\tSequencia=%s\n", valorInicialCollatz, valorInicialMaiorSequencia, tamanhoMaiorSequencia, maiorSequencia);
		final long endTime = currentTimeMillis();
		out.printf("****** Tempo Execucao Collatz: %dms ******\n", endTime - startTime);
	}

}
