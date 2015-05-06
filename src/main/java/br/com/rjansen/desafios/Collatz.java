package br.com.rjansen.desafios;

import static java.lang.System.out;
import static java.lang.System.currentTimeMillis;

public class Collatz {
	
	/*
	def collatz(numero):
	    sys.stdout.write(str(numero) + " ")
	    if numero == 1:
	        return
	    if numero % 2 == 0:
	        collatz(numero / 2)
	    else:
	        collatz(3 * numero + 1)
	*/
	
	/**
	 * Ececuta a conjectura de Collatz no número informado
	 * @param numero - Numero aonde será aplicada a função
	 */
	public static void collatz(Integer numero) {
		out.print(numero + " ");
		if (numero == 1) {
			out.println();
			return;
		}
		if (numero % 2 == 0) {
			collatz(numero / 2);
		} else {
			collatz(3 * numero + 1);
		}
	}

	public static void main(String[] args) {
		final long startTime = currentTimeMillis();
		out.println("Conjectura de Collatz");
		final Integer valorInicialCollatz = 1000000;
		out.printf("Start Number %s\n", valorInicialCollatz);
		collatz(valorInicialCollatz);
		final long endTime = currentTimeMillis();
		out.printf("TempoExecucaoCollatz: %dms", endTime - startTime);
	}

}
