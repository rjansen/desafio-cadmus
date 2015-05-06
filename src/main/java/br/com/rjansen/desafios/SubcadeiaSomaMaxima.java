package br.com.rjansen.desafios;

import static java.lang.System.out;
import static java.lang.System.currentTimeMillis;

import java.util.Arrays;
import java.util.List;

public class SubcadeiaSomaMaxima {
	/*
	 def forca_bruta(vetor):
	    """
	    Algoritmo de força bruta, complexidade O(n^3)
	    """
	    print "\nUsando algoritmo de força bruta"
	
	    soma_max = 0
	    i = j = 0
	
	    for primeiro in range(len(vetor)):
	
	        for ultimo in range(primeiro,len(vetor)):
	            soma = 0
	
	            for k in range(primeiro,ultimo+1):
	                soma += vetor[k]
	
	            if soma > soma_max:
	                soma_max = soma
	                i = primeiro
	                j = ultimo
	
	    return vetor[i:j+1]
	
	 */
	public static List<Integer> subcadeiaSomaMaxima(List<Integer> cadeia) {
		Integer maiorSoma = 0, i = 0, j = 0;
		
		for (int primeiro = 0; primeiro < cadeia.size(); primeiro ++) {
			
			for (int ultimo = 0; ultimo < cadeia.subList(primeiro, cadeia.size()).size(); ultimo ++) {
				Integer soma = 0;
				for (int k = primeiro; k < ultimo+1; k ++) {
					soma += cadeia.get(k);
				}
	            if (soma > maiorSoma) {
	            	maiorSoma = soma;
	                i = primeiro;
	                j = ultimo;
	            }
			}
		}
		return cadeia.subList(i, j + 1);
	}
	
	public static void main(String[] args) {
		final long startTime = currentTimeMillis();
		out.println("Subcadeia de soma máxima");
		final List<Integer> cadeia = Arrays.asList(2, -4, 6, 8, -10, 100, -6, 5);
		final List<Integer> subCadeiaSomaMaxima = subcadeiaSomaMaxima(cadeia);
		out.println(subCadeiaSomaMaxima);
		final long endTime = currentTimeMillis();
		out.printf("TempoExecucaoSubcadeiaSomaMaxima: %dms", endTime - startTime);		
	}

}
