package br.com.rjansen.desafios;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SistemaRobo {
	
	private enum Orientacao {
		ESQUERDA, DIREITA;
	}
	
	private enum Direcao {
		NORTE("N"), SUL("S"), LESTE("L"), OESTE("O");
		
		private final String apelido;

		private Direcao(String apelido) {
			this.apelido = apelido;
		}
		
		public String getApelido() {
			return apelido;
		}
		
		public static Direcao valorPeloApelido(String apelido) {
			for (Direcao direcao : values()) {
				if (direcao.getApelido().equals(apelido)) {
					return direcao;
				}
			}
			throw new RuntimeException(String.format("ImpossivelObterDirecaoPeloApelido: Apelido=[%s]", apelido));
		}
	}
	
	private enum Comando {
		VIRAR_ESQUERDA("L"), VIRAR_DIREITA("R"), MOVER("M"), TELETRANSPORTAR("T");
		
		private final String apelido;

		private Comando(String apelido) {
			this.apelido = apelido;
		}
		
		public String getApelido() {
			return apelido;
		}
		
		public static Comando valorPeloApelido(String apelido) {
			for (Comando comando : values()) {
				if (comando.getApelido().equals(apelido)) {
					return comando;
				}
			}
			throw new RuntimeException(String.format("ImpossivelObterComandoPeloApelido: Apelido=[%s]", apelido));
		}
	}
	
	
	
	private static class Coordenada {
		private final Integer x;
		private final Integer y;
		
		public Coordenada(Integer x, Integer y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		public Integer getX() {
			return x;
		}
		public Integer getY() {
			return y;
		}

		@Override
		public String toString() {
			return String.format("Coordenada [x=%s, y=%s]", x, y);
		}
	}
	
	private static class Posicao {
		private final Coordenada coordenada;
		private final Direcao direcao;
		public Posicao(Coordenada coordenada, Direcao direcao) {
			super();
			this.coordenada = coordenada;
			this.direcao = direcao;
		}
		public Coordenada getCoordenada() {
			return coordenada;
		}
		public Direcao getDirecao() {
			return direcao;
		}
		@Override
		public String toString() {
			return String.format("Posicao [coordenada=%s, direcao=%s]",
					coordenada, direcao);
		}
	}
	
	private static class ConversorComando {
		private static final Pattern TAMAMHO_ESPACO_PATTERN = Pattern.compile("(\\d+)[ ](\\d+)");
		private static final Pattern POSICAO_PATTERN = Pattern.compile("(\\d+)[ ](\\d+)[ ]([NSLO])");
		private static final Pattern COMANDOS_PATTERN = Pattern.compile("([LRM])|([T])[ ](\\d+)[ ](\\d+)");
		
		public static Coordenada converterTamanhoEspaco(String comando) {
			final Matcher matcher = TAMAMHO_ESPACO_PATTERN.matcher(comando);
			if (matcher.matches()) {
				final Coordenada coordenada = new Coordenada(Integer.valueOf(matcher.group(1)), Integer.valueOf(matcher.group(1)));
				return coordenada;
			} else {
				throw new RuntimeException(String.format("ImpossivelConverterEspaco: Comando=[%s]", comando));
			}
		}
		
		public static Posicao converterPosicao(String comando) {
			final Matcher matcher = POSICAO_PATTERN.matcher(comando);
			if (matcher.matches()) {
				final Posicao posicao = new Posicao(new Coordenada(Integer.valueOf(matcher.group(1)), Integer.valueOf(matcher.group(1))), Direcao.valorPeloApelido(matcher.group(2)));
				return posicao;
			} else {
				throw new RuntimeException(String.format("ImpossivelConverterPosicao: Comando=[%s]", comando));
			}
		}
		
		public static List<Comando> converterComandos(String comandos) {
			final Matcher matcher = COMANDOS_PATTERN.matcher(comandos);
			final List<Comando> listaComandos = new ArrayList<>();
			while (matcher.find()) {
				final Comando comando = Comando.valorPeloApelido(matcher.group(1));
				listaComandos.add(comando);
			} 
			if (listaComandos.isEmpty()) {
				throw new RuntimeException(String.format("ImpossivelConverterComandos: Comandos=[%s]", comandos));
			}
			return listaComandos;
		}
	}
	
	private List<Comando> comandos = new ArrayList<>();
	private final Coordenada tamanhoEspaco;
	private final Posicao posicaoInicial;
	
	public SistemaRobo(List<String> comandos) {
		super();
		final String tamanhoEspaco = comandos.get(0);
		final String posicaoInicial = comandos.get(1);
		this.tamanhoEspaco = ConversorComando.converterTamanhoEspaco(tamanhoEspaco);
		this.posicaoInicial = ConversorComando.converterPosicao(posicaoInicial);
		for (final String comandosStr : comandos.subList(2, comandos.size())) {
			final List<Comando> comandoLista = ConversorComando.converterComandos(comandosStr);
			this.comandos.addAll(comandoLista);
		}
	}

	private void virar(Orientacao orientacao) {
		
	}
	
	private void mover() {
		
	}
	
	private void teletransportar(Integer x, Integer y) {
		
	}
	
	
	public static void main(String[] args) {

	}

}
